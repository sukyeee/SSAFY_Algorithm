package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888연산자끼워넣기 {

	static int N;
	static int A[];
	static int plus_cnt,minus_cnt,mul_cnt,div_cnt;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		plus_cnt = Integer.parseInt(st.nextToken());
		minus_cnt = Integer.parseInt(st.nextToken());
		mul_cnt = Integer.parseInt(st.nextToken());
		div_cnt = Integer.parseInt(st.nextToken());
		
		dfs(0, 0, 0, 0, 0);
		
		
		//결과가 최대인 것과 최소인 것
	}
	
	static void dfs(int plus, int minus, int mul, int div, int sum) {
		if( plus == plus_cnt && minus == minus_cnt && mul == mul_cnt && div == div_cnt) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		
		if(plus == plus_cnt || minus == minus_cnt || mul == mul_cnt || div == div_cnt ) return;
		
		for (int i = 0; i < plus_cnt; i++) {
			dfs(plus + 1, minus, mul, div, sum + A[i]);
		}
		for (int i = 0; i < minus_cnt; i++) {
			dfs(plus, minus + 1, mul, div, sum - A[i]);
		}
		for (int i = 0; i < mul_cnt; i++) {
			dfs(plus, minus, mul + 1, div, sum * A[i]);
		}
		for (int i = 0; i < div_cnt; i++) {
			dfs(plus, minus, mul, div + 1, sum / A[i]);
		}
		
	}

}
