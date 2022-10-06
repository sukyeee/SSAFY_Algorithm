

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ9205맥주마시면서걸어가기 {

	static int T;
	static int N;
	
	static int matrix[][];
	static int INF = 999999;
	
	static List<Dist> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			list.clear();
			N = Integer.parseInt(br.readLine());
			matrix = new int[N+2][N+2];
			
			
			for (int i = 0; i < N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				// 집, 편의점, 락 페스티벌까지의 거리
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Dist(a, b));
			}
			// 입력 완료 
			
			
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if( getDistance(list.get(i).y, list.get(i).x, list.get(j).y, list.get(j).x ) <= 1000 ) {
						matrix[i][j] = 1;
					}
				}
			}
			
			// 플로이드 
			  for(int k = 0; k < N + 2; k++) {
		            for(int i = 0; i < N + 2; i++) {
		                for(int j = 0; j < N + 2; j++) {
		                    if(matrix[i][k] == 1 && matrix[k][j] == 1) {
		                        matrix[i][j] = 1;
		                    }
		                }
		            }
		        }
			System.out.println((matrix[0][N+1] == 1 ? "happy" : "sad"));
			
			
		} // testcase 
		
	}
	
	static int getDistance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

}
/*
2
2
0 0
1000 0
1000 1000
2000 1000
2
0 0
1000 0
2000 1000
2000 2000
*/