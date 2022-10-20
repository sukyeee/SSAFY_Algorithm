package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11053가장긴증가하는부분수열 {

	static int N;
	static int LIS[];
	static int inp[];
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		inp = new int[N];
		LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inp[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(LIS, 1); // 1로 초기화 
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// i 이전 인덱스 중에서, inp[i] 보다 작은 것 중, LIS[j] 가 가장 큰 값 찾아서 + 1
				if( inp[i] > inp[j] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1;
			}
		}
		
		for (int i = 0; i < N; i++) {
			max = Math.max(max, LIS[i]);
		}
	
		System.out.println(max);
	}
	

}
/*
6
10 20 10 30 20 50
*/