package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ14961거스름돈 {

	static int N;
	static int dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[2] = dp[5] = 1;
        dp[4] = 2;

        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 5]) + 1;
        }

        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
		
	}

}
