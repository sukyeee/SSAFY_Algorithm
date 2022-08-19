package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3234준환이의양팔저울 {

	static int T, N;
	static int weight[];
	static int cnt;
	static boolean select[];
	static int right[];
	static int left[];
	static int sum = 0;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			right = new int[N];
			left = new int[N];
			weight = new int[N];
			select = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) weight[i] = Integer.parseInt(st.nextToken());

			for(int i=0;i<N;i++) sum += weight[i];
			
			dfs(0, 0, 0);
			
			System.out.println("#" + t +" " + cnt);
		} // testcase
		
	}
	
	static void dfs(int tgtIdx, int right_sum, int left_sum ) {
		
		
		// 기저 조건 - 모든 추를 저울에 올릴 경우
		if(tgtIdx == N) {
			cnt++;
			return;		
		}
	
		for(int i=0;i<N;i++) {
			
		
			if( right_sum + weight[i] > left_sum ) continue;
			
			dfs( tgtIdx + 1, right_sum , left_sum + weight[i] );
			dfs( tgtIdx + 1, right_sum + weight[i], left_sum );
				

		}
		
		
	}

}
