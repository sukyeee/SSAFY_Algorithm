package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA2819격자판의숫자이어붙이기 {

	static int T, N;
	static char map[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static HashSet<String> s = new HashSet<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		map = new char[4][4];
		for (int t = 1; t <= T; t++) {
			
			s.clear();
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 0, "");
				}
			}
			
			System.out.println("#" + t + " " + s.size());
		} // testcase
			

	}
	static void dfs(int y, int x, int depth, String num) {
		
		if(depth == 7) {
			s.add(num);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py<0 || px < 0 || py >= 4 || px >= 4) continue;
			
			dfs(py, px, depth + 1, num + map[py][px]);
		}
	}

}

/*
1
1 1 1 1
1 1 1 2
1 1 2 1
1 1 1 1
 */

