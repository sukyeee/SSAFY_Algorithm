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
	static int start;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		A = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N-1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		plus_cnt = Integer.parseInt(st.nextToken());
		minus_cnt = Integer.parseInt(st.nextToken());
		mul_cnt = Integer.parseInt(st.nextToken());
		div_cnt = Integer.parseInt(st.nextToken());
		
		
		dfs(0, 0, 0, 0, start, 0); // 덧셈 뺄셈 곱셈 나눗셈  합 깊이 
		
		
		//결과가 최대인 것과 최소인 것
		System.out.println(max);
		System.out.println(min);
		
	}
	
	static void dfs(int plus, int minus, int mul, int div, int sum, int depth) {
		if( plus == plus_cnt && minus == minus_cnt && mul == mul_cnt && div == div_cnt) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		
		
		for (int i = plus; i < plus_cnt; i++) {
			dfs(plus + 1, minus, mul, div, sum + A[depth], depth+1);
		}
		for (int i = minus; i < minus_cnt; i++) {
			dfs(plus, minus + 1, mul, div, sum - A[depth], depth+1);
		}
		for (int i = mul; i < mul_cnt; i++) {
			dfs(plus, minus, mul + 1, div, sum * A[depth], depth+1);
		}
		for (int i = div; i < div_cnt; i++) {
			dfs(plus, minus, mul, div + 1, sum / A[depth], depth+1);
		}
		
		
	}

}
