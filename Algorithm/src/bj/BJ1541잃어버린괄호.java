package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1541잃어버린괄호 {

	static String inp;
	static String a[];
	static int sum, num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer stMinus = new StringTokenizer(br.readLine(), "-");
		
		// - 토큰 까지 잘라내기
		int minus = stMinus.countTokens();
		for(int i=0;i<minus;i++) {
			String token = stMinus.nextToken();
			// + 토큰 개수 만큼 더해주기
			num = 0;
			StringTokenizer stPlus = new StringTokenizer(token, "+");
			int plus = stPlus.countTokens();
			for(int j=0;j<plus;j++) {
				num += Integer.parseInt(stPlus.nextToken());
			}
			
			if(i == 0) sum = num;
			else sum -= num;
			
			
		}
		
		System.out.println(sum);
		
		
		
		
	}
}
