package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9095_123더하기2 {

	static int T,N;

	// 재귀로  4를 1 2 3합으로, 5를 1 2 3 합으로 ...
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			System.out.println(dfs(N));
		}
		
		
	}
	static int dfs(int N) {
		if(N < 0) return 0;
		if(N == 0 || N == 1) return 1;
		if( N == 2 ) return 2;
		
		return dfs(N-1) + dfs(N-2) + dfs(N-3);
		
	}

}
