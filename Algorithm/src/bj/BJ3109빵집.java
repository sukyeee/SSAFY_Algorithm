package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3109빵집 {

	static int R, C;
	static char[][] map;
	static boolean isVisited[][];
	static int dx[] = { 1,  1,  1 }; // 오른쪽, 오른쪽 위, 오른쪽 아래
	static int dy[] = { -1, 0, 1 };
	static int ans;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isVisited = new boolean[R][C];
		
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완
		
		for(int i=0;i<R;i++) {
			
				if(map[i][0] == '.') {
					dfs(i, 0, 0);
					flag = false;
				}
		}
		
		System.out.println(ans);
		
		
	}

	static void dfs(int i, int j, int depth) {
		
		if(isVisited[i][j]) return;
		isVisited[i][j] = true;
		
		if(depth == C-1)	{
			ans++;
			flag = true;
			return;
		}
		
		
		for(int d=0;d<3;d++) {
			int px = i + dy[d];
			int py = j + dx[d];
			
			if( px < 0 || px >= R || py < 0 || py >= C || map[px][py] == 'x' || isVisited[px][py])continue;
			dfs(px, py, depth+1);
			if(flag) return;
		}
		
		
	}
	
}

