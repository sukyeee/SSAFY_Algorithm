package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ17472다리만들기2 {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int matrix[][];
	static int islandCnt;
	static int cost[][];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료 
		
		
		// 색칠되어있는 칸은 땅 
		
		// 다리는 바다에만 건설 가능, 다리의 길이는 다리가 격자에서 차지하는 칸 수 
		// A에서 다리를 통해 B로 갈 수 있을 때, A B 는 연결. 다리 길이는 2 이상, 다리의 방향은 중간에 바뀌면 안됌 
		// 다리 방향은 가로, 세로 
		
		// 모든 섬을 연결하는 다리 길이의 최솟값 
		
		
		
		// 다리의 개수가 섬의 개수 - 1 를 만족하면 종료 
		
		// 섬의 개수 세기
		islandCnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visit[i][j] ) {
					dfs(i, j, islandCnt);
					islandCnt++;
				}
			}
		}
		
		// 섬과 섬 사이의 최단거리 구하기 
		matrix = new int[islandCnt][islandCnt];
		
		// BFS로 탐색 
		bfs();
	
		
		System.out.println(min);
	}
	
	static void bfs() {
		
		// 섬과 섬 사이 최단거리 구하기 
		
		cost = new int[islandCnt][islandCnt];
		
		
		for (int i = 0; i < islandCnt; i++) {
			for (int j = 0; j < islandCnt; j++) {
				if(i == j) continue;
				cost[i][j] = Integer.MAX_VALUE;	
			}
		}
		
		for (int k = 1; k <= islandCnt; k++) {
			visit = new boolean[N][M];
			Deque<Dist> q = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == k) {
						q.offer(new Dist(i, j, 0));
						visit[i][j] = true;
					}
				}
			}
			
			while(!q.isEmpty()) {
				
				Dist e = q.poll();
				
					for (int d = 0; d < 4; d++) { // 한 방향으로만 봐야 함 
						
						int py = e.y;
						int px = e.x;
						
						while(true) {
							
							py += dy[d];
							px += dx[d];
							
							if(py < 0 || px < 0 || py >= N || px >= M) break;
							if(visit[py][px] ) continue;
							
							// map[py][px] 가 0이 아닌 섬과 만나면 while문 종료 
							if( map[py][px] != 0  && dist-1 > 1) {
								cost[k][ map[py][px] ] = Math.min(cost[k][ map[py][px] ] , dist-1);
							}
						
								q.offer(new Dist(py, px, e.dist + 1));
								visit[py][px] = true;
							
							
						}
						
					}
					
				
				
			
			}
			
			
			
			
			
			
			
		} // k
		
		
		
		
	}
	
	
	
	static void dfs(int y, int x, int num) {
		
		if(visit[y][x]) return;
		visit[y][x] = true;
		map[y][x] = num;

		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || px < 0 || py >= N || px >= M || map[py][px] == 0 || visit[py][px] ) continue;
			
			dfs(py, px, num);
			
		}
		
		
	}
	
	static class Dist {
		int y, x, dist;

		public Dist(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		
	}
	
	

}
