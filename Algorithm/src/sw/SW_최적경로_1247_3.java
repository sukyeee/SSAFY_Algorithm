package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_최적경로_1247_3 {

	static int T, N, comY, comX, homeY, homeX, min;
	static int[][] cust; // src
	static int[] tgt;
	static boolean[] select;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine()); // 고객 수
			cust = new int[N][2];
			tgt = new int[N];
			select = new boolean[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			comY = Integer.parseInt(st.nextToken());
			comX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				cust[i][0] = Integer.parseInt(st.nextToken());
				cust[i][1] = Integer.parseInt(st.nextToken());
			}
			
			perm(0, 0);
			
			System.out.println("#" + t + " " + min);
		} //testcase
		
	}
	
	static void perm(int tgtIdx, int sum) { // sum : 현재 단계 이전까지의 거리의 합이 전달
		// 기저 조건
		if( tgtIdx == N ) {
		
			sum += distance(homeY, homeX, cust[tgt[N-1]][0], cust[tgt[N-1]][1]);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if( select[i] ) continue;
			select[i] = true;
			tgt[tgtIdx] = i;
			
			int dis = 0;
			if( tgtIdx == 0 ) {
				// 회사 -> 첫번째 고객
				dis = distance(comY, comX, cust[tgt[0]][0], cust[tgt[0]][1]);
			} else {
				// 	  C - 7-2-4-5..6-8- H
				// tgtIdx 0 1 2 3..
				dis = distance( cust[tgt[tgtIdx-1]][0] , cust[ tgt[tgtIdx-1]][1] , cust[ tgt[tgtIdx]][0] , cust[ tgt[tgtIdx]][1]  );

			}
			
			if( sum + dis < min ) perm( tgtIdx + 1, sum + dis );

			select[i] = false;
		}
		
	}
	
	static int distance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}

}










