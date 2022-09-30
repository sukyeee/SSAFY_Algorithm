package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5644무선충전3 {

	static int T, M, A;
	static int user[][];
	static Dist BC[];
	static int X, Y, C, P;
	static int dy[] = { 0, -1, 0, 1, 0 };
	static int dx[] = { 0,  0, 1, 0,-1 };
	static int map[][] = new int[10][10];
	static int ans;
	static int sum;
	static int max;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수 만큼 충전 양 균등 분배 
		// 모든 사용자가 충전한 양의 합의 최댓값 ?
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			ans = 0;
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			user = new int[2][M];
			BC = new Dist[A];
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < M ; i++) {
					user[j][i] = Integer.parseInt(st.nextToken());
				}	
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken())-1;
				Y = Integer.parseInt(st.nextToken())-1;
				C = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());	
				
				BC[i] = new Dist(Y, X, C, P);
			}
			// 입력 완 
			
			
			// 첫번쩨 user
			int uy = 0;
			int ux = 0;
			// 두번째 user 
			int vy = 9;
			int vx = 9;
			check(uy, ux, vy, vx);

			
			// user의 이동. user1은 0,0 시작  user2는 N-1 N-1 시작  
			for (int i = 0; i < M; i++) { // i 가 "초" 를 의미 
				
				// 상1 우2 하3 좌4 
				uy += dy[user[0][i]];
				ux += dx[user[0][i]];
				
				vy += dy[user[1][i]];
				vx += dx[user[1][i]];
				
				// 충전소에 속하는 경우 계산 
				check(uy, ux, vy, vx);
				
			}
			
			
	
		System.out.println("#" + t + " " + ans);	
			
		} // testacase
		
		
	}
	
	
	
	static void check(int uy, int ux, int vy, int vx) {
		
		int max = 0;
		// user1가 선택한 BC
		for (int i = 0; i < BC.length; i++) {
			// user2가 선택한 BC
			for (int j = 0; j < BC.length; j++) {
				int sum = 0;
				int amountA = coverage(uy, ux, i);
				int amountB = coverage(vy, vx, j);
				
					// 충전소가 같을 경우
					if( i == j ) {
						sum = Math.max(amountA, amountB);
					}
					// 충전소가 다를 경우
					else if ( i != j ) {
						sum = amountA + amountB;
					
					}
					
				
					if( max < sum ) max = sum;
				
				
			}
		}
		ans += max;
		
	}
	

	static int coverage(int user_y, int user_x, int idx) {
		int dist =  Math.abs(user_y - BC[idx].y) + Math.abs(user_x - BC[idx].x); 
		if( dist <= BC[idx].c) return BC[idx].p;
		return 0;
		 
	}
	
	static class Dist {
		int y, x;
		int c; // 충전 범위  
		int p; // 처리량

		public Dist(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
		
	}
	
}

/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
40 2
0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4 
0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1 
5 2 4 140
8 3 3 490
60 4
0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4 
1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1 
6 9 1 180
9 3 4 260
1 4 1 500
1 3 1 230
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
100 8
2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1 
4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4 
3 4 2 340
10 1 1 430
3 10 4 10
6 3 4 400
7 4 1 80
4 5 1 420
4 1 2 350
8 4 4 300
*/