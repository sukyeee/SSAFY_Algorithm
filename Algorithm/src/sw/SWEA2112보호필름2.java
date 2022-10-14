package sw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2112보호필름2 {
	static int D, W, K;
	static int[][] map;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0); // k 는 행 한줄 의미, cnt는 약물 투여 횟수 
			
			System.out.println("#" + test_case + " " + min);
		} // testcase

		System.out.print(sb);
	}
	
	static void dfs(int k, int cnt) {
		
		
		if( cnt >= min ) return; // 최솟값을 넘어가면 의미 없음 
		
		if( k == D ) {
			
			loop : for (int j = 0; j < W; j++) {
				int same = 1;
				for (int i = 0; i < D - 1; i++) {
					if(map[i][j] == map[i+1][j]) same++;
					else same = 1;
					
					if( same >= K ) continue loop; // 한 번이라도 만족하면 통과 
				}
				return; // 여기까지 내려왔다는 것은 한 열에 same개수를 만족하지 못한다는 의미 				
			}
		
			min = Math.min(min, cnt);
			return;
		}
		
		
		int temp[] = map[k].clone(); // 한 행씩 복사 
		
		
		// 약물 투여 하지 않는 경우 
		dfs(k+1, cnt);
		
		// 약물 A 투여 하는 경우
		Arrays.fill(map[k], 0);
		dfs(k+1, cnt+1);
		
		// 약물 B 투여 하는 경우 
		Arrays.fill(map[k], 1);
		dfs(k+1, cnt+1);
		
		map[k] = temp; // 백트래킹( 원상복구 )
		
		
	}

	
}