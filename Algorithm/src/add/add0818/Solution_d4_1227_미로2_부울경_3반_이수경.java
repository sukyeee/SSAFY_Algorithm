package add.add0818;

import java.io.*;
import java.util.*;

// A형의 가장 기본적인 코드
public class Solution_d4_1227_미로2_부울경_3반_이수경 {

	static int[] dy = {0,  1, 0, -1}; // 우 하 좌 상
	static int[] dx = {1,  0,-1, 0}; // 우 하 좌 상
	static int[][] map;
	static boolean[][] v;
	static int find, N = 100;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/add/add0818/input_d4_1227.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력할 때 stringBuilder 쓰기
		
	
		for (int tc = 1; tc <= 10; tc++) {
			map = new int[N][N];
			v = new boolean[N][N];
			
			int t = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {			
					map[i][j] = s.charAt(j) - '0';
				}
			}
//			for (int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
			
			find = 0;
			bfs(1,1);
//			dfs(1,1);
			sb.append("#").append(tc).append(" ").append(find).append("\n");
			
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static void dfs( int i, int j ) {
	
		v[i][j] = true;
		if(map[i][j] == 3) {
			find = 1;
			return;
		}
		
		for(int d=0; d<4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if( 0 <= ny && ny < N && 0 <= nx && nx < N && !v[ny][nx] && map[ny][nx] != 1) {
				dfs(ny, nx);
			}
		}
		
	}
	
	static void bfs(int i, int j) {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		v[i][j] = true;
		q.offer(new int[]{i, j});
		while(!q.isEmpty()) {
			
			int[] ij = q.poll();
			i = ij[0];
			j = ij[1];
			if(map[i][j] == 3) {
				find = 1;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int ny = i + dy[d];
				int nx = j + dx[d];
				if( 0 <= ny && ny < N && 0 <= nx && nx < N && !v[ny][nx] && map[ny][nx] != 1) {
					v[ny][nx] = true;
					q.offer(new int[] {ny, nx});
				}
			}
			
			
		}
		
		
	}

}
