package bj;

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
	static int time;
	static List<Integer> result = new ArrayList<>();

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
		
		bfs();
		
		System.out.println(time-1);
//		System.out.println(result.get(time-2));
	}
	
	static void bfs() {
		

		Deque<Dist> q = new ArrayDeque<>();
		
		// 0 인 부분에 대해 bfs 
		// 상 하 좌 우 에서 처음 만나는 0 좌표들에 대해서 큐에 다 집어넣기 
		

		// 왼쪽에서 부터
		for (int i = 0; i < N; i++) {
			int x = 0;
			while(x < M && map[i][x] == 0 ) x++;
			if(x != M) {
				if(x == M) continue;
				x--; // 0 인 부분이어야 하므로 
				
				if(visit[i][x]) continue;
				q.offer(new Dist(i, x));
				visit[i][x] = true;
			}
			
		
		}
		 
		// 오른쪽에서 부터
		for (int i = 0; i < N; i++) {
			int x = M-1;
			while(x > 0 && map[i][x] == 0 ) x--;
			if(x != M) {
				if(x == 0) continue;
				x++; // 0 인 부분이어야 하므로 
				if(visit[i][x]) continue;
				q.offer(new Dist(i, x));
				visit[i][x] = true;
			}
			
			

		}
		// 아래쪽에서 부터
		for (int i = 0; i < 0; i++) {
			int y = N-1;
			while(y > 0 && map[y][i] == 0 ) y--;
			if(y != N) {
				if(y == 0) continue;
				y++; // 0 인 부분이어야 하므로 
				
				if(visit[y][i]) continue;
				q.offer(new Dist(y, i));
				visit[y][i] = true;
			}
			
		}
		// 위쪽에서 부터
		for (int i = 0; i < 0; i++) {
			int y = 0;
			while(y < N && map[y][i] == 0 ) y++;
			if(y != N) {
				if(y == N) continue;
				y--; // 0 인 부분이어야 하므로 
				if(visit[y][i]) continue;
				q.offer(new Dist(y, i));
				visit[y][i] = true;
			}
			
			
		}
		
		
		while(!q.isEmpty()) {
		
			time++;
			int q_size = q.size();
			result.add(q_size);
			for (int i = 0; i < q_size; i++) {
				Dist e = q.poll();
				for (int d = 0; d < 4; d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					if( py < 0 || px < 0 || py >= N || px >= M || visit[py][px] || map[py][px] == 0 ) continue;
					
					q.offer(new Dist(py, px));
					visit[py][px] = true;
					map[py][px] = 0;
				}
			}
			
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
