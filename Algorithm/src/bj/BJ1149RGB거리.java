package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1149RGB거리 {

	static int N;
	static int map[][];
	static int dp[][];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][4];
		
		map = new int[N+1][4];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완 
		
		//  빨 초 파 
		// 1번 집 색 != 2번 집 색 
		// N번 집 색 != N-1번 집 색
		// 양옆으로 집 색은 달라야함 
		
		// 1번 집 빨26 초40 파83
		// 2번 집 빨49 초60 파57
		// 3번 집 빨13 초89 파99
		
		// [i][1]빨강    [i][2]초록     [i][3]파랑
		dp[0][0] = dp[0][1] = dp[0][2] = dp[0][3] = 0;
		for (int i = 1; i <= N; i++) {
			
			// 빨간색을 칠하는 방법은 이전에 초록, 파랑으로 칠한 것만 가능 
			dp[i][1] = Math.min(dp[i-1][2] + map[i][1] , dp[i-1][3] + map[i][1]);
			
			// 초록색을 칠하는 방법은 이전에 빨강, 파랑으로 칠한 것만 가능 
			dp[i][2] = Math.min(dp[i-1][1] + map[i][2], dp[i-1][3] + map[i][2]);
			
			// 파란색을 칠하는 방법은 이전에 빨강, 초록으로 칠한 것만 가능
			dp[i][3] = Math.min(dp[i-1][1] + map[i][3], dp[i-1][2] + map[i][3]);
			
			
		}
		
		
		int ans = Math.min(dp[N][1], dp[N][2]);
		ans = Math.min(ans, dp[N][3]);
	
		System.out.println(ans);
		
		
	
	}

}
