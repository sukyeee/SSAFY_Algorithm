package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// next-permutation
public class BJ17406배열돌리기4_2 {

	static int[][] map, backup, rcs;
	static int N, M, K, min;

	static int[] npIdx;
//	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		backup = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				backup[i][j] = n;
			}

		}

		// 초기화
		min = Integer.MAX_VALUE;
		rcs = new int[K][3];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rcs[i][0] = Integer.parseInt(st.nextToken());
			rcs[i][1] = Integer.parseInt(st.nextToken());
			rcs[i][2] = Integer.parseInt(st.nextToken());
		}

		// 순열
		npIdx = new int[K];
//        select = new boolean[K];
		for (int i = 0; i < K; i++) { // 앞쪽이 작은수 뒤가 가장 큰수 // 정렬됨
			npIdx[i] = i;
		}

		while (true) {

			// rotate
			for (int n : npIdx) {

				int r = rcs[n][0] - 1; // 0 base 보정
				int c = rcs[n][1] - 1; // 0 base ㅂ보정
				int s = rcs[n][2]; // 반복 횟수가 됨.

				int sy = r - s;
				int ey = r + s;
				int sx = c - s;
				int ex = c + s;

				while (s > 0) {

					int temp = map[sy][sx]; // 백업 (왼쪽 위)
					// 이동 방향의 앞쪽부터 이동해야 함 <- 1 <- 2 <- 3 <- 4
					// 뒸쪽 부터 이동하면 계속 덮어씀 <- 4 < -3 <- 2 <- 1

					// 왼쪽 위로 1칸 이동
					for (int i = sy; i < ey; i++) {
						map[i][sx] = map[i + 1][sx];
					}

					// 아래 왼쪽 1칸 이동
					for (int i = sx; i < ex; i++) {
						map[ey][i] = map[ey][i + 1];
					}

					// 오른쪽 아래로 1칸 이동
					for (int i = ey; i > sy; i--) {
						map[i][ex] = map[i - 1][ex];
					}

					// 위 오른쪽으로 한칸 이동
					for (int i = ex; i > sx; i--) {
						map[sy][i] = map[sy][i - 1];
					}

					// temp 로부터 이동 마지막 부분 복사( 왼쪽 위의 한칸 오른쪽 )
					map[sy][sx + 1] = temp;

					sy += 1; // 시작 y는 하나 아래로
					sx += 1; // 시작 x는 하나 오른쪽으로
					ey -= 1; // 종료 y는 하나 위
					ex -= 1; // 종료 x는 하나 왼쪽으로

					s--;
				}
			}

			// 최소값 갱신

			// 배열 초기화

			// np() 다음 것 호출

			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += map[i][j];
				}
				min = Math.min(min, sum);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = backup[i][j];
				}
			}

			if (!np(npIdx))
				break;
		}

		System.out.println(min);
	}

	private static boolean np(int array[]) {

		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			--i;

		if (i == 0)
			return false;

		int j = array.length - 1;
		while (array[i - 1] >= array[j])
			--j;
		swap(array, i - 1, j);

		// reverse
		int k = array.length - 1;
		while (i < k) {
			swap(array, i++, k--);
		}
		return true;
	}

	private static void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;

	}
}
