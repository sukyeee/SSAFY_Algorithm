package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656벽돌깨기3 {

	static int T, N, W, H;
	static int map[][];
	static int mapCopy[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static boolean visit[][];
	static int tgt[];
	static int min, sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			mapCopy = new int[H][W];
			visit = new boolean[H][W];
			tgt = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			go(map, 0);
			
			
			
			
			System.out.println("#" + t + " " + min);
		} // testcase

	}
	
	// 중복 순열 
	static void go(int [][]map, int cnt) {
		
		if(cnt == N) { // 구슬을 다 던졌으면 
			brickCount(mapCopy);
			return;
		}
		mapCopy = new int[H][W];

		for (int x = 0; x < W; x++) {
			
			
			// 구슬에 맞는 시작벽돌 찾기 
			int y = 0;
			while( y < H && map[y][x] == 0 ) ++y;
			if(y == H) { // 맞는 시작벽돌 없는 상태 
				continue;
			}else {
				
				// 벽돌 복사 
				copy(map, mapCopy);
				
				// 벽돌 연쇄 작업
				check(mapCopy, y, x);
				
				// 벽돌 아래로 당기기 
				down(mapCopy);
				
				// 벽돌 개수 세기 ( 최소 벽돌 개수 갱신 )
				brickCount(mapCopy);
				
				go(mapCopy, cnt + 1); // 다음 구슬 던지기 	
			}
			
			
		}
		
		
		
	}
	
	
	static void brickCount(int[][] map) {
		sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] > 0) sum++;
			}
		}
		min = Math.min(min, sum);
		
	}
	static void down(int[][] map) {
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < W; i++) { // 열 하나씩 
			
			for (int j = 0; j < H; j++) {
				if(map[j][i] != 0) {
					st.push(map[j][i]);
					map[j][i] = 0;
				}
				
			}
			
			int idx = H-1;
			while(!st.isEmpty()) {
				map[idx--][i] = st.pop(); 
			}
		}
		
	}
	static void copy(int [][]map, int [][]mapCopy) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
	}
	static void check(int [][]map, int y, int x) {
		
		Deque<Dist> q = new ArrayDeque<>();
		
		if(map[y][x] > 1) {
			// 부서진 벽돌은 0으로 표시 
			q.offer(new Dist(y,x, map[y][x]));
		}
		map[y][x] = 0;
		
		
		while(!q.isEmpty()) {
						
				Dist e = q.poll();
				
				for (int d = 0; d < 4; d++) {
					// 최대로 벽돌을 부술 수 있는 위치 찾기 
					int py = e.y;
					int px = e.x;
					
					for (int k = 0; k < e.cnt - 1; k++) { // 벽돌에 적힌 숫자 -1 만큼 반복 
						py += dy[d];
						px += dx[d];
						
						if(py < 0 || py >= H || px < 0 || px >= W ) continue;
						
						if(map[py][px] > 1) {
							// 부서진 벽돌은 0으로 표시 
							q.offer(new Dist(py, px, map[py][px]));
						}
						map[py][px] = 0;
						
						
						
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
3 6 7
1 1 0 0 0 0
1 1 0 0 1 0
1 1 0 0 4 0
4 1 0 0 1 0
1 5 1 0 1 6
1 2 8 1 1 6
1 1 1 9 2 1
4 4 15
0 0 0 0 
0 0 0 0 
0 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 5 0 
1 1 1 0 
1 1 1 9 
1 1 1 1 
1 6 1 2 
1 1 1 5 
1 1 1 1 
2 1 1 2 
4 12 15
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
*/
