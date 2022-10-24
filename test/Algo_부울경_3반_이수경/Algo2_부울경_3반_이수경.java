import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Algo2_부울경_3반_이수경 {

	static int T;
	static int R, C;
	static char map[][];
	static char mapCopy[][];
	static boolean visit[][];
	static boolean visitCopy[][];
	static Dist start;
	static Dist end;
	static int py, px;
	static char ans;
	static int ans_y, ans_x;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static char b;
	static boolean flag;
	static int cnt;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// M은 집, Z는 유치원 도착지점
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			mapCopy = new char[R][C];
			visit = new boolean[R][C];
			visitCopy = new boolean[R][C];

			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					mapCopy[i][j] = line.charAt(j);
					if(mapCopy[i][j] == 'M') {
						start = new Dist(i, j, '0');
						visitCopy[i][j] = true;
					}
					if(mapCopy[i][j] == 'Z') {
						end = new Dist(i, j, '0');
					}
				}
			}
			
			// . 공백
			// M: 집
			// Z: 도착지점
			
			// M에서부터 시작하는 지점을 큐에 넣고 
			// M부터 Z까지 이어지는 블록이 있다면 , 결과로 해당 블록 출력 
			
			for (int i = 0; i < 7; i++) {
				copy(); // map 복사 
				simulation(i);
				if(flag) break;
			}
			
			System.out.println("#" + t + " " + (ans_y+1) + " " + (ans_x+1) + " " + b);
		} // testcase
	}
	
	static void copy() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = mapCopy[i][j];
				visit[i][j] = visitCopy[i][j];
			}
		}
	}
	static void simulation(int k) {
		Deque<Dist> q = new ArrayDeque<>();

		// k 는 현재 넣으려는 블록 모양 
		block(k);
		
		// 시작점에서 상 하 좌 우 중 이어지는 칸 구하기 
		dy = new int[4];
		dx = new int[4];
		dy[0] = -1; dy[1] = 1; dy[2] = 0; dy[3] = 0;
		dx[0] = 0; dx[1] = 0; dx[2] = -1; dx[3] = 1;
		for (int d = 0; d < 4; d++) {
			py = start.y + dy[d];
			px = start.x + dx[d];
			if( py < 0 || px < 0 || py >= R || px >= C || visit[py][px]  ) continue;

			if(map[py][px] != '.') {
				q.offer(new Dist(py, px, map[py][px]));	
				visit[py][px] = true;
			}
		}
		
		cnt = 1;
		
		// 지운 블록은 한 칸 
		while(!q.isEmpty()) {
		
			Dist e = q.poll();
			if( py == end.y && px == end.x ) { // 종료 조건 
				flag = true;
				break;
			}
			
			char direction = e.v;
			check(direction); // 블록에 따라 방향 정해짐 
			
			for (int d = 0; d < dy.length; d++) {
				py = e.y + dy[d];
				px = e.x + dx[d];
				if( py < 0 || px < 0 || py >= R || px >= C || visit[py][px]  ) continue;
				
			
				if(map[py][px] == '.' && cnt == 1) { // 블록은 한 개만 지워짐
					ans_y = py;
					ans_x = px;
					map[py][px] = b;
					visit[py][px] = true;
					cnt = 0;
				}
				q.offer(new Dist(py, px, map[py][px]));
				visit[py][px] = true;
				
			}
			
		
		}
		
		
		
		
	}
	
	static void block(int k) {
		if( k == 0 ) b = '|';
		else if ( k == 1 ) b = '-';
		else if ( k == 2 ) b = '+';
		else if ( k == 3 ) b = '1';
		else if ( k == 4 ) b = '1';
		else if ( k == 5 ) b = '2';
		else if ( k == 6 ) b = '3';
		else if ( k == 7 ) b = '4';
	}
	
	static void check(char d) {
		if( d == '|') {
			// 1번 블록 -> 상 하 이어짐 0 1	
			dy = new int[2];
			dx = new int[2];
			dy[0] = -1; dy[1] = 1;
			dx[0] = 0; dx[1] = 0;
		}
		else if ( d == '-' ) {
			// 2번 블록 -> 좌 우 이어짐 2 3 
			dy = new int[2];
			dx = new int[2];
			dy[0] = 0; dy[1] = 0;
			dx[0] = -1; dx[1] = 1;
			
		}
		else if ( d == '+' ) {
			// 3번 블록 -> 상 하 좌 우 이어짐(모두 이어져야)	
			dy = new int[4];
			dx = new int[4];
			dy[0] = -1; dy[1] = 1;	dy[2] = 0; dy[3] = 0;
			dx[0] =  0; dx[1] = 0;  dx[2] = -1; dx[3] = 1;
			
		}
		else if ( d == '1' ) {	
			// 4번 블록 -> 하 우 이어짐  1 3	
			dy = new int[2];
			dx = new int[2];
			dy[0] = 1; dy[1] = 0;
			dx[0] = 0; dx[1] = 1;
		}
		else if ( d == '2' ) {
			// 5번 블록 -> 상 우 이어짐  0 3
			dy = new int[2];
			dx = new int[2];
			dy[0] = -1; dy[1] = 0;
			dx[0] = 0; dx[1] = 1;
			
		}
		else if ( d == '3' ) {
			// 6번 블록 -> 상 좌 이어짐  0 2
			dy = new int[2];
			dx = new int[2];
			dy[0] = -1; dy[1] = 0;
			dx[0] = 0; dx[1] = -1;
		}
		else if ( d == '4' ) {
			// 7번 블록 -> 하 좌 이어짐  1 2
			dy = new int[2];
			dx = new int[2];
			dy[0] = 1; dy[1] = 0;
			dx[0] = 0; dx[1] = -1;
			
		}
	}
	static class Dist {
		int y, x;
		char v;
		public Dist(int y, int x, char v) {
			super();
			this.y = y;
			this.x = x;
			this.v = v;
		}

	}

}

