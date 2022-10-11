

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ2531회전초밥 {

	static int N, D, K, C;
	static int dish[];
	static int max;
	static int list[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시 개수 
		D = Integer.parseInt(st.nextToken()); // 초밥 가지수 
		K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 개수 
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		dish = new int[N];
		list = new int[3001];
		for (int i = 0; i < N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
	
		
		// 먹을 수 있는 초밥 가지수 최댓값 
		// 연속으로 K개 먹으면 쿠폰 발급 !
		
		// 연속으로 K개 먹을 수 없다면 X.
		// C를 포함해서 연속으로 K개 먹을 수 있다면, 그냥 최댓값은 K개.
		// C를 포함하지 않고 연속으로 K개 먹을 수 있다면 최댓값은 K+1개
		
		int cnt = 0;
		// 맨 처음에 무조건 K개만큼 넣기 
		for (int k = 0; k < K; k++) {
			if( list[ dish[k] ] == 0 ) cnt++; // 중복이 아닐 때만 카운트 
			
			list[ dish[k] ]++;
		}
		
		int start = 0;
		int end = K;
		while(true) {
			if( start == N-1 ) break;
			
			// start는 빼기 
			if( list[ dish[start] ] == 1 ) cnt--; 
			list[ dish[start] ]--;
			
			// end는 더하기 
			if( list[ dish[end%N] ] == 0 ) cnt++; // 중복이 아닐 때만 카운트 
			list[ dish[end%N] ]++;
			
			if( list[ C ] == 0 ) max = Math.max(max, cnt+1);
			
			else max = Math.max(max, cnt);
			
			
			start++;
			end++;
			
			
		}
		
		System.out.println(max);
	}

}
/*
8 30 4 30
7
9
7
30
2
7
9
25

6 6 6 6
1
2
3
4
5
6

2 2 2 2
1
1
*/