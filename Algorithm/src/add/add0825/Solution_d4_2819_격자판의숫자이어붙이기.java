package add.add0825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_d4_2819_격자판의숫자이어붙이기 {

	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우 
	static int[] dj = {  0, 0, -1, 1 };
	static int N, M, max;
	static int[][] a;
	static Set<Integer> set;
	
	static void dfs(int i, int j,int cnt, int n) {
		if(cnt == 7) {
			set.add(n);
			return;
		}
		
		for (int d=0;d<4;d++) {
			int ni = i+di[d];
			int nj = j+dj[d];
			
			if(0 <= ni && ni < 4 && 0 <= nj && nj < 4) {
				dfs(ni, nj, cnt+1, n*10 + a[ni][nj]);
			}
		}
		
	}
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("src\\add\\add0822/input_d9_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			set = new HashSet<>();
			a = new int[4][4];
		
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 1, a[i][j]);
				}
			}
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
			
			
		}

		System.out.println(sb);
		br.close();

	}

}
