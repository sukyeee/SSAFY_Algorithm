package add.add0822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_d9_2117_홈방범서비스_부울경_3반_이수경_DFS {

	static int map[][];
	static int N, M, max;
	public static void main(String[] args) throws Exception {
		

		System.setIn(new FileInputStream("src\\add\\add0822/input_d9_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=T; tc++) {
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
					for (int k = 0; k <= K; k++) {
						int cnt = 0;
						for (int r = i-k; r <= i+k; r++) {
							if(r<0 || N <= r) continue;
							for (int c = j-k; c <= j+k; c++) {
								if(r<0 || N <= r) continue;
								if(Math.abs(r-i) + Math.abs(c-j) > k) continue;
//								if(map[r][c] == 1) cnt++;
								cnt += map[r][c]; // 어차피 0 1 밖에 없으니 if문 안쓰고 더해도 됨.
							}
						}
						if(cnt*M - ( (k*k) +(k-1)*(k-1)) >= 0)max = Math.max(max, cnt);
					}
				}
			}
			
			//
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
		
		
	}

}
