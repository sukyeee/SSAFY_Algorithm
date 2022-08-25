package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_작업순서_1258_2 {

	static int V, E;
	static int[] indegree; // 진입 차수 관리 배열
	
	static boolean[][] matrix;
	static boolean[] visit;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			matrix = new boolean[V+1][V+1];
			visit = new boolean[V+1];
					
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				matrix[from][to] = true;
				
			}
			sb.append('#').append(t).append(' ');
			// 위상 정렬
			
			for (int i = 1; i <= V; i++) { // 모든 정점에서 dfs() 시도
				// 이미 방문한 선행된 정점이 있으면 skip
				if(visit[i]) continue;
				dfs(i);
			}
			
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int v) {
		visit[v] = true;
		
		// 시작하는 정점에서 나로 들어오는 애들을 따져야 함
		// v로 들어오는 정점. matrix[v][i] 가 아니라 matrix[i][v] 를 따져야함
		for (int i = 1; i <= V; i++) {
			if( matrix[i][v] && !visit[i] ) dfs(i); 
		}
		
		sb.append(v).append(' ');
		
	}
	

}
