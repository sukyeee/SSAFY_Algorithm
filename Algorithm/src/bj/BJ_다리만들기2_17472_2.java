package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

	// 섬들을 정점으로 만든다. => dfs
	// 간선 ( 두 정점, 연결 비용 ) 구성
	//   가로 ---> 섬들을 연결하는 간선 hr()
	//   세로 ---> 섬들을 연결하는 간선 vr()
	//   위 과정에서 파악되는 간선을 자료구조에 담는다. PriorityQueue ( 최소비용의 간선을 뽑기 위해 이용 )
	
	// 프림
	// 정점 중심
	// 최소 비용의 정점을 선택하면서 완성 , 선택된 정점으로부터 갈 수 있는 다른 정점들을 모두 고려 대상으로! (visit)
	public class BJ_다리만들기2_17472_2 {
	
	static int N, M, min;
	static int[][] map;
	// dfs
	static boolean[][] visit;
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	// 정점, 간선
	static int V; // 정점의 수
	static PriorityQueue<Edge> pqueue = new PriorityQueue<Edge>( (e1, e2) -> e1.cost - e2.cost );
	
	// 프림
	static ArrayList<ArrayList<Edge>> vertex;
	static boolean[] visitPrim;
	
	
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	
	    map = new int[N][M];
	    visit = new boolean[N][M];
	    
	    // map 입력
	    for (int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < M; j++) {
	            map[i][j] = Integer.parseInt(st.nextToken()) * (-1); // 육지를 -1 로 <= 정점번호를 1부터 ..
	        }
	    }
	    
	    // 풀이
	    // dfs 로 번호 붙이기
	    int num = 1;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if( map[i][j] == -1 && ! visit[i][j]) {
	                dfs( i, j, num); // num 번호를 i, j 와 연결된 모든 곳에 저장
	                num++;
	            }
	        }
	    }
	    
	    // 정점의 수
	    V = num - 1;
	    
	    // 프림
	    vertex = new ArrayList<ArrayList<Edge>>();
	    for (int i = 0; i <= V; i++) {
	        vertex.add(new ArrayList<Edge>());
	    }
	    visitPrim = new boolean[V+1];
	    
	    // 간선 계산
	    hr(); // 옆으로
	    vr(); // 밑으로
	    
	    int cnt = 1;
	    visitPrim[1] = true;
	    pqueue.addAll(vertex.get(1));
	    
	    while( !pqueue.isEmpty() ) {
	        Edge edge = pqueue.poll();
	        if( visitPrim[edge.v] ) continue; // 이미 선택된 정점으 skip
	        
	        pqueue.addAll(vertex.get(edge.v));
	        visitPrim[edge.v] = true;
	        min += edge.cost;
	        
	        cnt++;
	        if( cnt == V ) break; // 선택된 정점이 V 이면 종료
	    }
	
	    
	    if( cnt != V ) min = -1;
	    System.out.println( min == 0 ? -1 : min );
	}
	
	static void addEdge(int v1, int v2, int cost) {
	    boolean same = false;
	    
	    for (Edge edge : vertex.get(v1) ) {
	        
	        if( edge.v == v2 ) {
	            same = true;
	            edge.cost = Math.min(edge.cost, cost);
	            break;
	        }
	    }
	    
	    if( !same) vertex.get(v1).add(new Edge(v2, cost));
	}
	
	static void hr() {
	    for (int i = 0; i < N; i++) {
	        int prev = 0;
	        int curr = 0;
	        int v1 = 0;
	        int v2 = 0;
	        int cost = 0;
	        
	        for (int j = 0; j < M; j++) {
	            curr = map[i][j];
	            if( prev == 0 && curr != 0 ) { // 0 ( 바다, 시작 경계선) -> 0 아님 ( 섬 )
	                if( v1 == 0 ) v1 = curr; // 시작 경계선
	                else {
	                    // edge 발생
	                    v2 = curr;
	                    if( cost > 1 ) {
	                        addEdge( v1, v2, cost ); // 간선 확정
	                        addEdge( v2, v1, cost );
	                    }
	                    
	                    v1 = v2;
	                    v2 = 0;
	                    cost = 0;
	                }
	            }else if( v1 != 0 && curr == 0 ) {
	                cost++;
	            }
	            
	            prev = curr;
	        }
	        
	    }
	}
	
	static void vr() {
	    for (int i = 0; i < M; i++) {
	        int prev = 0;
	        int curr = 0;
	        int v1 = 0;
	        int v2 = 0;
	        int cost = 0;
	        
	        for (int j = 0; j < N; j++) {
	            curr = map[j][i];
	            if( prev == 0 && curr != 0 ) { // 0 ( 바다, 시작 경계선) -> 0 아님 ( 섬 )
	                if( v1 == 0 ) v1 = curr; // 시작 경계선
	                else {
	                    // edge 발생
	                    v2 = curr;
	                    if( cost > 1 ) {
	                        addEdge( v1, v2, cost ); // 간선 확정
	                        addEdge( v2, v1, cost );
	                    }
	                    
	                    v1 = v2;
	                    v2 = 0;
	                    cost = 0;
	                }
	            }else if( v1 != 0 && curr == 0 ) {
	                cost++;
	            }
	            
	            prev = curr;
	        }
	        
	    }
	}
	
	static void dfs(int y, int x, int num) {
	    visit[y][x] = true;
	    map[y][x] = num;
	    
	    for (int d = 0; d < 4; d++) {
	        int ny = y + dy[d];
	        int nx = x + dx[d];
	        
	        if( ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] ) continue;
	        if( map[ny][nx] == -1 ) dfs( ny, nx, num);
	    }
	}
	
	
	// 간선
	static class Edge{
	    int v, cost;
	    Edge(int v, int cost){
	        this.v = v;
	        this.cost = cost;
	    }
	}
	}