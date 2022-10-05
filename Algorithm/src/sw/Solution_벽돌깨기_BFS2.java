package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author THKim
 */
public class Solution_벽돌깨기_BFS2 {

	static int N,W,H,min;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine()," ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
		
			go(map,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	// 구슬던지기 게임
	static void go(int[][] map,int cnt) { // map: 직전구슬까지 던진 상태의 배열
		
		int result = getRemain(map); ////////////////////////////////
		if(result == 0) {  ////////////////////////////////
			min = result;
			return;
		}
		
		if(cnt==N) { // 구슬을 다 던진 상태
			// 남은 벽돌 수 카운트 최소값 갱신
			if(min>result) min = result;
			return;
		}
		
		// 구슬 던지기 (중복 순열)
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {
			// 구슬에 맞는 시작벽돌 찾기
			int r = 0;
			while(r<H && map[r][c]==0) ++r;
			
			if(r == H) { // 맞는 시작벽돌이 없는 상태
				continue;   //////////////////////////////////////////////
			}else { // 맞는 시작벽돌이 있는 상태
				copy(map,newMap);
				// 제거될 벽돌 연쇄 처리
				boom(newMap,r,c);
				// 벽돌 중력 처리
				down(newMap);
				// 다음 구슬 던지기
				go(newMap,cnt+1); ////////////////////////////////////
			}
		}
		 ///////////////////////////////////////
	}
	private static int getRemain(int[][] map) {
		int result = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if(map[r][c]>0) result++;
			}
		}
		return result;
	}

//	private static void down(int[][] map) {
//		
//		for (int c = 0; c < W; c++) {
//			int r = H-1;
//			while(r>0) {
//				if(map[r][c]==0) { // 빈칸이면 내릴 벽돌 찾기
//					int nr = r-1;
//					while(nr>0 && map[nr][c]==0) nr--;
//					
//					map[r][c] = map[nr][c];
//					map[nr][c] = 0;
//				}
//				--r;
//			}
//		}
//	}
	
	static Stack<Integer> stack = new Stack<Integer>();
	private static void down(int[][] map) {
		
		for (int c = 0; c < W; c++) {
			// 윗행부터 남은 벽돌 스택에 넣기
			for(int r=0; r<H; r++) {
				if(map[r][c]>0) {
					stack.push(map[r][c]);
					map[r][c] = 0;
				}
			}// 남은 벽돌은 스택에 들어있고 모든 칸은 빈칸 상태
			int nr = H-1;
			while(!stack.isEmpty()) {
				map[nr--][c] = stack.pop();
			}
		}
	}

	private static void boom(int[][] map, int r, int c) {// BFS
		
		Queue<Point> queue = new ArrayDeque<Point>();
		if(map[r][c]>1) {
			queue.offer(new Point(r, c, map[r][c]));
		}
		// 벽돌이 있던 자리를 0으로 변경 : 빈칸으로 만들어서 방문처리
		map[r][c] = 0; // 방문처리 ==> 제거처리 
		
		while(!queue.isEmpty()) {
			Point p = queue.poll(); // 주변에 영향주는 벽돌 정보
			
			//벽돌의 크기-1만큼 주변 벽돌(4방) 연쇄 처리
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				for (int k = 1; k < p.cnt; k++) { // 현재 방향에서 cnt-1 벽돌 보기 
					nr += dr[d];
					nc += dc[d];
					if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]>0) {
						if(map[nr][nc]>1) {
							queue.offer(new Point(nr, nc, map[nr][nc]));
						}
						// 벽돌이 있던 자리를 0으로 변경 : 빈칸으로 만들어서 방문처리
						map[nr][nc] = 0; // 방문처리 ==> 제거처리 
					}
				}
			}
		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}
	
	static class Point{
		int r,c,cnt;
		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
}
