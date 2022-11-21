package sw;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 수영대회결승전 {
	static int T,N;
	static int Ans;
	static int [][] map;
	static int[][]cpy;
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("수영대회결승전.txt"));
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N=sc.nextInt();
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c]=sc.nextInt();
					// 섬의값을 -1로 변환
					map[r][c] = map[r][c]==1?-1:map[r][c];
				}
			}
			
			//print(map);
			
			Ans = bfs(new Point(sc.nextInt(),sc.nextInt()),new Point(sc.nextInt(),sc.nextInt()));
			System.out.printf("#%d %d\n",tc,Ans);
		}
	}
	
	
	private static int bfs(Point start, Point end) {
		// bfs
		// Q 를 만들고 하나 넣고 [하나빼고 뺀놈과 연관된 노드를 Q 에 넣는다] 반복 
		Queue<Point> Q = new LinkedList();
		boolean[][] v = new boolean[N][N];
		Q.add(start);
		v[start.r][start.c] = true;
		int time = 0;
		while(!Q.isEmpty()) {
			// 지도는 3타임마다 초기화
			if(time%3==0) {
				cpy = mapCpy(map);
			}
			//print(cpy);
			int size = Q.size();
			
			for (int cnt = 0; cnt < size; cnt++) {
				Point p = Q.poll();
				if(p.r==end.r&&p.c==end.c) {
					// 도착했어요
					return time;
				}
				// 4 방탐색
				for (int d = 0; d < 4; d++) {
					int nr = p.r+dr[d];
					int nc = p.c+dc[d];
					if(nr>=0&&nr<N&&nc>=0&&nc<N&&!v[nr][nc]) {
						// 소용돌이가 있으면 대기를 탄다~~~~~~~~~~
						if(cpy[nr][nc]>=1) {
							Q.add(p);
						}
						// 그냥 바다면 고~~~~~
						if(cpy[nr][nc]==0) {
							v[nr][nc]=true;
							Q.add(new Point(nr,nc));
						}
					}
				}
				
			}
//			print(cpy);
//			System.out.println("-----------------------");
			time++;
			
			//System.out.println("-------------------------");
			// 한타임이 지날때마다 소용돌이 값을 1씩 빼준다
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(cpy[r][c]>0) {
						cpy[r][c]--;
					}
				}
			}
			
		}
		
		return -1;
	}
	private static int[][] mapCpy(int[][] map) {
		int[][]cpy = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, cpy[i], 0, map[i].length);
//			for (int j = 0; j < map[i].length; j++) {
//				cpy[i][j]=map[i][j];
//			}
		}
		return cpy;
	}

	static int [] dr = {1,-1,0,0};
	static int [] dc = {0,0,1,-1};
	static class Point{
		int r,c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}
	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}
	
	
}










