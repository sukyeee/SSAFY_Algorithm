package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074_Z {

	// 분할 정복
	static int N, r, c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		double cnt = 0;
		
		while(true) {
			N--;	
			if( N < 0) break;
			// 1사분면
			if(r < Math.pow(2, N) && c < Math.pow(2, N)) {
				cnt += Math.pow(2, N) * Math.pow(2, N) * 0 ;
			}
			// 2사분면
			if(r < Math.pow(2, N) && c >= Math.pow(2, N)) {
				cnt += Math.pow(2, N) * Math.pow(2, N) * 1 ;
				c -= Math.pow(2, N);
			}			
			// 3사분면
			if(r >= Math.pow(2, N) && c < Math.pow(2, N)) {
				cnt += Math.pow(2, N) * Math.pow(2, N) * 2 ;
				r -= Math.pow(2, N);
			}
			// 4사분면
			if(r >= Math.pow(2, N) && c >= Math.pow(2, N)) {
				cnt += Math.pow(2, N) * Math.pow(2, N) * 3 ;
				r -= Math.pow(2, N);
				c -= Math.pow(2, N);
			}
		}
		
		System.out.println((int)cnt);
	}

}
