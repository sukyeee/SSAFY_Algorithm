package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_Z_1074_dfs {

	static int N, r, c, ans;
	static int y, x;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		// N 은 2의 제곱수이므로 N을 실제 좌, 우에 해당하는 길이로 보정
		N = (int) Math.pow(2, N); // N 은 2로 나눌 수 있다.

		dfs(0, 0); // x, y, ans

		System.out.println(ans);

	}

	static void dfs(int x, int y) {

		// 가로, 세로를 반으로 나누어서 4공간으로 분할
		N /= 2;

		if (N < 1)
			return;
		
		// r, c가 4공간 중 어느 곳에 해당하는 지 판단, 각 공간에서 r, c 값에 따라 ans 값 갱신, 원점 보정
		if (r < y + N && c < x + N) { // top-left
			ans += N*N*0;
			dfs(x, y);
		} else if (r < y + N && c >= x + N) { // top-right
			ans += N*N*1;
			dfs(x+N, y);
		} else if (r >= y + N && c < x + N) {
			ans += N*N*2;
			dfs(x, y+N);
		} else if (r >= y + N && c >= x + N) {
			ans += N*N*3;
			dfs(x+N, y+N);
		}

	}

}
