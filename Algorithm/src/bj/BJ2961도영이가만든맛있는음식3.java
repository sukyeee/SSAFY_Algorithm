package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



// 조합
public class BJ2961도영이가만든맛있는음식3 {
	
	static int N;
	static int sour, bitter, differ;
	static int Min = Integer.MAX_VALUE;
	static Item[] src;
	static Item tgt[];
	static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		src = new Item[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			src[i] = new Item( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i=1;i<=N;i++) { // i는 선택하는 원소 개수
			tgt = new Item[i]; // 1개짜리 배열, 2개짜리 배열... N개자리 배열
			comb(0, 0); 
				
		}

		System.out.println(Min);
	}


	static void comb(int srcIdx, int tgtIdx) {
		
		if(tgtIdx == tgt.length ) {
			sour = 1;
			bitter = 0;
			
			for(int i=0;i<tgt.length;i++) {
				sour = sour * tgt[i].s;
				bitter = bitter + tgt[i].b;
			}
			// 공집합은 제외시켜야 함
			 Min = Math.min(Min, Math.abs(sour - bitter));
			
			return;
		}
		
		if( srcIdx == N ) return;
		
		tgt[tgtIdx] = src[srcIdx];

		comb(srcIdx + 1 , tgtIdx + 1); // 현재 선택
		comb(srcIdx + 1 , tgtIdx); // 현재 선택 X
	
	

		
	}
	
	static class Item {
		int s, b;
		
		public Item(int s, int b) {
			this.s = s;
			this.b = b;
		}
		
	}
}
