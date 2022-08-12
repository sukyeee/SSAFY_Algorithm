package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ2961도영이가만든맛있는음식 {
	
	static int N;
	static int sour, bitter, differ;
	static int Min = Integer.MAX_VALUE;
	static Item[] src;
	static boolean select[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		src = new Item[N];
		select=  new boolean[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			src[i] = new Item( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		}
		
		subset(0);
		
		System.out.println(Min);
	}

	static void subset(int srcIdx) {
		
		// 신맛은 신맛들 곱
		// 쓴맛은 쓴맛들 합
		// 차이가 가장 작은 요리의 차이 출력
		
		if( srcIdx == N ) {
			sour = 1;
			bitter = 0;
			int cnt = 0;
			for(int i=0;i<N;i++) {
				if(select[i]) {
					sour = sour * src[i].s;
					bitter = bitter + src[i].b;
					cnt++;
				}
				
			}
			// 공집합은 제외시켜야 함
			if(cnt != 0) Min = Math.min(Min, Math.abs(sour - bitter));
			
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		
		select[srcIdx] = false;
		subset(srcIdx + 1);
		
		
	}
	
	static class Item {
		int s, b;
		
		public Item(int s, int b) {
			this.s = s;
			this.b = b;
		}
		
	}
}
