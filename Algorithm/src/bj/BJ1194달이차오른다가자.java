package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1194달이차오른다가자 {

	static int N, M;
	static char map[][];
	static boolean visit[][];
	static Dist minsik;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int min;
	static List<Character> key = new ArrayList<>(); // 키 저장

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == '0') minsik = new Dist(i,j, key, 0);
			}
		}
		
		// . 빈칸 
		// # 벽
		// 열쇠 a b c d e f
		// 문 A B C D E F 
		// 민식이 현재 위치 0
		// 출구 1 
		
		// A 게이트는 a 열쇠가 없으면 되돌아 가야함 
	
		bfs(minsik.y, minsik.x);
		

		System.out.println(min);
		
	}
	
	static void bfs(int y, int x) {
	
		Deque<Dist> q = new ArrayDeque<>();
		
		q.offer(new Dist(y, x, key, 0));

		
		while(!q.isEmpty()) {
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
				Dist e = q.poll();
		
				// 출구 도달하는데 까지 최솟값 
				if( map[e.y][e.x] == '1' ) {
					min = Math.min(min, e.cnt);
					return;
				}
				
				for (int d = 0; d < 4; d++) {

					int py = e.y + dy[d];
					int px = e.x + dx[d];
					if( py < 0 || px < 0 || py >= N || px >= M  || map[py][px] == '#') continue;

					// a ~ f ( 열쇠 )
					if(map[py][px] >= 97 && map[py][px] <= 102 ) {
						e.key.add(map[py][px]);
					}
					// A ~ F ( 문 ) 
					else if ( map[py][px] >= 65 && map[py][px] <= 70) {
						if(e.key.contains( (char)(map[py][px] + 32) )) { // 해당 키를 가지고 있지 않으면
							q.offer(new Dist(py, px, e.key, e.cnt + 1));
						}
						else continue;
					}
					
					q.offer(new Dist(py, px, e.key, e.cnt + 1));
					
				}
			}
			
		
			
		}
		
		
	
	
	
		
	}
	static class Dist {
		int y, x;
		List<Character> key = new ArrayList<>(); // 키 저장
		int cnt;
		public Dist(int y, int x, List<Character> key, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.key = key;
			this.cnt = cnt;
		}
		
		
		
	}

}
