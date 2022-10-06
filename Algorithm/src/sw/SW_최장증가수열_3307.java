package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_최장증가수열_3307 {

	static int T, N;
	static int[] input;
	static int[] LIS;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			max = 0;
			N = Integer.parseInt(br.readLine());
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			input = new int[N];
			LIS = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for (int i = 0; i < N; i++) { // 애를 기준으로 
				LIS[i] = 1;
				for (int j = 0; j < i; j++) {
					if(input[j] < input[i] && LIS[i] <= LIS[j] ) {
						LIS[i] = LIS[j]+1;
					}
				}
				max = Math.max(max, LIS[i]);
			}
			
			System.out.println("#" + t + " " + max);
		} // testcase 
		
	}

}
