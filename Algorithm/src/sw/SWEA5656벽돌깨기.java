package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA5656벽돌깨기 {

	static int T, N, W, H;
	static int map[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static boolean visit[][];
	static int max;
	static int tgt[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			visit = new boolean[H][W];
			tgt = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			perm(map, 0);
			
			
			
			
			
		} // testcase

	}
	
	// 중복 순열 
	static void go(int [][]map, int cnt) {
		
		if(cnt == N) {
			return;
		}
		
		for (int i = 0; i < W; i++) {
			
			
			// 구슬에 맞는 시작벽돌 찾기 
			int r = 0;
			while(map[r][i] == 0 ) ++r;
			if(r == H) { // 맞는 시작벽돌 없는 상태 
				
			}else {
				
			}
			
			go(map, cnt + 1);
		}
		
		
	}
	static void check() {
		
		Deque<Dist> q = new ArrayDeque<>();
		
		// 해당 열 에서 가장 위에있는 벽돌 체크 해서 bfs, 가장 많이 부서지는 벽돌 위치 확인 
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if(map[j][i] != 0 ) q.offer(new Dist(j,i, 0));
			}	
		}
		
		while(!q.isEmpty()) {
			
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
				Dist e = q.poll();
				// 최대로 벽돌을 부술 수 있는 위치 찾기 
				max = Math.max(max, e.cnt);
				
				int py = e.y;
				int px = e.x;
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					
					for (int k = 0; k < map[py][px]; k++) { // 벽돌에 적힌 숫자 -1 만큼 반복 
						py += dy[d];
						px += dx[d];
						
						if(py < 0 || py >= H || px < 0 || px >= W ) continue;
						
						q.offer(new Dist(py, px, cnt + 1));
						
					}
				}
			}
			
			
			
			
			
			
		}
		
		
		
	}
	
	static class Dist {
		int y, x;
		int cnt;
		public Dist(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
	}

}
/*
5
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
*/
