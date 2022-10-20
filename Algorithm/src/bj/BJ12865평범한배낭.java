package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12865평범한배낭 {

	static int N, K;
	static int dp[][];
	static Item knapsack[]; // 무게, 가치 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		knapsack = new Item[N+1];
		dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			knapsack[i] = new Item(w, v); 
		}

		
		for (int i = 1; i <= N; i++) { // 물건 번호 
			for (int j = 1; j <= K; j++) { // 무게 당 넣을 수 있는 물건
				
				dp[i][j] = dp[i-1][j];
				// 만약 무게를 넘지 않는 다면
				if( j - knapsack[i].w >= 0 ) {
					
					dp[i][j] = Math.max( knapsack[i].v + dp[i-1][j - knapsack[i].w], dp[i-1][j] );
				}
				
			}
		}
		
		
		System.out.println(dp[N][K]);
	}
	static class Item {
		int w, v;

		public Item(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
		
	}

}
/*
4 7
6 13
4 8
3 6
5 12
*/