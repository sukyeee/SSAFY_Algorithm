package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_작업순서_1258 {

	static int V, E;
	static int[] indegree; // 진입 차수 관리 배열
	
	static boolean[][] matrix;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			indegree = new int[V+1];
			matrix = new boolean[V+1][V+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				matrix[from][to] = true;
				indegree[to]++;
			}
			sb.append('#').append(t).append(' ');
			// 위상 정렬
			// 진입 차수가 0인 항목을 queue에 넣고 시작
			for (int i = 1; i <= V; i++) {
				if(indegree[i] == 0) queue.offer(i);
			}
			
			while(! queue.isEmpty() ) {
				int v = queue.poll();
				sb.append(v).append(' ');
				for (int i = 1; i <= V; i++) {
					if( matrix[v][i] ) {
						indegree[i]--;
						if( indegree[i] == 0 )queue.offer(i);
					}
				}
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}
