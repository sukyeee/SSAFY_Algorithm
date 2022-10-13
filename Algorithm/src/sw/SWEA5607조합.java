package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607조합 {

	static int T, N, R, P;
	static double ans;
	static long[] factMod;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		P = 1234567891;
		factMod = new long[1000001];
		factMod[0] = 1;
		
		
		// factorial mod 미리 계산하기 
		for (int i = 1; i <= 1000000; i++) {
			factMod[i] = ( factMod[i-1] * i ) % P;
		}
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			// nCr % P 구하기 

			// n!
			// ----------  % P
			// (n-r)! * r! 
			
			// n! => A, (n-r)! * r! => B 로 놓으면
			// 위 식은 A * B ^ -1 % P
			
			// 페르마의 소정리에 의해
			// ( A * (B ^ -1) ) % P ==> ( A * B ^ (P-2) ) % P  

			long top = factMod[N];
			long bottom = factMod[N-R] * factMod[R] % P;
						
			ans = (top * pow( bottom, P - 2)) % P;
			System.out.println("#" + t + " " + ans);
			
		}
	}

	static long pow(long base, long expo) {
		if( expo == 0 ) return 1;
		else if( expo == 1 ) return base;
		
		long num = 1;

		while (expo > 0) {

			// 지수가 홀수일 경우, 지수를 줄이는 expo /= 2 처리가 안되므로 미리 결과 값에 base를 한번 곱함 
			if (expo % 2 == 1) {
				num *= base;
				num %= P;
			}
			base = (base * base) % P;
			expo /= 2;
		}
		return num;
	}
}
