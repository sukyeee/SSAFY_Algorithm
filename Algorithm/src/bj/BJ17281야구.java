package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17281야구 {

	static int N;
	static int inning[][];
	static int tgt[]; // 타자가 타석에 서는 순서
	static int src[];
	static boolean select[];
	static int cnt, sum, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		inning = new int[N][2];
		tgt = new int[9];
		select = new boolean[9];
		src = new int[9];

		for (int t = 0; t < N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 9; j++)
					inning[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < 9; i++)
				src[i] = i;
			tgt[3] = 0;
			select[3] = true;

			perm(0);

		} // N 횟수 (이닝 수) 만큼 반복

	}

	static void search() {

		sum = 0;
		int out = 0;

		while (true) {

			for (int n = 0; n < N; n++) { // 이닝 횟수 만큼 반복
				for (int i = 0; i < 9; i++) {

					switch (inning[n][tgt[i]]) {
					case 0: // 아웃
						out++;
						break;
					case 1: // 안타
						sum += 1;
						break;
					case 2: // 2루타
						sum += 2;
						break;
					case 3: // 3루타
						sum += 3;
						break;
					case 4: // 홈런
						ans++;
						break;
					}

					if (sum >= 4) {
						sum -= 4;
						ans++;
					}

					if (out == 3) {
						break;
					}

				}
			}

		}

	}

	static void perm(int tgtIdx) {

		if (tgtIdx == tgt.length) {
			System.out.print(Arrays.toString(tgt));
			System.out.println();

			search();

			return;
		}

		for (int i = 0; i < 9; i++) {

			if (select[i])
				continue;
			if (tgtIdx != 3) {
				tgt[tgtIdx] = src[i];
				select[i] = true;
			}

			perm(tgtIdx + 1);
			if (tgtIdx != 3)
				select[i] = false;
		}

	}

}

/*
 * 
 * 2 4 0 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 AZ
 */