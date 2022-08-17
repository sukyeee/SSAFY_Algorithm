
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 데일리과제_Algorithm_13_1992 {
	static int N;
	static int map[][];
	static StringBuilder sb = new StringBuilder();
	static int one_cnt;
	static int zero_cnt;
	static int r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완
		quad(0, 0, N);

		System.out.println(sb);

	}

	static void quad(int c, int r, int N) {

		if(N == 0) return;
		
		int one_cnt = 0;
		int zero_cnt = 0;
		
		// 1또는 0으로 압축 가능한지 판별 
		for (int i = c; i < c + N; i++) {
			for (int j = r; j < r + N; j++) {
				if(map[i][j] == 1) one_cnt++;
				if(map[i][j] == 0) zero_cnt++;
			}
		}
		if (one_cnt == N * N ) {  // 1로 압축 가능한 경우 
			sb.append(1);
			return;
		}
		if (zero_cnt == N * N ) { 	// 0으로 압축 가능한 경우 
			sb.append(0);
			return;
		}	
		else { // 압축 불가능한 경우 
			sb.append('(');
			
			// 1사분면
			quad(c, r, N/2 );

			// 2사분면
			quad(c, r + N/2, N/2 );

			// 3사분면
			quad(c + N/2, r, N/2 );

			// 4사분면
			quad(c + N/2, r + N/2, N/2 );
			
			sb.append(')');
		}
		
		

	}

}
