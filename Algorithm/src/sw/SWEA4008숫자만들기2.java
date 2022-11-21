package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008숫자만들기2 {

	static int T, N;
	static int oper[] = new int[4];
	static int num[];
	static int max, min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			num = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			// + - * / 
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			// 수식에 사용되는 숫자 
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			//입력 완
			
			
			dfs(1, num[0]); // 현재 인덱스, 현재까지의 합 
			
			System.out.println(max - min);
		} //testcase
		
	}
	static void dfs(int k, int sum) {
		
		if(k == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				if(i == 0) dfs(k+1, sum + num[k]);
				else if(i == 1)dfs(k+1, sum - num[k]);
					else if(i == 2)dfs(k+1, sum * num[k]);
						else if(i == 3)dfs(k+1, sum / num[k]);
				
				oper[i]++;
				
				
			}
		}
		
	}
		
}
/*
10
5
2 1 0 1
3 5 3 7 9
6
4 1 0 0
1 2 3 4 5 6 
5
1 1 1 1
9 9 9 9 9 
6
1 4 0 0
1 2 3 4 5 6 
4
0 2 1 0
1 9 8 6
6
2 1 1 1
7 4 4 1 9 3 
7
1 4 1 0
2 1 6 7 6 5 8 
8
1 1 3 2
9 2 5 3 4 9 5 6 
10
1 1 5 2
8 5 6 8 9 2 6 4 3 2 
12
2 1 6 2
2 3 7 9 4 5 1 9 2 5 6 4 
*/