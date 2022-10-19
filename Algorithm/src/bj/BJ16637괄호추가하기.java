package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ16637괄호추가하기 {

	static int N;
	static char oper[];
	static int num[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		// 0 ~ 9
		// 짝수번째는 모두 숫자
		// 홀수번째는 모두 연산자
		// 백트래킹으로 계산 
		
		String line = br.readLine();
		for (int i = 0; i < N; i++) {
			if(i%2 == 0) num[i] = line.charAt(i) - '0';
			else if(i%2 == 1) num[i] = line.charAt(i);
		}
		num[N-1] = line.charAt(N-1) - '0';
		dfs(0);
		
	}
	
	static void dfs(int sum) {
		
		for (int i = 0; i < N/2; i++) { // 연산자 개수만큼 반복
			
		}
	}

}
