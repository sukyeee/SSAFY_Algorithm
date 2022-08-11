package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW9229한빈이와SpotMart2 {

	static int TC;
	static int N, M;
	static int a[];
	static int sum[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("SW9229한빈이와SpotMart.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 과자 봉지 두 개 M 그램 이하로 해야함
		// 정확히 두 봉지! 사야 함

		TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());

			// n m
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			a = new int[N+1];
			sum = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				sum[i] += a[i] + a[i-1];
			}
				
			int Max = -1;
			int accu = -1;
			for (int i = 1; i <= N; i++) {
				
				if(sum[i] - a[i-2] <= M) {
					accu = sum[i] - sum[i-2];
					Max = Math.max(Max, accu);
				}
				
			}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			System.out.println("#" + t + " " + Max);
		}

	}

}
