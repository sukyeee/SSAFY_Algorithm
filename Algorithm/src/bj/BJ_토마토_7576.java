package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_토마토_7576 {

	static int M, N, max;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dx[] = { 0, 0, -1, 1 };
	static int cnt;
	static int ans = -1;
	static Deque<Node> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visit = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				// 익은 토마토를 input 처리하면서 queue 에 담는다.
				if( n == 1 ) queue.offer(new Node(i, j, 0)); // 처음에 익은 토마토는 0일째
			}
		}
		// 입력 완 

		// bfs
		while( ! queue.isEmpty() ) {
			Node n = queue.poll();
			int y = n.y;
			int x = n.x;
			
			// 꺼내면 일수 체크 max 로 대체
			max = Math.max(max, n.d);
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if( ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if( map[ny][nx] == 0) { // 익지 않은 토마토
					map[ny][nx] = 1; // 익은 토마토 
					queue.offer(new Node(ny, nx, n.d + 1));
				}
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if( map[i][j] == 0 ) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(max);

	}

	
	static class Node {
		int y, x, d;
		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}


}
