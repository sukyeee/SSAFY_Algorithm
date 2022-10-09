package sw;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA2115벌꿀채취4 {

	// 3<=n<=10
	// 1<=m<=5
	// 10<=c<=30
	//1. 두 팀 선택 (backtracking)
	//2. 팀 선택 표시  (setSelect)
	//3.1 c이하 최댓값 설정 (setMax)
	//3.2 c이하 최댓값 설정 (combination)
	//selected[i][j] i,j번째 칸의 상태
	//1 = 1팀 
	//2 = 2팀
	
	static int[][] selected;
	static int[][] graph;
	static int N,M,C;
	static int answer;
	static int[] max = new int[3];

	static Dist[] sel;
	static int Max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			//input
			StringTokenizer tk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());
			C = Integer.parseInt(tk.nextToken());
			selected = new int[N][N];
			graph = new int[N][N];
			answer=0;
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			
			dfs(1,0);
			
	
		}
	
	}
	static void subset(int srcIdx) {
		
		if(srcIdx == sel.length ) { // 기저 조건 
			
			int sum1 = 0;
			int sum2 = 0;
			// 선택된 것들에 대해서 max값 찾기  
			for (int i = 0; i < sel.length; i++) {
				if( sel[i].sel ) { // 선택된 것들에 한해서 
					if( sel[i].cnt == 1 ) {
						if(sum1 <= C) {
							int a = graph[sel[i].y][sel[i].x];
						}
						
					}
					else if( sel[i].cnt == 2) {
						if(sum2 <= C) {
							int a = graph[sel[i].y][sel[i].x];
						}
					}
				}
			}
			
			
			return;
		}
		
		sel[srcIdx].sel = true; // 선택 하고 
		subset(srcIdx + 1); // 다음으로 
		
		sel[srcIdx].sel = false; // 선택하지 않고 
		subset(srcIdx + 1); // 다음으로 
		
	}
	static void dfs(int cnt, int idx) {
		if(cnt == 3) {
			// 두 명 다 뽑았을 때, 최댓값 갱신 
		
			sel = new Dist[M*2];
			int k = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( selected[i][j] != 0 ) {
						sel[k] = new Dist(i, j, selected[i][j], false);
						k++;
					}
				}
			}
			subset(0); // 부분집합 -> 일꾼1 일꾼2 동시에 가능 
			
			
			return;
		}
		if(idx >= N*N ) return;
		
		// 일꾼1, 일꾼2 겹치지 않게 각각 꿀 뽑아내기 
		int y = idx/N;
		int x = idx%N;
		
		
		// 그래프 한 줄 넘어가는 경우 
		if( x + M > N ) {
			// 다음 행으로 
			dfs(cnt, (idx+M) - (idx+M)%N);
		}
		else {
			
			select(cnt, y, x);
			
			dfs(cnt+1, idx + M);
			
			select(cnt, y, x);
			
			dfs(cnt, idx + 1);
		}
		
		
	}
	
	static void select(int cnt, int y, int x) {
		for(int i=0; i<M; i++) {
			// 선택 
			if(selected[y][x+i]==0) {
				selected[y][x+i] = cnt;
			}
			// 선택 하지 않음 
			else{
				selected[y][x+i] = 0;
			}
		}
	}
	
	static class Dist {
		int y, x, cnt;
		boolean sel;
		public Dist(int y, int x, int cnt, boolean sel) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.sel = sel;
		}

	

		
		
	}
}

/*
10
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
3 3 10
7 2 9
6 6 6
5 5 7
4 1 10
8 1 8 2
4 6 1 6
4 9 6 3
7 4 1 3
4 3 12
8 8 6 5
5 2 7 4
8 5 1 7
7 8 9 4
5 2 11
7 5 9 9 6
7 3 7 9 3
1 7 1 4 5
1 7 9 2 6
6 6 8 3 8
6 3 20
8 5 2 4 3 1
4 3 6 1 1 8
4 4 1 2 3 1
1 7 4 9 6 1
6 5 1 2 8 4
3 1 4 5 1 3
7 2 11
2 8 2 5 2 8 6
2 3 7 4 6 4 8
3 7 8 3 9 4 4
8 8 5 9 3 6 9
9 7 6 2 4 1 3
2 9 2 8 9 7 3
2 1 7 2 7 8 3
8 3 12
9 1 6 7 5 4 6 7
9 5 1 8 8 3 5 8
5 2 6 8 6 9 2 1
9 2 1 8 7 5 2 3
6 5 5 1 4 5 7 2
1 7 1 8 1 9 5 5
6 2 2 9 2 5 1 4
7 1 1 2 5 9 5 7
9 4 20
5 2 4 8 3 7 6 2 1
7 9 8 5 8 2 6 3 6
1 9 4 6 7 5 3 1 1
4 4 7 6 2 2 8 1 7
9 6 8 5 7 3 7 9 5
7 3 1 4 1 1 8 5 3
4 6 8 9 4 5 3 8 8
1 3 4 2 4 1 1 3 6
5 9 2 3 5 2 4 8 5
10 5 30
7 4 7 5 9 3 6 4 6 7
8 9 8 4 5 7 8 9 2 9
6 5 3 4 6 4 7 6 3 2
4 7 4 3 4 7 3 3 4 3
3 5 6 4 8 8 2 1 8 6
3 7 9 7 1 7 6 2 8 9
3 6 1 6 8 9 7 7 5 1
4 3 5 6 2 1 2 6 3 6
3 4 9 2 1 5 9 9 6 3
9 9 7 3 7 5 5 5 8 4
*/