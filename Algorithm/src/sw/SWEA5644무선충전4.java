package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5644무선충전4 {

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
			
			int user1_y = 0;
			int user1_x = 0;
			int user2_y = 9;
			int user2_x = 9;
			check(user1_y, user1_x, user2_y, user2_x  );

			for (int i = 0; i < M; i++) {
				
				user1_y += dy[user[0][i]];
				user1_x += dx[user[0][i]];
				
				user2_y += dy[user[1][i]];
				user2_x += dx[user[1][i]];
				
				check(user1_y, user1_x, user2_y, user2_x  );
				
			}
			
			
		 System.out.println("#" + t + " " + ans);
		} // testcase
		
		
	}
	
	static void check(int user1_y, int user1_x, int user2_y, int user2_x) {
		
		int max = 0;
		for (int i = 0; i < BC.length; i++) { // user1번의 충전소 
			for (int j = 0; j < BC.length; j++) { // user2번의 충전소 
				
				int powerA = getDistance(BC[i], user1_y, user1_x );
				int powerB = getDistance(BC[j], user2_y, user2_x );
				
				int sum = 0;
				if( i == j ) {
					sum = Math.max(powerA, powerB);
				}
				else {
					sum = powerA + powerB;
				}
				
				max = Math.max(max, sum);
				
			}
		}
		
		ans += max;
	}
	static int getDistance(Dist BC, int y, int x) {
		if( Math.abs(BC.y - y) + Math.abs(BC.x - x) <= BC.c) {
			return BC.p;
		}
		else return 0;
	}
	static class Dist {
		int y, x, c, p;

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