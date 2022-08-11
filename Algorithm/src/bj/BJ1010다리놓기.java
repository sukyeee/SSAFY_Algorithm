package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1010다리놓기 {

	static int T;
	static int result;
	static int N, M;
	static int c[][] = new int[30][30];
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//조합 누적 초기화  
			for(int i=0;i<30;i++) {
				for(int j=0;j<30;j++) {
					if( i == 0 || j == 0 || i == j) c[i][j] = 1;
				}
			}
			
			for(int i=1;i<30;i++) {
				for(int j=i+1;j<30;j++) {
					c[i][j] = c[i-1][j-1] + c[i][j-1];
				}
			}
			
			System.out.println(c[N][M]);
		}
		
	}
	
}
