package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_Z_1074 {

	static int N, r, c, ans;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		// N 은 2의 제곱수이므로 N을 실제 좌, 우에 해당하는 길이로 보정
		N = (int)Math.pow(2, N); // N 은 2로 나눌 수 있다.
		
		// 시작 원점
		int y = 0;
		int x = 0;
		
		// 분할 정복
		while( N > 1 ) {
			// 가로, 세로를 반으로 나누어서 4공간으로 분할
			N /= 2;
			
			// r, c가 4공간 중 어느 곳에 해당하는 지 판단, 각 공간에서 r, c 값에 따라 ans 값 갱신, 원점 보정
			if( r < y + N && c < x + N ) { // top-left
				;
			} else if( r < y + N && c >= x + N ) { // top-right
				ans += N*N*1;
				x += N;
			} else if( r >= y + N && c < x + N ) {
				ans += N*N*2;
				y += N;
				
			} else if( r >= y + N && c >= x + N ) {
				ans += N*N*3;
				x += N;
				y += N;
			}
			
			
		}
		
		System.out.println(ans);
		
		
	}

}
