package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_최적경로_1247 {

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
			
			perm(0);
			
			System.out.println("#" + t + " " + min);
		} //testcase
		
	}
	
	static void perm(int tgtIdx) {
		// 기저 조건
		if( tgtIdx == N ) {
			// complete code
			// 현재 고객의 순서를 순열에 의해서 경우의 수가 만들어졌다. 그 순서는 tgt 배열에 저장되어 있음.
			// 총 거리를 계산해서 min 갱신
			

			// 회사 -> 첫번째 고객
			int sum = distance(comY, comX, cust[tgt[0]][0], cust[tgt[0]][1]);
			// 첫번째 고객 -> 마지막 고객
			for (int i = 0; i < N-1; i++) {
				sum += distance( cust[tgt[i]][0] , cust[ tgt[i]][1] , cust[ tgt[i+1]][0] , cust[ tgt[i+1]][1]  );
			}
			// 마지막 고객 -> 집
			sum += distance(homeY, homeX, cust[tgt[N-1]][0], cust[tgt[N-1]][1]);
			
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if( select[i] ) continue;
			select[i] = true;
			tgt[tgtIdx] = i;
			perm( tgtIdx + 1 );
			select[i] = false;
		}
		
	}
	
	static int distance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}

}










