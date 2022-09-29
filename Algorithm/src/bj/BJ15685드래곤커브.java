package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15685드래곤커브 {

	static int N;
	static int x, y, d, g;
	static int size = 6;
	static boolean map[][] = new boolean[size][size];
	static int dy[] = { 0, -1, 0, 1 }; // 우 상 좌 하 
	static int dx[] = { 1, 0, -1, 0 };
	static List<Dist> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// X 축은 → 방향, y축은 ↓ 방향 
		
		// 1. 시작 점
		// 2. 시작 방향
		// 3. 세대 
		
		// 0세대 드래곤 커브 ㅡ
		// 1세대 드래곤 커브 
		
		for (int t = 0; t < N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken()); // 시작점 x
			y = Integer.parseInt(st.nextToken()); // 시작점  y
			d = Integer.parseInt(st.nextToken()); // 시작 방향
			g = Integer.parseInt(st.nextToken()); // 세대 
			
			// 3 3 0 1
			
			// 시작점 초기화 
			int py = y + dy[d];
			int px = x + dx[d];
			map[y][x ] = true;
			map[py][px] = true;
			list.add(new Dist(y, x, d));
			list.add(new Dist(py, px, d));
			
			// d = 0 ( 오른쪽 ) 일 떄는 d = 1 (위쪽)
			// d = 1 ( 위쪽 ) 일 때는 d = 2 ( 왼쪽 )
			// d = 2 ( 왼쪽) 일 때는 d = 3 ( 아래쪽 ) 
			// d = 3 ( 아래쪽) 일 떄는 d = 0 ( 오른쪽 )
			
			// 끝점 기준 -> 끝점 기준으로 잡고 돌림 
			for (int i = 0; i < g; i++) { // 세대 만큼 반복 
				
				// 리스트의 마지막이 끝점 
				

				
				
			}
			
			
			
		} // testcase
		
	}
	
	static class Dist {
		int y, x, d;

		public Dist(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}

}
