package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_ABCDE_13023_2 {

	static int N, M;
//	static boolean[][] friends; // matrix
	// visit -> bit masking 으로 대체
//	static List<List<Integer>> friends = new ArrayList<List<Integer>>();
	static List<List<Integer>> friends = new ArrayList<>();
	static boolean done; // 성공 여부
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		friends = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			friends.add(new ArrayList<>());
		}
		// 입력 처리
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
//			friends[a][b] = friends[b][a] = true;
			friends.get(a).add(b);
			friends.get(b).add(a);
			
		}
		
		// dfs 탐색
		// 모든 정점에서 따져본다. 가능한 경우를 만나면 바로 종료
		for (int i = 0; i < N; i++) {
			dfs(i, 0, 1 << i);
		}

		if( done ) System.out.println(1);
		else System.out.println(0);
	}
	
	static void dfs(int num, int cnt, int visit) {
		// 기저조건
		if( done ) return;
		
		if( cnt == 4 ) {
			done = true;
			return;
		}
		
		friends.get(num).forEach( (i) ->{
			if( (visit & ( 1 << i)) == 0 ) {
				dfs(i, cnt + 1, (visit | ( 1 << i)));
			}
		});
		// 모든 정점을 대상으로
//		for (int i = 0; i < N; i++) {
//			if( i == num || (visit & ( 1 << i)) != 0 ) continue;
//			
//			if( friends[num][i] ) dfs( i, cnt + 1, (visit | ( 1 << i)) );
//		}
		
	}

}
