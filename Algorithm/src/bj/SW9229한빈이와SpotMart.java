package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW9229한빈이와SpotMart {

	static int TC;
	static int N, M;
	static int a[];

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
			a = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				a[i] = Integer.parseInt(st.nextToken());

			int sum = -1;
			int Max = -1;

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {

					if (a[i] + a[j] <= M) {
						sum = a[i] + a[j];
						Max = Math.max(sum, Max);
					}
				}
			}

			System.out.println("#" + t + " " + Max);
		}

	}

}
