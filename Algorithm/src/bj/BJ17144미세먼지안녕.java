package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BJ17144미세먼지안녕 {

	static int R,C,T;
	static int map[][];
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 
	static int dx[] = {  0, 0,-1, 1 };
	static boolean visit[][];
	static int dust;
	static int dust_map[][]; 
	static int circulater_y = -1;
	static int ans;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/bj/BJ17144미세먼지안녕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && circulater_y == -1) {
					circulater_y = i; // i+1 까지 공기청정기존재, x는 항상 0
				}
			}
		}
		// 입력 완 
		
		for (int i = 0; i < T; i++) {
			dust_map = new int[R][C];
			
			// 미세먼지 확산
			spread();
			
			// 공기청정기 작동
			circulater();
		
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1)ans += map[i][j];
			}
		}
		
		System.out.println(ans);
	
	} // main
	

	static void spread() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				if( map[i][j] < 0 ) continue; // 공기청정기와 빈공간은 제외
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int py = i + dy[d];
					int px = j + dx[d];
					
					if(py < 0 || px < 0 || py >= R || px >= C || map[py][px] == -1) continue;
					dust = map[i][j]/5 ;
					dust_map[py][px] += dust;
					cnt++;
				}
				
				dust_map[i][j] -= (dust * cnt);
				
			}
		}
	
		// 기존 배열에 더해주기
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += dust_map[i][j];
			}
		}
		
	}
	
	static void circulater() {
		int right, left, top, bottom;
		
			// 공기청정기 기준 위쪽: 반시계 방향
			
				// 1번. 먼지들 동쪽으로 이동 
				right = map[circulater_y][C-1]; // 맨 오른쪽 아래 먼지 
				for (int i = C-1; i > 1; i--) {
					//왼쪽이 오른쪽으로
					map[circulater_y][i] = map[circulater_y][i-1];
				}
				
				// 2번. 먼지들 위쪽으로 이동
				
				top = map[0][C-1];			// 맨 오른쪽 위 먼지 
				
				for (int i = 0; i < circulater_y; i++) {
					// 밑에서 위로 
					map[i][C-1] = map[i+1][C-1];
				}
				map[circulater_y-1][C-1] = right;
				
				left = map[0][0];			// 맨 왼쪽 위 먼지 
				
				// 3번. 먼지들 왼쪽으로 이동
				for (int i = 0; i < C-1; i++) {
					// 오른쪽에서 왼쪽으로 
					map[0][i] = map[0][i+1];
				}
				map[0][C-2] = top;
				
				
				// bottom 은 공기청정기 속으로 사라지기 때문에 필요없음!
				
				// 4번. 먼지들 아래쪽으로 이동
				for (int i = circulater_y-2; i > 0; i--) {
					// 위에서 아래로
					map[i+1][0] = map[i][0];
				}
				map[1][0] = left;
				
				map[circulater_y][1] = 0;
				
		// 공기청정기 기준 아래쪽 : 시계 방향 ----------------------------------------
				
				circulater_y++;
				
				// 1번. 먼지들 동쪽으로 이동 
				right = map[circulater_y][C-1]; // 맨 오른쪽 아래 먼지 
				for (int i = C-1; i > 1; i--) {
					//왼쪽이 오른쪽으로
					map[circulater_y][i] = map[circulater_y][i-1];
				}
				
				// 2번. 먼지들 아래쪽으로 이동
				bottom = map[R-1][C-1];
				
				for (int i = R-1; i > circulater_y; i--) {
					// 위에서 아래로
					map[i][C-1] = map[i-1][C-1];
				}
				map[circulater_y+1][C-1] = right;
				
				
				
				// 3번. 먼지들 왼쪽으로 이동
				left = map[R-1][0];
				
				for (int i = 0; i < C-1; i++) {
					// 오른쪽에서 왼쪽으로 
					map[R-1][i] = map[R-1][i+1];
				}
				map[R-1][C-2] = bottom;
				
				
				// bottom 은 공기청정기 속으로 사라지기 때문에 필요없음!
				
				// 4번. 먼지들 위쪽으로 이동 	
				for (int i = circulater_y+1; i < R-1; i++) {
					// 밑에서 위로 
					map[i][0] = map[i+1][0];
				}
				map[R-2][0] = left;
				
		
				map[circulater_y][1] = 0;
				
				circulater_y--;// 공기청정기 원래 위치로 복구
				
	}
}
