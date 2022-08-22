package add.add0822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_d9_2117_홈방범서비스_부울경_3반_이수경_BFS {

	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌, 시계방향
	static int[] dj = { 0, 1, 0, -1 };
	static int N, M, max;
	static int[][] map;

	static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque();
		// 속도 차이가 나면 ArrayDeque 만들어야 함! 링크드 리스트 아니고.
		boolean[][] v = new boolean[N][N];
		v[i][j] = true;
		q.offer(new int[] { i, j });
		int cnt = map[i][j];
		while (!q.isEmpty()) {

			for (int k = 1; k < 2 * N; k++) {
				int price = M*cnt-( (k*k) + (k-1)*(k-1));
				if(price >= 0 ) max = Math.max(max, cnt);
				int size = q.size();
				
				for (int s = 0; s < size; s++) {
					int[] ij = q.poll();
					i = ij[0];
					j = ij[1];
					for (int d = 0; d < 4; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (0 <= ni && ni < N && 0 <= nj && nj < N && !v[ni][nj]) {
							v[ni][nj] = true;
							q.offer(new int[] { ni, nj });
//							if(map[ni][nj] == 1) cnt++;
							cnt += map[ni][nj];
						}
					}
				}
				
				

			}
			

		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src\\add\\add0822/input_d9_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int K = N; // + 서비스 영역 반지름
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			for(int[] a:map) System.out.println(Arrays.toString(a));

			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			//
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}

		System.out.println(sb.toString());
		br.close();

	}

}
