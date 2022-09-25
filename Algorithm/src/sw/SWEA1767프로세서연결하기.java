package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767프로세서연결하기 {

	static int T, N;
	static int map[][];
	static boolean visit[][];
	static boolean visitCopy[][];
	static int tgt[];
	static List<Dist> core = new ArrayList<>();
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우 
	static int dx[] = {  0, 0,-1, 1 };
	static int core_cnt;
	static int cnt;
	static int len;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static boolean flag;
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
			tgt = new int[core.size()];
			// 0, 1, 2, 3 의 방향을 중복순열로 지정한다.
			
			// 최대한 많은 core에 전원을 연결 하였을 경우, 전선 길이의 최솟값 ?
			// 전원이 연결되지 않는 Core가 존재할 수 있다.
			
			// 가장자리는 이미 연결 되어있다.
			
			// 봐야 할 것은, 연결 된 코어의 수 (최대)
			// 전선 길이의 합 (최소)
			
			perm(0);
		
			System.out.println("#"+t+ " " + min);
		} //testcase
		
		
		
	}
	static void cal() {
		
		//초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = visitCopy[i][j];
			}
		}
		len = 0;
		core_cnt = 0;
		// tgt 안에 프로세서들의 방향 정해져 있음.
		for (int i = 0; i < core.size(); i++) {
			
			flag = false;
			Dist c = core.get(i);
			int d = tgt[i]; // tgt[i]가 각 프로세서의 방향을 뜻함 
			
			// 해당 프로세서의 방향 ~ 벽까지 중에서 "코어" 가 있는 경우나 선이 있는 경우 연결하지 못함!
			if( d == 0 ) { // 북쪽일 경우 
				for (int y = c.y - 1; y >= 0 ; y--) {
					if( visit[y][c.x] ) {
						flag = true;
						break;
					}
				}
				
				if(!flag) { // 코어 연결 가능한 경우 !
					for (int y = c.y - 1; y >= 0 ; y--) {
						visit[y][c.x] = true; // 연결 표시 
						len++;
					}	
				}
			}
			else if( d == 1 ) { // 남쪽일 경우 
				for (int y = c.y + 1; y < N ; y++) {
					if( visit[y][c.x] ) {
						flag = true;
						break;
					}
				}
				
				if(!flag) { // 코어 연결 가능한 경우 !
					for (int y = c.y + 1; y < N ; y++) {
						visit[y][c.x] = true; // 연결 표시 
						len++;
					}	
				}
			}
			else if ( d == 2 ) { // 서쪽일 경우 
				for (int x = c.x - 1; x >= 0 ; x--) {
					if( visit[c.y][x] ) {
						flag = true;
						break;
					}
				}
				
				if(!flag) { // 코어 연결 가능한 경우 !
					for (int x = c.x - 1; x >= 0 ; x--) {
						visit[c.y][x] = true;
						len++;
					}
				}
			}
			else if ( d == 3 ) {
				for (int x = c.x + 1; x < N ; x++) {
					if( visit[c.y][x] ) {
						flag = true;
						break;
					}
				}
				
				if(!flag) { // 코어 연결 가능한 경우 !
					for (int x = c.x + 1; x < N ; x++) {
						visit[c.y][x] = true;
						len++;
					}
				}
			}
			
			// 코어 연결할 수 있는 경우  
			if(!flag) {
				core_cnt++;
			}
			 
			
		}
		
		
		// core_cnt가 최대일 때의 최소 전선길이 값 
		
		if(  max < core_cnt  ) { // 기존보다 연결된 프로세서가 크거나 같을 때 만!
			// 전선의 길이가 더 작으면 갱신
			max = core_cnt;
			min = len;
		}
		else if ( max == core_cnt ) {
			min = Math.min(min, len);
		}
		
	}
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			cal();
			return;
		}
		
		for (int i = 0; i < 4; i++) { // 0 1 2 3 코어의 방향 
			
			tgt[tgtIdx] = i;
			perm( tgtIdx + 1 );
		}
		
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