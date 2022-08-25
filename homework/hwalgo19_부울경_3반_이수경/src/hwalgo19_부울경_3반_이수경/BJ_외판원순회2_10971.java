package hwalgo19_부울경_3반_이수경;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_외판원순회2_10971 {
	static int N;
	static int map[][];
	static boolean visit[];
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visit = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완

		for (int i = 0; i < N; i++) {
			Arrays.fill(visit, false);
			dfs(i, i, 0, 0); // 모든 곳에서 시작해봄
		}

		System.out.println(ans);
	}

	static void dfs(int start, int v, int sum, int depth) {
		if (visit[v]) return;
		if (depth == N - 1) {
			// 마지막 노드와 첫번째 노드 연결
			if (map[v][start] == 0) return;
			ans = Math.min(ans, sum + map[v][start]);
			return;
		}
		for (int i = 0; i < N; i++) {

			if (visit[i] || map[v][i] == 0)
				continue;
			// 연결 될 때 방문 표시
			visit[v] = true;
			dfs(start, i, sum + map[v][i], depth + 1);
			visit[v] = false;

		}

	}

}