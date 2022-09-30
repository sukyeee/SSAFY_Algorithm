package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ14631로만들기2 {

	static int N;
	static int dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		Arrays.fill(dp, 1000000);
		// X % 3 == 0 이면 X/3
		// X % 2 == 0 이면 X/2
		// X--
		// 1만들기
	
		dp[0] = dp[1] = 0;
		dp[2] = 1;
		for (int i = 3; i <= N; i++) {
			
			
			dp[i] = dp[i-1] + 1;
			
			if ( i % 2 == 0 ) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			if ( i % 3 == 0 ) dp[i] = Math.min(dp[i], dp[i/3] + 1);
		
			
		}
		
		
		System.out.println(dp[N]);
	}

}
