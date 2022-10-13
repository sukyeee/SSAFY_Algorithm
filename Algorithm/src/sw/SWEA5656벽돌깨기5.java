package sw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656벽돌깨기5 {

	static int T, N, W, H;
	static int map[][];
	static int mapCopy[][];
	
	static boolean visit[][];
	static int tgt[];
	static int dy[] = { -1, 1,  0, 0 };
	static int dx[] = {  0, 0, -1, 1 };
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			min = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 쏘는 횟수 
			W = Integer.parseInt(st.nextToken()); // x 
			H = Integer.parseInt(st.nextToken()); // y
		
			map = new int[H][W];
			mapCopy = new int[H][W];
			visit = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					mapCopy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			tgt = new int[N];
			perm(0); // 중복 순열 
			

			System.out.println("#" +t +" " + min);
		} // testcase 
		
	}
	
	
	static void perm(int tgtIdx) {
		
		if( tgtIdx == tgt.length ) {
			
			simulation();
			return;
		}
		
		for (int i = 0; i < W; i++) {
			tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
			
		}
		
	}
	
	static void simulation() {
		
		Copy();
		
		for (int x : tgt) { // 중복순열에서 선택된 tgt 열 순서대로 

			
			// 1. 벽돌 부수기
			bomb(x);
			
			// 2. 벽돌 내리기 
			down();
			
		
		}
		
		// 3. 남은 벽돌 개수 구하기 
		countBrick();
		
	}
	
	static void bomb(int x) {
		
		int y = 0;
		while(y < H && map[y][x] == 0) y++;
		if( y == H ) return; // 부술 벽돌이 없음 
		
		// 부술 벽돌이 있다면 
		visit = new boolean[H][W];
		Deque<Dist> q = new ArrayDeque<>();
		
		// 2 이상인 것만 큐에 넣기 
		if( map[y][x] >= 2 ) q.offer(new Dist(y, x, map[y][x]));
		
		map[y][x] = 0;
		
		while(!q.isEmpty()) {
			
			Dist e = q.poll();

				for (int d = 0; d < 4; d++) {
					
					int py = e.y;
					int px = e.x;
						
					for (int i = 0; i < e.num-1; i++) {
						py += dy[d];
						px += dx[d];
						
						if( py < 0 || px < 0 || py >= H || px >= W || visit[py][px] ) continue;
						
						if( map[py][px] >= 2 ) q.offer(new Dist(py, px, map[py][px]));
						map[py][px] = 0;
						visit[py][px] = true;
					}
					
				}
			
			
		}
		
		
	}
	
	static void down() {
		
		// 블록 전체를 아래로 내리기 
		
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if( map[j][i] != 0 ) {
					st.add(map[j][i]);
					map[j][i] = 0;
				}
				
			}
			int idx = H-1;
			while(!st.isEmpty()) {
				map[idx][i] = st.pop();
				idx--;
			}
			
		}
		
	}
	static void countBrick() {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] >= 1) sum++;
			}
		}
		
		min = Math.min(min, sum);
	}
	static void Copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}
	}
	
	static class Dist {
		int y, x, num;

		public Dist(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
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
