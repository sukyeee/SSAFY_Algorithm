package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
public class SW_햄버거다이어트_5215_2 {

	static int T, N, L, max;
	static Item[] src;
	static boolean[] select;
	
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
			src = new Item[N];
			select = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				src[i] = new Item( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			subset(0);
			
			System.out.println("#" + t + " " + max);
			
		}
		
	}
	
	static void subset(int srcIdx) {
		if(srcIdx == N) {
			// complete code
			int cal = 0;
			int point = 0;
			
			for (int i = 0; i < N; i++) { // N개의 재료를 순회하면서 선택된 것만 고려 (select)
				if( select[i] ) continue; // !select[i] 와 select[i] 는 결과가 같다!!
				cal += src[i].c;
				point += src[i].p;
			}
			
			if( cal <= L ) max = Math.max(max, point);
			
			return;
		}
		
		
		select[srcIdx] = true; // 현재 선택
		subset(srcIdx + 1);
		
		select[srcIdx] = false; // 현재 선택 X
		subset(srcIdx + 1);
		
		
	}
	
	static class Item {
		int p; // 맛에 대한 포인트
		int c; // 칼로리
		public Item(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

}
