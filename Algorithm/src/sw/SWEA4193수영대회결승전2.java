package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA4193수영대회결승전2 {

	static int T;
	static int N;
	static int map[][]; // 수영장 
	static int mapCopy[][];
	static boolean visit[][];
	static int A, B, C, D;
	static int ans; // 이동시간 
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			ans = -1;
			
			N = Integer.parseInt(br.readLine());
			mapCopy = new int[N][N];
			map = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mapCopy[i][j] = Integer.parseInt(st.nextToken());
					if(mapCopy[i][j] == 1 ) visit[i][j] = true;
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 시작위치 A, B 
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 도착위치 C, D
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			
			// 이거풀기 
			test();
			
			System.out.println("#" + t +  " " + ans);

		} // testacse
	}
	
	static void test() {
		

		
		
		
	}
	static void copyOfMap(int[][] mapCopy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = mapCopy[i][j];
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
/*
3
5
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0
2 0
6
0 0 0 0 0 0
0 1 1 0 0 0
0 0 0 1 2 0
1 1 0 1 0 1
0 0 0 1 0 1
0 0 0 2 0 1
5 0
2 5
6
0 0 0 0 0 0
0 0 0 0 0 0
1 0 1 1 1 0
1 0 0 0 0 0
1 0 1 1 1 0
0 0 2 0 2 0
5 0
3 5
*/