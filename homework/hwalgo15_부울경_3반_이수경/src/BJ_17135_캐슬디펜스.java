
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17135_캐슬디펜스 {

	static int N, M, D;
	static int map[][];
	static int tgt[] = new int[3];
	static List<int[]> enemy = new ArrayList<>();
	static int cnt;
	static int max;
	static List<int[]> dead = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완

		// 1. 궁수 3명 배치
		comb(0, 0);

		System.out.println(max);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) { // 궁수 3명 배치 완료
			attack();
			return;
		}

		if (srcIdx == M)
			return;

		tgt[tgtIdx] = srcIdx; // 궁수가 몇번째 열에 있는지 저장
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);

	}

	static void attack() {

		// 초기화
		cnt = 0;
		int temp[][] = new int[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				temp[i][j] = map[i][j]; // 임시 배열 복사

		while (true) {

			enemy.clear();
			dead.clear();

			// -------------------------------------------------------------
			// 적군이 한 명도 없을 때 까지 반복
			int enemy_cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (temp[i][j] == 1)
						enemy_cnt++;
			if (enemy_cnt == 0) {
				max = Math.max(max, cnt);
				break;
			}

			// 2. D 거리 안에 들어오는 공격 대상 임시 설정
			for (int k = 0; k < tgt.length; k++) { // 궁수를 기준으로
				boolean flag = false;
				enemy.clear();

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (temp[i][j] == 1 && position(i, N, j, tgt[k]) <= D) {
							enemy.add(new int[] { i, j, position(i, N, j, tgt[k]) }); // D 거리 이내 모든 적 좌표 저장
						}
					}
				}
				// 궁수 한명 당 enemy 설정 완료

				// 적 제거
				// 좌표 가장 가까운 순서대로 오름차순 정렬
				Collections.sort(enemy, (a, b) -> a[2] - b[2]); // 거리 가장 가까운 순서 정렬
				Collections.sort(enemy, (a, b) -> a[2] == b[2] ? a[1] - b[1] : a[2] - b[2]); // 거리 같으면 제일 왼쪽에 있는 순서대로 정렬

				// 적은 맨 앞에 있는 (왼쪽, 가장 가까운 적) 한 명만 제거 목록에 추가
				if (enemy.size() > 0)
					dead.add(new int[] { enemy.get(0)[0], enemy.get(0)[1] }); // 맨 앞 하나만 죽일 적 dead리스트에 넣어두기 (나중에 한꺼번에
																				// 죽이기)

			}
			// -------------------------------------------------------------
			// 3. 제거 목록에 있는 적들 한꺼번에 처리
			for (int k = 0; k < dead.size(); k++) {
				if (temp[dead.get(k)[0]][dead.get(k)[1]] == 1) {
					temp[dead.get(k)[0]][dead.get(k)[1]] = 0;
					cnt++;
				}

			}

			// -------------------------------------------------------------
			// 4. 적 아래로 한칸씩 이동
			for (int i = N - 1; i > 0; i--) {
				for (int j = 0; j < M; j++) {
					temp[i][j] = temp[i - 1][j];
				}
			}
			for (int j = 0; j < M; j++) temp[0][j] = 0; // 맨 윗줄은 0으로 초기화 

		}

	}

	static int position(int y1, int y2, int x1, int x2) {
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}

}
