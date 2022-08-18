package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_빵집_3109 {

	static int R, C, ans;
	static char[][] map;
	static int[] dy = { -1, 0, 1 }; // 우선순위 존재 - 상우->우->하우, dx는 필요 X

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 풀이
		// row 로 밑으로 내려가면서
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0))
				ans++; // boolean return
		}

		System.out.println(ans);
	}

	static boolean dfs(int y, int x) {
		// 다음 방문 좌표
		int nx = x + 1; // 오른쪽으로 한칸 이동
		if (nx == C - 1)
			return true; // 성공

		for (int d = 0; d < 3; d++) {
			int ny = y + dy[d];
			if (ny < 0 || ny >= R || map[ny][nx] == 'x')
				continue;

			// 갈 수 있는 좌표이면 방문 처리 후 방문
			map[ny][nx] = 'x'; // 건물로 방문처리
			if (dfs(ny, nx))
				return true;
		}

		return false;
	}
}