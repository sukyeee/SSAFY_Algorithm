package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949등산로조성 {
	
	private static int N, K, maxLength, maxHeight, map[][];
	private static boolean[][] visit;
	private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			maxLength = maxHeight = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (maxHeight < map[i][j])
						maxHeight = map[i][j];
				}
			}
			startPoint();
			sb.append("#" + t + " " + maxLength + "\n");
		}
		System.out.println(sb);
	}

	private static void startPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == maxHeight) {
					visit[i][j] = true;
					dfs(i, j, maxHeight, 1, 1);
					visit[i][j] = false;
				}
			}
		}
	}

	private static void dfs(int y, int x, int height, int length, int shaveCount) {
		
		for (int d = 0; d < 4; d++) {
			
			maxLength = Math.max(maxLength, length);
			
			int py = y + dy[d];
			int px = x + dx[d];
			
			if( py < 0 || px < 0 || py >= N || px >= N || visit[py][px] ) continue;
			
			if( map[py][px] >= height ) { // 원래 갈 수 없는 길일 때
				if( shaveCount == 1 ) { // 깎을 수 있는 기회가 있다면 
					
					if( map[py][px] - K < height ){
						visit[py][px] = true;
						dfs(py, px, height - 1, length + 1, shaveCount - 1);
						visit[py][px] = false;	
					}
					
					
				}
				
			}
			else { // 갈 수 있는 길 
				visit[py][px] = true;
				dfs(py, px, map[py][px], length + 1, shaveCount);
				visit[py][px] = false;
			}
			
			
		}
		
		
		
		
	}
}