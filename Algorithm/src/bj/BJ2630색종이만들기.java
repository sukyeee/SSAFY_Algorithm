package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630색종이만들기 {

	static int N;
	static int map[][];
	static int blue, white;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		paper(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
		
	}

	static void paper(int r, int c, int N) {

		//파란색 1 , 하얀색 0
		int one_cnt = 0;
		int zero_cnt = 0;
		for(int i=c;i<c+N;i++) {
			for(int j=r;j<r+N;j++) {
				if( map[i][j] == 1) one_cnt++;
				if( map[i][j] == 0) zero_cnt++;
			}
		}
		
		if( one_cnt == N*N ) {
			blue++;
			return;
		}
		if ( zero_cnt == N*N ) {
			white++;
			return;
		}
		else {
			
			// 1사분면 
			paper(r, c, N/2);
			
			// 2사분면 
			paper(r + N/2, c, N/2);
			
			// 3사분면 
			paper(r, c + N/2, N/2);
		
			// 4사분면 
			paper(r + N/2, c + N/2, N/2);
			
		}
		
		
	}
}
