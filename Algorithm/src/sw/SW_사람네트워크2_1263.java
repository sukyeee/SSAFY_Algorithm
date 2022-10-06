package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_사람네트워크2_1263 {

	static int T, N;
	static final int BIG = 999999;
	static int[][] matrix; // <- dp
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			matrix = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					if( n == 0 && i != j ) matrix[i][j] = BIG;
					else matrix[i][j] = n;
				}
			}
			
			// 플로이드 워셜 알고리즘 적용 
			// mtrix[][] 계속 갱신 <= 3중 for문 <= 경유지 k 먼저
			// a -> b 은 이미 주어져 있다.
			// a -> b 로 가는 비용을  a -> k -> b ( a -> k 비용 + k -> b 비용 ) 비용과 비교 최솟값 선택 
			
			for (int k = 0; k < N; k++) { // 경유지 
				for (int i = 0; i < N; i++) {
					if( i == k ) continue;
					for (int j = 0; j < N; j++) {
						if( i == j ) continue;
						matrix[i][j] = Math.min(matrix[i][k] + matrix[k][j], matrix[i][j] );
					}
				}
			}
			
			// 최종적으로 matrix 완성되면 
			// a, b, c, d가 있을 경우,
			// a -> b, a -> c, a -> d 을 모두 합치고 그것과 다시 
			// b -> a, b -> c, b -> d 을 모두 합치고 그것과 다시 
			// .... 이중 가장 적은 비용을 선택 
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += matrix[i][j];
				}
				min = Math.min(min, sum);
			}
			
			System.out.println(min);
		}// testcase
		
	}

}

