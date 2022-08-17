package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5644무선충전 {

	static int T, M, A;
	static int a[]; // A의 이동 정보
	static int b[]; // B의 이동 정보
	static int AP_x, AP_y, C, P;
	static List<Item> info = new ArrayList<>();
	static int dy[] = { 0, -1, 0, -1, 0 }; // - 상 우 하 좌
	static int dx[] = { 0, 0, 1, 0, -1 };
	static int map[][] = new int[11][11];
	static int sum;
	static int Max;
	static int Ax = 1;
	static int Ay = 1;
	static int Bx = 10;
	static int By = 10;
	static int aPower = 0;
	static int bPower = 0;
	static int ans = 0;
	static List<int[]> g = new ArrayList<>(); // 겹치는 부분 리스트

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/sw/SWEA5644무선충전.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			a = new int[M];
			b = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				AP_x = Integer.parseInt(st.nextToken());
				AP_y = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());

				info.add(new Item(AP_x, AP_y, C, P));
			}
			// 입력 완

			Ax = 1;
			Ay = 1;
			Bx = 10;
			By = 10;
			aPower = 0;
			bPower = 0;
			Max = 0;
			sum = 0;
			
			// 처음 시작할 때 한 번 실행
			simulation();
			
			for (int i = 0; i < M; i++) {

				Ax += dx[a[i]];
				Ay += dy[a[i]];
				Bx += dx[b[i]];
				By += dy[b[i]];
				
				
				simulation();
				
				
			}
			System.out.println("#" + t + " " + Max);
		} // testcase

	}

	static void simulation() {
		// BC는 2개 까지만 겹칠 수 있음
		for (int j = 0; j < A; j++) {
			
			// BC의 범위 내에 있다면, aPower에 해당 BC의 P 할당
			if(Math.abs(Ax - info.get(j).x) + Math.abs(Ay - info.get(j).y) <= info.get(j).C) {
				aPower = info.get(j).P;
			}
			if(Math.abs(Bx - info.get(j).x) + Math.abs(By - info.get(j).y) <= info.get(j).C) {
				bPower = info.get(j).P;
			}
		
			if(aPower == 0 && bPower == 0) continue;
			if(aPower != bPower) { // 다른 BC를 쓰고 있다면
				sum = aPower + bPower;
			} else { // 같은 BC를 쓰고 있다면
				sum = Math.max(aPower, bPower);
			}
			Max = Math.max(sum, Max);

		}
		
		ans += Max;
	}
	
	static class Item {

		int x;
		int y;
		int C;
		int P;

		public Item(int aP_x, int aP_y, int c, int p) {
			super();
			AP_x = aP_x;
			AP_y = aP_y;
			C = c;
			P = p;
		}

	}

}
