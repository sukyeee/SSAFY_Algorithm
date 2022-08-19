package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 리스트
public class BJ_DFS와BFS_1260 {

	static int N, M, V;
	static boolean[][] matrix;
	static boolean[] visit;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder ans = new StringBuilder();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken()); // 방문 시작 정점
		
		matrix = new boolean[N+1][N+1]; // 0 dummy
		visit = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			matrix[n1][n2] = true;
			matrix[n2][n1] = true;
			
		}
		
		// dfs
		visit[V] = true;
		dfs(V);
		
		// 맨 뒤 문자열을 개행 문자로 변경
		ans.setCharAt(ans.length()-1, '\n');
		
		// visit 초기화
		for (int i = 1; i <= N; i++) {
			visit[i] = false;
		}
		// bfs
		bfs();
		
		// 마지막 문자 제거
		ans.setLength(ans.length() - 1);
		System.out.println(ans);
	}
	
	static void dfs(int num) {
		ans.append(num).append(" ");
		
		// num 에서 갈 수 있는 다른 정점을방문
		for (int i = 1; i <= N; i++) { // 1 ~ N 의 정점중
			if( !matrix[num][i] || visit[i] ) continue;
			visit[i] = true;
			dfs(i);
		}
	}
	
	static void bfs() {
		visit[V] = true;
		queue.offer(V);
		
		while( !queue.isEmpty() ) {
			int num = queue.poll();
			ans.append(num).append(" ");
			
			for (int i = 1; i <= N; i++) {
				if( !matrix[num][i] || visit[i] ) continue;
				visit[i] = true;
				queue.offer(i);
			}
		}
		
	}

}







