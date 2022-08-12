package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_도영이가만든맛있는음식_2961 {

	static int N, min;
	static int[][] src ;
	static boolean[] select;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		src = new int[N][2];
		select = new boolean[N];
		
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			src[i][0] = Integer.parseInt(st.nextToken());
			src[i][1] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		
		
		System.out.println(min);
		
		
	}
	
	
	static void subset(int srcIdx) {
		
		// 기저 조건
		if( srcIdx == N ) {
			// complete code
			int sin = 1;
			int ssn = 0;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if(select[i]) {
					sin *= src[i][0];
					ssn += src[i][1];
					cnt++;
				}
			}
			
			min = Math.min(min, Math.abs(sin - ssn));
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}
}
