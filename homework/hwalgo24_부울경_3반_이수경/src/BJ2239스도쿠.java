

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2239스도쿠 {

	static int map[][] = new int[10][10];
	static boolean col[][] = new boolean[10][10];
	static boolean row[][] = new boolean[10][10];
	static boolean square[][] = new boolean[10][10];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = line.charAt(j) - '0';
				
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] > 0) {
					row[i][map[i][j]] = true; // 같은 행에서 map[i][j](= n) 이 존재한다면[i][n] = true 
					col[j][map[i][j]] = true; // 같은 열에서 map[i][j](= n) 이 존재한다면[j][n] = true
					
					// cnt/9 , cnt%9 로 행과 열을 찾을 수 있다.
					int temp = ( i / 3 ) * 3 + ( j / 3 ); // square 위치 
					square[temp][map[i][j]] = true; // square안에서 map[i][j](= n) 이 존재한다면 square[temp][n] = true
					
					
				}
			}
		}
		
		
		
		
		dfs(0);
		
		
		
	}

	static void dfs(int cnt) {
		
		if(flag) return;
		if( cnt == 81 )	{
			flag = true; 
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			return;
		}
		
		
		int y = cnt / 9;
		int x = cnt % 9;
		
		
		if( map[y][x] == 0 ) { // 빈 자리에만 
			
			for (int i = 1; i <= 9; i++) {
				int temp = ( y / 3 ) * 3 + x / 3;
				if( row[y][i] || col[x][i] || square[temp][i]  )continue;
				
				// 숫자 표시 
				row[y][i] = col[x][i] = square[temp][i] = true; 
				map[y][x] = i;
				
				dfs(cnt + 1);
				
				// 숫자 표시 되돌리기 
				row[y][i] = col[x][i] = square[temp][i] = false; 
				map[y][x] = 0;
				
				
			}
			
		}
		else dfs(cnt + 1);
		
		
		
	}
}