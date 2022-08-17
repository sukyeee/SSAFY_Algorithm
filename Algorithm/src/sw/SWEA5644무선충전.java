package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sw.SW_무선충전_5644.BC;

public class SWEA5644무선충전 {

	static int T, M, A;
	static int a[]; // A의 이동 정보
	static int b[]; // B의 이동 정보
	static List<Item> info = new ArrayList<>();
	static int dy[] = { 0, -1, 0, 1, 0 }; // - 상 우 하 좌
	static int dx[] = { 0, 0, 1, 0, -1 };
	static int map[][] = new int[11][11];
	static int sum, max;
	static int Ax, Ay, Bx, By;
	static int aPower, bPower;
	static int ans = 0;
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
			info.clear();
			
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
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				info.add(new Item(y, x, c, p));
			}
			// 입력 완

			Ax = 1; Ay = 1;
			Bx = 10; By = 10;
			aPower = 0;	bPower = 0;
			max = 0;
			sum = 0;
			ans = 0;
			
			// 처음 시작할 때 한 번 실행
			simulation();
			
			for (int i = 0; i < M; i++) {

				Ax += dx[a[i]];
				Ay += dy[a[i]];
				Bx += dx[b[i]];
				By += dy[b[i]];
				
				simulation();
				
				
			}
			System.out.println("#" + t + " " + ans);
		} // testcase

	}

//	if(Math.abs(Ax - info.get(i).x) + Math.abs(Ay - info.get(i).y) <= info.get(i).C) {
//		aPower = info.get(i).P;
//	}
//	if(Math.abs(Bx - info.get(j).x) + Math.abs(By - info.get(j).y) <= info.get(j).C) {
//		bPower = info.get(j).P;
//	}	
	static void simulation() {
		// BC는 2개 까지만 겹칠 수 있음
		
		max = 0;
		for (int i = 0; i < A; i++) { // 모든 BC 에 대해서 A 가  충전하려는 BC
	        // i == j => A, B 가 같은 BC 에서 충전함
	        for (int j = 0; j < A; j++) { // 모든 BC 에 대해서 B 가 충전하려는 BC
	            int sum = 0; // A 충전량 + B 충전량
	            
	            
	            int aPower = getPower(info.get(i), Ay, Ax); // bcArray[i]
	            int bPower = getPower(info.get(j), By, Bx);// bcArray[j]

	            // 0 이면 거르자
	            if( aPower == 0 && bPower == 0 ) continue;
	            
	            // 둘 중 하나는 power 를 가지고 있다.
	            // 두 충전소가 다르다
	            if( i != j ) {
	                sum = aPower + bPower; // 각각 다른 충전소에서 충전 => 단순히 더한다. (한쪽이 0 이어도 상관 X)
	            }else { // 두 충전소가 같은 겨우
	                // a, b 모두 충전이 되었다면  100 충전 => 50, 50 => 100
	                // a, b 한쪽만 충전 ? 충전한 값을 선택
	                sum = Math.max(aPower, bPower);
	            }
	            max = Math.max(max, sum); // 현재 i, j 의 BC 에서 충전한 전기량이 이전 BC 최대값보다 크면 선택
	        }
	    }
		
		
		ans += max;
	}
	static int getPower(Item bc, int y, int x) {
	    if( Math.abs(bc.y - y) + Math.abs(bc.x - x) <= bc.c ) return bc.p; 
	    return 0;
	}

	static class Item {

		int y;
		int x;
		int c;
		int p;

		public Item(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

	}

}
