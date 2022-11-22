package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5208전기버스2 {

	static int T, N;
	static int max_station[];
	static int battery[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			max_station = new int[N];
			battery = new int[N];

			for (int i = 1; i < N; i++) {
				battery[i] = Integer.parseInt(st.nextToken()); 
			}
			
			// 충전지로 갈 수 있는 거리 
			// 현재 정류장에서 충전한다면 최대 어디까지 갈 수 있는지 ?, 0번은 비워두기.
			for (int i = 1; i < N; i++) {
				max_station[i] = battery[i] + i;
			}
			
		 
			int target = N; // 원하는 도착 지점
			int cnt = -1;
			int j = 1;
			while(target > 1) {
				 
				if( max_station[j] >= target ) { // 타겟까지 도착할 수 있는 정류장이 있다면 ? 그 정류장을 타겟으로 다시 순회 ,
					cnt++;
					target = j;
					j = 1;
				}
				else j++;
	
			}
		
			
			System.out.println("#" + t + " " + cnt);
			
		} // testcase
		
		

	}

}
