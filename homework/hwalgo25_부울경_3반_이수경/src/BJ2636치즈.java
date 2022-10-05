

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2636치즈 {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static List<Dist> list = new ArrayList<>();
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = {  0, 0,-1, 1 };
	static int time, cheese, ans;
	static List<Integer> result = new ArrayList<>();
	static Deque<Dist> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 치즈가 없으면 종료 
			
			bfs();
			time++;
			
			if(check()) break;
			
				
		}
		
		System.out.println(time - 1);
		System.out.println(ans);
	}
	
	
	static void bfs() {
		
		visit = new boolean[N][M];
		
		q.offer(new Dist(0,0));
		visit[0][0] = true;

		cheese = 0;
		while(!q.isEmpty()) {
		
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
				Dist e = q.poll();
				for (int d = 0; d < 4; d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					if( py < 0 || px < 0 || py >= N || px >= M || visit[py][px] ) continue;
					
					if(map[py][px] == 1) { // 인접한 부분
						map[py][px] = 0; // 치즈 제거 
						cheese++;
					}
					else { // 0이라면 계속 탐색 
						q.offer(new Dist(py, px));
					}
					visit[py][px] = true;
					
				}
			}
			
		}
		
	}
	static boolean check() {
		if(cheese == 0) 	
			return true;
		
		else {
			ans = cheese;
			return false;
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
