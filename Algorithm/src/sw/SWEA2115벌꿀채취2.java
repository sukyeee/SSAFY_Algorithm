package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2115벌꿀채취2 {

	static int T;
	static int N, M, C, res, map[][], profit[][];

	static int tgt1[][];
	static int tgt2[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			profit = new int[N][N];
			
			for (int i = 0; i < N; i++) { // 벌통 크기 
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 완 
			
			// A가 채취할 벌꿀을 미리 정한 후, 남은 것 들 중 조합으로 B 구하기 
			
			res = 0;
			process();
			
			
			
			
			
			
			System.out.println("#" + t + " " + res);
			
		} // testcase
		
	}
	 static void process() {
			
		// 꿀을 채취할 수 있는 구간에서 얻을 수 있는 최대 수익
		makeProfit();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				combination(i, j+M, 1, profit[i][j]);
			}
		}
		
	}
	 
	static void combination(int x, int y, int cnt, int sum) {
		if(cnt==2){
			res = Math.max(res,sum);
			return;
		}
		for (int i = x; i < N; i++) {
			for (int j = y; j <= N-M; j++) {
				combination(i, j, cnt+1, sum+profit[i][j]);
			}
			y = 0;
		}
	} 

	
	 static void makeProfit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-M + 1; j++) {
				profitSubset(i, j, 0, 0, 0);
			}
		}
	}
	 
	static void profitSubset(int i, int j, int cnt, int sum, int totalSum){
		if(sum > C) return;
		if( cnt == M ) {
			profit[i][j-M] = Math.max(profit[i][j-M], totalSum);
			return;
		}
		// 이 꿀을 채취해보자 
		profitSubset(i, j+1, cnt+1, sum + map[i][j], totalSum + map[i][j] * map[i][j]);
		profitSubset(i, j+1, cnt+1, sum, totalSum );
		
	}	
}
/*
10
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
3 3 10
7 2 9
6 6 6
5 5 7
4 1 10
8 1 8 2
4 6 1 6
4 9 6 3
7 4 1 3
4 3 12
8 8 6 5
5 2 7 4
8 5 1 7
7 8 9 4
5 2 11
7 5 9 9 6
7 3 7 9 3
1 7 1 4 5
1 7 9 2 6
6 6 8 3 8
6 3 20
8 5 2 4 3 1
4 3 6 1 1 8
4 4 1 2 3 1
1 7 4 9 6 1
6 5 1 2 8 4
3 1 4 5 1 3
7 2 11
2 8 2 5 2 8 6
2 3 7 4 6 4 8
3 7 8 3 9 4 4
8 8 5 9 3 6 9
9 7 6 2 4 1 3
2 9 2 8 9 7 3
2 1 7 2 7 8 3
8 3 12
9 1 6 7 5 4 6 7
9 5 1 8 8 3 5 8
5 2 6 8 6 9 2 1
9 2 1 8 7 5 2 3
6 5 5 1 4 5 7 2
1 7 1 8 1 9 5 5
6 2 2 9 2 5 1 4
7 1 1 2 5 9 5 7
9 4 20
5 2 4 8 3 7 6 2 1
7 9 8 5 8 2 6 3 6
1 9 4 6 7 5 3 1 1
4 4 7 6 2 2 8 1 7
9 6 8 5 7 3 7 9 5
7 3 1 4 1 1 8 5 3
4 6 8 9 4 5 3 8 8
1 3 4 2 4 1 1 3 6
5 9 2 3 5 2 4 8 5
10 5 30
7 4 7 5 9 3 6 4 6 7
8 9 8 4 5 7 8 9 2 9
6 5 3 4 6 4 7 6 3 2
4 7 4 3 4 7 3 3 4 3
3 5 6 4 8 8 2 1 8 6
3 7 9 7 1 7 6 2 8 9
3 6 1 6 8 9 7 7 5 1
4 3 5 6 2 1 2 6 3 6
3 4 9 2 1 5 9 9 6 3
9 9 7 3 7 5 5 5 8 4
*/
