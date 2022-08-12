package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_도영이가만든맛있는음식_2961_Binary {

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
		
		subset(0, 0);
		
		
		System.out.println(min);
		
		
	}
	
	
	static void subset(int srcIdx, int flag) {
		

		
		for (int i = 0; i < 1 << N; i++) {
	        for (int j = 0; j < N; j++) { 
	            // 각 자리 논리합(AND)의 결과가 0이 아닐경우
	            // (해당bit를 포함함을 의미)
	            if ((i & 1 << j) != 0) {
	            	
	            	int sin = 1;
	    			int ssn = 0;
	    			int cnt = 0;
	    			for (int k = 0; k < N; k++) {
	    				if(( (1 << k )) != 0) {
	    					sin *= src[k][0];
	    					ssn += src[k][1];
	    					cnt++;
	    				}
	    			}
	    			
	    			if(cnt != 0)min = Math.min(min, Math.abs(sin - ssn));
	            	
	            }
	              
	        }
	      
	    }
		
		
		
	}
	
}
