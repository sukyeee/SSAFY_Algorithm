package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1987알파벳 {

	static int R, C;
	static char alphabet[][];
	static int dy[] = { -1, 1, 0, 0}; // 상 하 좌 우 
	static int dx[] = {  0, 0,-1, 1};
	static List<Character> list = new ArrayList<>(); // 지나온 알파벳
	static boolean isVisited[][];
	static int max;
	static boolean flag; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alphabet = new char[R][C];
		isVisited = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				alphabet[i][j] = line.charAt(j);
			}
		}
		
		
		dfs(0,0, 1); // y, x , cnt
		
		
		System.out.println(max);
	}
	
	static void dfs(int y, int x, int cnt) {
		
		max = Math.max(max, cnt); 
	
		// 새로 이동한 칸에 적혀 있는 알파벳은, 지금까지 지나온 모든 칸에 적혀있는 알파벳과 달라야 함.
		if(isVisited[y][x]) return;
		isVisited[y][x] = true;

		
		list.add(alphabet[y][x]); // 방문한 알파벳들 저장
		
	
		for(int d=0;d<4;d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || py >= R || px < 0 || px >= C || isVisited[py][px] || list.contains(alphabet[py][px])) continue;
			dfs(py, px, cnt+1);
			list.remove((Character)alphabet[py][px]);
			isVisited[py][px] = false;

		}
		
	}

}
