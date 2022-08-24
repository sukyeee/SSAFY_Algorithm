package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ3055탈출 {

	static int R, C;
	static char map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 }; // 위 아래 오른쪽 왼쪽
	static int dx[] = {  0, 0, 1,-1 };
	static Deque<Dist> q = new ArrayDeque<>(); // 고슴도치 이동 루트
//	static Deque<Dist> water = new ArrayDeque<>(); // 물 이동 루트
	static boolean flag;
	static int cnt;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완
		
		// 물부터 넣고 도치 넣기 
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j] == '*') {
					q.add(new Dist(i,j, '*'));
					visit[i][j] = true;
				}
			}
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j] == 'S') {
					q.add(new Dist(i,j, 'S'));
					visit[i][j] = true;
				}
			}
		}
		
		while( !q.isEmpty() ) {
			if(flag) break;
			int q_size = q.size();
			cnt++;
			for(int i=0;i<q_size;i++) {
				Dist d = q.poll();
				
				for(int k=0;k<4;k++) {
					int py = d.y + dy[k];
					int px = d.x + dx[k];
					
					if( py < 0 || py >= R || px < 0 || px >= C
							|| visit[py][px] || map[py][px] == 'X') continue;
					
					if(map[py][px] == 'D' && d.value == '*' )continue;
					if(map[py][px] == 'D' && d.value == 'S') {
						flag = true;
						break;
					}
					
					visit[py][px] = true;
					q.add(new Dist(py, px, d.value));
		
				}
			}
			
			
		}
		if(flag)System.out.println(cnt);
		else System.out.println("KAKTUS");
		
		
	}

	static class Dist {
		int y, x;
		char value;
	

		public Dist(int y, int x, char value) {
			super();
			this.y = y;
			this.x = x;
			this.value = value;

		}
		
	}
}
