package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 초과
public class SW_준환이의양팔저울_3234_3 {

	static int T, N, ans;
//	static int[] choo;
//	static boolean[] select;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			int[] choo = new int[N];
			boolean[] select = new boolean[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				choo[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			
			perm(choo, select, 0, 0, 0);
			
			System.out.println("#" + t + " " + ans);
			
		}
		
	}
	
	static void perm(int[] choo, boolean[] select, int tgtIdx, int leftSum, int rightSum) {
		
		if(rightSum > leftSum) return;
		
		// 기저 조건
		if(tgtIdx == N) {
			// complete code
			ans++;
			return;
		}
		
		for (int i = 0; i < choo.length; i++) {
			if( select[i] ) continue;
			select[i] = true;
			// 2가지의 재귀호출
			
			// #1 왼쪽에 추를 올리는 경우
			perm( choo, select, tgtIdx + 1, leftSum + choo[i] , rightSum);
			// #2 오른쪽에 추를 올리는 경우
			perm( choo, select, tgtIdx + 1, leftSum  , rightSum + choo[i]);			
			
			select[i] = false;
			
		}
		
	}

}
