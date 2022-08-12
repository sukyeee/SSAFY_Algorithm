package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dfs
public class BJ2961도영이가만든맛있는음식2 {
	
	static int N;
	static int sour, bitter, differ;
	static int Min = Integer.MAX_VALUE;
	static Item[] src;
	static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		src = new Item[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			src[i] = new Item( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		
		dfs(0, 1, 0); // srcIdx, 신맛, 쓴맛 초기값
		
		System.out.println(Min);
	}

	static void dfs(int srcIdx, int sour, int bitter) {
		
		if(srcIdx == N) {
		
			if(cnt != 0) Min = Math.min(Min, Math.abs(sour - bitter));
			sour = 1;
			bitter = 0;
			return;
		}
		
		cnt++;
		dfs(srcIdx + 1, sour * src[srcIdx].s, bitter + src[srcIdx].b); // 선택 하는 경우
		
		cnt--;
		dfs(srcIdx + 1, sour, bitter); // 선택 안하는 경우
		
	}
	
	static class Item {
		int s, b;
		
		public Item(int s, int b) {
			this.s = s;
			this.b = b;
		}
		
	}
}
