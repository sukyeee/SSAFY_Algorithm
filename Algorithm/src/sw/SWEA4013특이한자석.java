package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4013특이한자석 {

	static int T, K;
	static int magnet[][];
	static int magnet_num;
	static int[] direction = new int[4];
	static int d;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/sw/SWEA4013특이한자석.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			
			K = Integer.parseInt(br.readLine());
			magnet = new int[4][8];
			
			for(int i=0;i<4;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int k=0;k<K;k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Arrays.fill(direction, 0);
				magnet_num = Integer.parseInt(st.nextToken())-1;
				d = Integer.parseInt(st.nextToken());
				direction[ magnet_num ] = d; 
				
				// magnet_num 1, 회전방향 1 (1 시계방향)
				// 회전 방향 정하기 
				
				// magnet_num과 방향이 다른 것만 회전!
				//  서로 붙어있는 날의 극이 같으면 회전하지 않음
				// 서로 붙어있는 날 
				//-> [0][2]와[1][6] , [1][2]와[2][6], [2][2]와[3][6] 반대로도 성립
				
				// magnet_num만 회전 방향이 정해져있음.
			
				if(magnet_num == 0) {
					if( magnet[0][2] == magnet[1][6] ) direction[1] = direction[0];
					else direction[1] = -direction[0];
					if( magnet[1][2] == magnet[2][6] ) direction[2] = direction[1];
					else direction[2] = -direction[1];
					if ( magnet[2][2] == magnet[3][6]) direction[3] = direction[2];
					else direction[3] = -direction[2];
				}
				else if(magnet_num == 1) {
					if( magnet[0][2] == magnet[1][6] ) direction[0] = direction[1];
					else direction[0] = -direction[1];
					if( magnet[1][2] == magnet[2][6] ) direction[2] = direction[1];
					else direction[2] = -direction[1];
					if ( magnet[2][2] == magnet[3][6]) direction[3] = direction[2];
					else direction[3] = -direction[2];
				}
				else if (magnet_num == 2) {
					if ( magnet[2][2] == magnet[3][6]) direction[3] = direction[2];
					else direction[3] = -direction[2];
					if( magnet[1][2] == magnet[2][6] ) direction[1] = direction[2];
					else direction[1] = -direction[2];
					if( magnet[0][2] == magnet[1][6] ) direction[0] = direction[1];
					else direction[0] = -direction[1];
				
				}
				else if( magnet_num == 3) {
					if ( magnet[2][2] == magnet[3][6]) direction[2] = direction[3];
					else direction[2] = -direction[3];
					if( magnet[1][2] == magnet[2][6] ) direction[1] = direction[2];
					else direction[1] = -direction[2];
					if( magnet[0][2] == magnet[1][6] ) direction[0] = direction[1];
					else direction[0] = -direction[1];
			
				}
			
				//회전 방향 완료
				
				// 회전 시키기
				
			
				
					// >> 오른쪽 방향으로 진행.
					// 방향이 같으면 그 이후로는 진행하지 않음
				
					for(int n=0;n<4;n++) { /// 이렇게 돌리면 안댄다!!!
						
						int temp_start = magnet[n][0];
						int temp_end = magnet[n][7];
						
						for(int i=1;i<8;i++) {
							if(direction[n] == -1) { // 반시계방향
								magnet[n][i-1] = magnet[n][i];
							}
							else if(direction[n] == 1) { // 시계 방향
								magnet[n][7-(i-1)] = magnet[n][7-i];
							}
						}
						if(direction[n] == -1) magnet[n][7] = temp_start;
						if(direction[n] == 1) magnet[n][0] = temp_end;
						
						// 방향 같으면 돌리지 않음
						if( n < 3 ) {
							if(direction[n] == direction[n+1]) break;	
						} 
						
					
					}
		
			
			}
		
			
			int sum = 0;
			if(magnet[0][0] == 1) sum += 1;
			if(magnet[1][0] == 1) sum += 2;
			if(magnet[2][0] == 1) sum += 4;
			if(magnet[3][0] == 1) sum += 8;
			
			System.out.println("#"+t+" " + sum);
						
			
			
		} // testcase
	
	
	
	}

}
