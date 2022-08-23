package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA3124최소스패닝트리_PRIM {

	static int T, V, E;
	static int A, B, C;
	// 현재 고려 대상인 정점(간선) 중 비용이 가장 작은 것을 리턴
	static PriorityQueue<Edge> pq = new PriorityQueue<>( (e1, e2) -> e1.c - e2.c);
	static List<Edge> node[];
	static long sum;
	static boolean visit[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			visit = new boolean[V+1]; // 0 dummy
			node = new ArrayList[V+1];
			for(int i=1;i<=V;i++) node[i] = new ArrayList<>();
			pq.clear();

			
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				node[A].add(new Edge(B,C));
				node[B].add(new Edge(A,C));
				
			}
			
			
			sum = 0;
			int cnt = 0;
			visit[1] = true;
			
			pq.addAll(node[1]); // 1에서 갈 수 있는 모든 노드 추가 
			
			while(!pq.isEmpty()) {
				if( cnt == V-1 )break;
				
				Edge e = pq.poll(); // 현재 pq에서 가장 가중치 작은 정점 꺼내기 
				
				if( visit[e.v] ) continue;
				visit[e.v] = true;
				sum += e.c;
				cnt++;
				
				pq.addAll(node[e.v]);
				
			}
			
			
			System.out.println("#"+t+ " " + sum);
		} //testcase
	
	}
	
	
	static class Edge {
		
		int v; // 도착점 
		int c;  // 가중치 
		public Edge(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}	
	}
	
	
}
	

/*
 
1
3 3
1 2 1
2 3 2
1 3 3
  
 */