package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ7576토마토 {

	static int M, N;
	static int tomato[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dx[] = { 0, 0, -1, 1 };
	static int cnt;
	static int ans = -1;
	static Deque<Dist> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tomato = new int[M][N];
		visit = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완 

		// 저장될 때부터 모든 토마토가 익어있는 상태이면 0 출력
		chk();
		if (cnt == 0) {
			System.out.println(0);
			System.exit(0);
		}

		bfs();

		chk();
		if (cnt != 0)
			ans = -1;

		System.out.println(ans);

	}

	static void chk() {
		cnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (tomato[i][j] == 0)
					cnt++;
			}
		}
	}

	static void bfs() {

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (tomato[i][j] == 1)
					q.add(new Dist(i, j));
			}
		}

		while (!q.isEmpty()) {

			int q_size = q.size();
			ans++;
			for (int i = 0; i < q_size; i++) {

				Dist top = q.poll();
				tomato[top.y][top.x] = 1;

				for (int d = 0; d < 4; d++) {
					int py = top.y + dy[d];
					int px = top.x + dx[d];

					if (py < 0 || py >= M || px < 0 || px >= N || tomato[py][px] != 0)
						continue;

					if (visit[py][px])
						continue;
					q.add(new Dist(py, px));
					visit[py][px] = true;

				}

			}

		}

	}

	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
