package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW낚시터자리잡기2 {

	static int T, N;
	static Item gate[] = new Item[4];
	static int tgt[] = new int[3];
	static boolean select[] = new boolean[3];
	static int seats[];
	static boolean selected_seat[];
	static int min;
	static int sum;
	static int ans;
	static int dx[] = { 0, -1, 1 }; // 해당 위치, 좌 우
	static int fisherCnt;
	static int distance;
	static int seatsCopy[];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/sw/SW낚시터자리잡기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			ans = Integer.MAX_VALUE;
			sum = 0;
			N = Integer.parseInt(br.readLine());
			seats = new int[N + 1];
			seatsCopy = new int[N + 1];
			select = new boolean[4];
			for (int i = 1; i <= 3; i++) { // 게이트는 항상 3
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				gate[i] = new Item(a, b);
			}
			// 입력 완

			perm(0);

			System.out.println("#" + t + " " + min);

		} // testcase
	}

	static void place() { // 누적 거리 합, 현재 사용중인 게이트 번호

		init(); // seat 초기화
		distance = 0;
		sum = 0;

		for (int id : tgt) { // tgt : 게이트의 순열

			// 해당 게이트 번호 ( id ) 에 대하여, 낚시꾼들의 위치를 지정해서 최소 거리 구하기.

			int fisherman = gate[id].person; // 낚시꾼 수
			int gatePosition = gate[id].position; // 게이트 위치
			int diff = 0; // 간격
			sum = 0;
			while (true) {
				if (fisherman == 0)
					break;

				// 왼쪽으로 배치
				int left = gatePosition - diff;
				if (left > 0 && seats[left] == 0) { // 자리가 비었을 때만
					seats[left] = id;
					sum += Math.abs(gatePosition - left) + 1;
					fisherman--;
				}

				if (fisherman == 0)
					break;

				// 오른쪽으로 배치
				int right = gatePosition + diff;
				if (right <= N && seats[right] == 0) { // 자리가 비었을 때만
					seats[right] = id;
					sum += Math.abs(gatePosition - right) + 1;
					fisherman--;
				}

				diff++; // 간격은 1씩 증가

			}

			// 만약 게이트에 줄 선 사람이 짝수라면, 오른쪽먼저 보는 경우도 봐줘야 함.
			int right_sum = Integer.MAX_VALUE;
			if (gate[id].person % 2 == 0) {
				// 기존 seat배열 복사
				seatsCopy = Arrays.copyOf(seats, N + 1);
				idInit(id);

				fisherman = gate[id].person; // 낚시꾼 수
				diff = 0; // 간격
				right_sum = 0;
				while (true) {

					if (fisherman == 0)
						break;

					// 오른쪽으로 배치
					int right = gatePosition + diff;
					if (right <= N && seatsCopy[right] == 0) { // 자리가 비었을 때만
						seatsCopy[right] = id;
						right_sum += Math.abs(gatePosition - right) + 1;
						fisherman--;
					}

					if (fisherman == 0)
						break;

					// 왼쪽으로 배치
					int left = gatePosition - diff;
					if (left > 0 && seatsCopy[left] == 0) { // 자리가 비었을 때만
						seatsCopy[left] = id;
						right_sum += Math.abs(gatePosition - left) + 1;
						fisherman--;
					}

					diff++; // 간격은 1씩 증가

				}

				// 오른쪽 부터 봐주는 것이 더 최소인지도 봐줘야 함
				if (sum > right_sum) {
					sum = right_sum;
				} else {
					// seat 배열 원래대로 되돌리기
					seats = Arrays.copyOf(seatsCopy, N + 1);
				}

			}

			distance += sum;

		} // id

	}

	static void idInit(int id) {
		// 해당 게이트 id 가진 seat 초기화
		for (int i = 1; i <= N; i++) {
			if (seatsCopy[i] == id)
				seatsCopy[i] = 0;
		}
	}

	static void init() {
		for (int i = 1; i <= N; i++) {
			seats[i] = 0;
		}
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			// 게이트 번호 순서가 정해지면, 해당 게이트 넘버 순서대로 낚시꾼 배치
			place();
			min = Math.min(min, distance);

			return;
		}

		for (int i = 0; i < 3; i++) {
			if (select[i])
				continue;

			tgt[tgtIdx] = i + 1;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

	static class Item {
		int position; // 게이트의 위치
		int person; // 통과하는 사람의 수

		public Item(int position, int person) {
			super();
			this.position = position;
			this.person = person;
		}

	}

}
