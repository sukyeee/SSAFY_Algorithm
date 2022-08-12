package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀호출, 파라미터, 가지치기
// 파라미터 -> Item 클래스 필요 X -> 2차원 배열
// Item => 2차원 배열
// 부분집합X -> select 필요 X
public class SW_햄버거다이어트_5215_3 {

	static int T, N, L, max;
	static int[][] src;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T  = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			// src : N개로 고정
			src = new int[N][2]; // 0: point, 1: calory
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				src[i][0] = Integer.parseInt(st.nextToken());
				src[i][1] = Integer.parseInt(st.nextToken());
				
			}
			
			dfs(0, 0, 0); // 맨앞 src 부터 시작,
			
			System.out.println("#" + t + " " + max);
			
		}
		
	}
	
	static void dfs(int srcIdx, int point, int cal) {
		// 가지치기
		if( cal > L ) return;
		
		// 기저조건
		if( srcIdx == N ) {
			// complete code
			max = Math.max(max, point);
			return;
		}
		
		dfs( srcIdx + 1, point, cal); // 현재 srcIdx 재료를 선택 X
		dfs( srcIdx + 1, point + src[srcIdx][0], cal + src[srcIdx][1]) ; // 재료를 선택 O
		
		// 순서 바꿔도 답 같은듯?
		
	}


}
