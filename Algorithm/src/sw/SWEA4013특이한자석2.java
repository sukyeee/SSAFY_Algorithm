package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4013특이한자석2 {

	static int T, K;
	static int magnet[][] = new int[4][8];
	static int direction[] = new int[4];
	static int ans;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			
			K = Integer.parseInt(br.readLine()); // 자석을 회전 시키는 횟수 
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for (int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken()) - 1; // 회전시키는 자석 번호 
				int d = Integer.parseInt(st.nextToken()); // 회전시키는 방향
				
				// 2번과 6번 자석 비교, 방향 지정 
				setDirection(id, d);
				
				// 자석 돌리기
				rotate();
			}
			
			
			if(magnet[0][0] == 1) ans += 1;
			if(magnet[1][0] == 1) ans += 2;
			if(magnet[2][0] == 1) ans += 4;
			if(magnet[3][0] == 1) ans += 8;
			
			
			
			
			System.out.println("#" + t + " " + ans);
		} // testcase
		
	
	}
	static void rotate() {
		
		for (int i = 0; i < 4; i++) {
			switch(direction[i]) {
			case 1: // 시계 방향으로 회전 
				
				int end = magnet[i][7];
				for (int j = 7; j > 0; j--) {
					magnet[i][j] = magnet[i][j-1];
				}
				magnet[i][0] = end;
				
				
			break;
			
			case -1: // 반시계 방향으로 회전 
				
				int start = magnet[i][0];
				for (int j = 1; j < 8; j++) {
					magnet[i][j-1] = magnet[i][j];
				}
				
				magnet[i][7] = start;
			break;
			
			}
				
		}
		
	}
	static void setDirection(int id, int d) {
		// 시계방향 1, 반시계 방향 -1
		// 방향이 같다면 회전하지 않음 
		
		// 입력받은 자석 id 기준으로 방향 결정 
		
		direction = new int[4];
		direction[id] = d;
		
		
		// 현재 자석 기준 왼쪽 자석 회전 방향 
		for (int i = id; i > 0; i--) {
			if( magnet[i][6] != magnet[i-1][2] ) {
				direction[i-1] = direction[i] * (-1);
			}
		}
		
		// 현재 자석 기준 오른쪽 자석 회전 방향
		for (int i = id; i < 4-1 ; i++) {
			if( magnet[i][2] != magnet[i+1][6] ) {
				direction[i+1] = direction[i] * (-1);
			}
		}
		
		
	}

}
/*
10
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1
2
1 0 0 1 0 0 0 0
0 1 1 1 1 1 1 1
0 1 0 1 0 0 1 0
0 1 0 0 1 1 0 1
3 1
1 1
5
0 0 1 1 1 1 1 1
1 1 1 1 1 0 1 0
0 0 0 0 1 0 0 1
0 1 0 1 0 1 0 1
4 -1
3 1
4 -1
3 -1
1 -1
2
1 0 1 0 0 1 0 1
0 0 1 0 1 1 1 1
0 0 1 1 0 0 0 1
0 1 0 1 1 0 0 0
2 -1
1 1
7
0 0 1 1 0 1 1 1
0 1 0 1 1 0 0 0
1 1 1 0 0 0 0 1
1 1 1 0 0 1 0 0
4 1
2 1
2 -1
3 1
2 1
4 1
2 -1
10
1 0 0 0 0 0 0 1
1 0 1 0 1 1 0 1
1 0 0 1 0 0 0 1
1 1 0 1 0 1 1 1
2 1
1 1
2 -1
3 1
3 -1
2 -1
2 -1
1 1
4 1
4 1
10
0 1 0 0 1 1 0 0
0 1 1 0 1 0 1 1
0 0 0 0 0 1 1 0
0 0 1 0 1 0 1 1
3 1
1 -1
2 1
4 -1
3 1
3 -1
4 -1
2 -1
1 -1
3 -1
10
0 1 0 1 0 1 0 0
0 1 1 1 1 1 0 1
1 0 0 0 0 1 1 0
1 0 0 0 0 0 0 1
1 1
4 -1
4 -1
2 -1
2 -1
2 -1
3 -1
2 1
3 1
3 -1
20
1 0 0 0 1 1 0 0
1 0 0 1 1 1 0 0
0 1 1 1 0 1 1 1
1 1 1 1 0 1 1 1
1 1
4 -1
4 -1
2 -1
3 -1
1 1
4 1
4 -1
4 -1
4 -1
3 -1
3 -1
4 -1
4 -1
2 -1
1 1
3 -1
3 -1
2 1
1 1
20
0 0 1 1 1 0 1 0
0 1 0 0 1 0 1 0
1 1 1 0 1 0 1 0
0 0 1 0 0 1 1 1
1 -1
4 -1
3 -1
1 1
4 1
2 1
1 -1
4 1
2 -1
4 -1
1 1
4 -1
1 1
2 -1
1 -1
3 -1
1 1
2 1
3 1
3 -1
*/