package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4831전기버스 {

	static int T;
	static int K, N, M;
	static int bus[];
	static int ans, start;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = start = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); // 한 번 충전으로 최대 이동할 수 있 정류장 수 
			N = Integer.parseInt(st.nextToken()); // 정류장 마지막 번호 
			M = Integer.parseInt(st.nextToken());	// 충전정류장 개수 
			
			bus = new int[M+2];
			
			st = new StringTokenizer(br.readLine());
			bus[0] = 0;
			for (int i = 1; i <= M; i++) {
				bus[i] = Integer.parseInt(st.nextToken());
			}
			bus[M+1] = N;
			
			
			for (int i = 1; i < M+2; i++) {
				
				if(bus[i] - bus[i-1] > K) {
					ans = 0;
					break;
				}
				if(bus[i] - start > K) {
					start = bus[i-1];
					ans++;
				}

			}
			
			System.out.println("#" + t + " " + ans);
			
		}// testcase
		
	}
}
/*
3
3 10 5
1 3 5 7 9
3 10 5
1 3 7 8 9
5 20 5
4 7 9 14 17

*/