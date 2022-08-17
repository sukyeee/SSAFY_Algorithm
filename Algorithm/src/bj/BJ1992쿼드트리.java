package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ1992쿼드트리 {
	static int N;
	static int map[][];
	static StringBuilder sb = new StringBuilder();
	static int one_cnt;
	static int zero_cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완
		
	
		sb.append('(');
		
		dfs(N);
		
		
		sb.append(')');
		System.out.println(sb);
	}
	
	
	static void dfs(int N) {
		
		one_cnt = 0;
		zero_cnt = 0;
		
		// 1사분면
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				if(map[i][j] == 1) one_cnt++;
				else if(map[i][j] == 0) zero_cnt++;
			}
		}
		if(one_cnt == N/2 * N/2) sb.append(1);
		if(zero_cnt == N/2 * N/2) sb.append(0);
		else dfs( N/2 );
		
		
		one_cnt = 0;
		zero_cnt = 0;
		// 2사분면
		for(int i=0;i<N/2;i++) {
			for(int j=N/2;j<N;j++) {
				
			}
		}
		if(one_cnt == N/2 * N/2) sb.append(1);
		if(zero_cnt == N/2 * N/2) sb.append(0);
		else dfs( N/2 );
		
		
		one_cnt = 0;
		zero_cnt = 0;
		// 3사분면
		for(int i=N/2;i<N;i++) {
			for(int j=0;j<N/2;j++) {
				
			}
		}
	
		if(one_cnt == N/2 * N/2) sb.append(1);
		if(zero_cnt == N/2 * N/2) sb.append(0);
		else dfs( N/2 );
		
		
		one_cnt = 0;
		zero_cnt = 0;
		// 4사분면
		for(int i=N/2;i<N;i++) {
			for(int j=N/2;j<N;j++) {
				
			}
		}
		if(one_cnt == N/2 * N/2) sb.append(1);
		if(zero_cnt == N/2 * N/2) sb.append(0);
		else dfs( N/2 );
		
		
	}

}
