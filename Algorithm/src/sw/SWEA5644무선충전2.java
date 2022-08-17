package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sw.SW_무선충전_5644.BC;

public class SWEA5644무선충전2 {

    static int T, M, A, ans;
    static int[] pathA, pathB; // 사람 a, b의 이동에 대한 값
    static BC[] bcArray; // 배터리 배열
    
    static int ay, ax, by, bx; // 사람 a, b 의 좌표
    // delta
    // 움직X - 상 - 우 - 하 - 좌
    static int[] dy = { 0, -1, 0, 1, 0 };
    static int[] dx = { 0,  0, 1, 0,-1 };
    static int aPower, bPower;
    
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/sw/SWEA5644무선충전.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			pathA = new int[M];
			pathB = new int[M];
			bcArray = new BC[A];
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				pathA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				pathB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				bcArray[i] = new BC( y, x, c, p);
			}
			// 입력 완
			
			ax = 1; ay = 1;
			bx = 10; by = 10;
			
			// 첫 실행 시 
			simulation();

			for(int i=0;i<M;i++) { //이동 거리 만큼 반복  
				ay += dy[ pathA[i] ];
				ax += dx[ pathA[i] ];
				by += dy[ pathB[i] ];
				bx += dx[ pathB[i] ];
				
				simulation();
				
			}
			
			System.out.println(ans);
			
		} //testacse
		

}

	static void simulation() {
		
		int max = 0;
		for(int i=0;i<A;i++) { // A 에대한 BC
			for(int j=0;j<A;j++) { //B 에 대한 BC
				int sum = 0;
				
				if( Math.abs( ay - bcArray[i].y) + Math.abs( ax - bcArray[i].x) <= bcArray[i].c  )
					aPower = bcArray[i].p;
				else aPower = 0;
				if( Math.abs( by - bcArray[j].y) + Math.abs( bx - bcArray[j].x) <= bcArray[j].c  )
					bPower = bcArray[j].p;
				else bPower = 0;
				
//				aPower = getPower(bcArray[i], ay, ax);
//				bPower = getPower(bcArray[j], by, bx);
				   
				if( aPower == 0 && bPower == 0 ) continue;
				if( i != j) {
					sum = aPower + bPower;
				} else { // 같은 BC를 쓰고 있다면 
					sum = Math.max(aPower, bPower);
				}
				max = Math.max(max, sum);

			}
			
		}
		ans += max;
		// BC의 C 범위에 포함된다면
		
		
	}
	
	static int getPower(BC bc, int y, int x) {
	    if( Math.abs(bc.y - y) + Math.abs(bc.x - x) <= bc.c ) return bc.p; 
	    return 0;
	}
	
	static class BC {
		int x, y, c, p;

		public BC(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
		
		
	}
}
