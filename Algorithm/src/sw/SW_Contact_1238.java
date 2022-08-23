package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_Contact_1238 {

	static int L, S, maxNum, maxDepth;
	static boolean[][] matrix = new boolean[101][101]; // 0 dummy (visit 로 활용)
	static Queue<Vertex> queue = new ArrayDeque<>();
	// bfs의 visit matrix[x][0] == visit[x]
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			
			// 초기화
			for (int i = 0; i <= 100; i++) {
				for (int j = 0; j <= 100; j++) {
					matrix[i][j] = false;
				}
			}
			
			maxNum = Integer.MIN_VALUE;
			maxDepth = Integer.MIN_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken()); // 입력 길이
			S = Integer.parseInt(st.nextToken()); // 시작 숫자
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < L/2; i++) {
				matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			bfs();
			System.out.println("#" + t + " " + maxNum);
		}
		
		
	}

	static void bfs() {
		// S부터 시작
		matrix[S][0] = true;
		queue.offer(new Vertex(S, 0));
		
		while( !queue.isEmpty() ) {
			Vertex vertex = queue.poll();
			
			// 최대 depth, 최대 번호 check
			if( vertex.d > maxDepth ) {
				maxDepth = vertex.d;
				maxNum = vertex.n;
			} else if ( vertex.d == maxDepth ) {
				maxNum = Math.max(maxNum, vertex.n);
			}
			
			for (int i = 1; i <= 100; i++) {
				// vertex.n 정점에서 갈 수 있는 지 i ~ 100 번까지 모든 정점을 고려
				// i 정점 visit 체크
				if( ! matrix[vertex.n][i] || matrix[i][0] ) continue;
				matrix[i][0] = true;
				queue.offer(new Vertex(i, vertex.d + 1));
			}
		}
		
	}
	
	// 정점
	static class Vertex{
		int n; // 정점 번호
		int d; // depth
		
		public Vertex(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}
