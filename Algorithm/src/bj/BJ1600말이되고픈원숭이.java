package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ1600말이되고픈원숭이 {

	static int K, W, H;
	static int map[][];
	static boolean visit[][][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int hdy[] = { -2, -2, 2, 2, -1, -1, 1, 1 }; // 말처럼 이동하는 경우
	static int hdx[] = {  1, -1, 1, -1,-2,  2,-2, 2 };
	static int min = Integer.MAX_VALUE;
	static Deque<Dist> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visit = new boolean[H][W][K+1]; // (x,y) 지점에 능력을 n번 사용해서 왔다 
		for (int i = 0; i < H; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완 
		
		
		q.offer(new Dist(0,0, 0,0));
		
		while(!q.isEmpty()) {
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
			
				Dist e = q.poll();
				if( e.y == H-1 && e.x == W-1 )min = Math.min(min, e.m);
				
				
				// 말으로 이동
				if(e.k < K) {
					for (int d = 0; d < 8; d++) {
						int py = e.y + hdy[d];
						int px = e.x + hdx[d];
						if(py < 0 || py >= H || px < 0 || px >= W || map[py][px] == 1 || visit[py][px][e.k+1]) continue;
						
					
						visit[py][px][e.k+1] = true;
						q.offer(new Dist(py, px, e.m+1, e.k+1));
					}
					
				}
				
				// 원숭이 이동
				for (int d = 0; d < 4; d++) {
				
					int py = e.y + dy[d];
					int px = e.x + dx[d];
			
					if(py < 0 || py >= H || px < 0 || px >= W || map[py][px] == 1 || visit[py][px][e.k] ) continue;
					
					visit[py][px][e.k] = true;
					q.offer(new Dist(py, px, e.m+1, e.k));
				
				}
				
			}
				
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	
		
	}
	
	static class Dist {
		int y, x, m, k;

		public Dist(int y, int x, int m, int k) {
			super();
			this.y = y;
			this.x = x;
			this.m = m;
			this.k = k;
		}

		
		
	}

}
