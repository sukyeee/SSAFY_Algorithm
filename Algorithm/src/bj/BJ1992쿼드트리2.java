package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ1992쿼드트리2 {
	static int N;
	static int map[][];
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완

		quad(0, 0, N);
		System.out.println(sb);
	}
	
	static void quad(int r, int c, int N) {
		
		int one_cnt = 0;
		int zero_cnt = 0;
		for(int i=r;i<r+N;i++) {
			for(int j=c;j<c+N;j++) {
				if(map[i][j] == 1) one_cnt++;
				if(map[i][j] == 0) zero_cnt++;
			}
		}
		if(one_cnt == N*N ) {
			sb.append(1);
			return;
		}
		if(zero_cnt == N*N ) {
			sb.append(0);
			return;
		}
		else { // 압축 불가능 하면 1 2 3 4 반복 
			
			sb.append('(');
			
			// 1사분면 
			quad(r, c, N/2);
			
			// 2사분면
			quad(r, c + N/2, N/2);
			
			// 3사분면
			quad(r + N/2, c, N/2);
			
			// 4사분면 
			quad(r + N/2, c + N/2, N/2);
			
			sb.append(')');
		}
		
		
	}
	
	
}