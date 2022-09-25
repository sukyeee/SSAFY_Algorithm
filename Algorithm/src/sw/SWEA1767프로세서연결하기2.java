package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767프로세서연결하기2 {

	static int T, N;
	static int map[][];
	static boolean visit[][];
	static boolean visitCopy[][];
	static List<Dist> core = new ArrayList<>();
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우 
	static int dx[] = {  0, 0,-1, 1 };
	static int core_cnt;
	static int cnt;
	static int len;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static boolean flag;
	static int sum;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			visitCopy = new boolean[N][N];
			core.clear();
			for (int i = 0; i < N; i++) { 
				 StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) visitCopy[i][j] = true;
					
					if(map[i][j] == 1 && ( i != 0 && j != 0 && i != N-1 && j != N-1 )) core.add(new Dist(i, j));
				}
			}
			// 입력 완 
			
			// 최대한 많은 core에 전원을 연결 하였을 경우, 전선 길이의 최솟값 ?
			// 전원이 연결되지 않는 Core가 존재할 수 있다.
			
			// 가장자리는 이미 연결 되어있다.
			
			// 봐야 할 것은, 연결 된 코어의 수 (최대)
			// 전선 길이의 합 (최소)
			
			// 모든 코어에 대해서 완전 탐색. 

			//초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit[i][j] = visitCopy[i][j];
				}
			}
			
			dfs(0, 0, 0); // 몇 번째 core인지 , 전선 길이의 합, 코어 개수 
		
			System.out.println("#"+t+ " " + min);
		} //testcase
		
		
		
	}
	static void dfs(int coreIdx, int sum, int core_cnt) {
			
		if( coreIdx == core.size() ) {
			// core_cnt가 최대일 때의 최소 전선길이 값 
			
			if(  max < core_cnt  ) { // 기존보다 연결된 프로세서가 크거나 같을 때 만!
				// 전선의 길이가 더 작으면 갱신
				max = core_cnt;
				min = sum;
			}
			else if ( max == core_cnt ) {
				min = Math.min(min, sum);
			}
			return;
		}
		
		Dist c = core.get(coreIdx); // 프로세스 하나씩 꺼내기 
		
		for (int d = 0; d < 4; d++) { // 4방향 완전 탐색 
			int len = 0;
			int py = c.y;
			int px = c.x;
			while(true) {
				py += dy[d];
				px += dx[d];	
				
				if(py < 0 || py >= N || px < 0 || px >= N) break;
				if(visit[py][px]) { // 프로세스가 이미 있다면? 배치 불가능 !
					len = 0;
					break;
				}
			
				len++;
			}
			
			// len이 1 이상일때만 visit 표시 
			py = c.y;
			px = c.x;
			for (int i = 0; i < len; i++) {
				py += dy[d];
				px += dx[d];
				visit[py][px] = true;
			}
			
			if(len == 0) dfs(coreIdx + 1, sum,  core_cnt ); 
			else {
				dfs(coreIdx + 1, sum + len, core_cnt + 1);
				
				// visit 배열 원래대로 되돌리기 
				py = c.y;
				px = c.x;
				for (int i = 0; i < len; i++) {
					py += dy[d];
					px += dx[d];
					visit[py][px] = false;
				}
				
			}
				
				
			
			
		} // for d
		
		
		
		
		
	}
	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}


/* 

3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0



*/