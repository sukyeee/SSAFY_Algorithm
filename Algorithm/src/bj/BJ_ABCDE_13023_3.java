package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 인접 리스트 + class
public class BJ_ABCDE_13023_3 {

	static int N, M;
//	static boolean[][] friends; // matrix
	// visit -> bit masking 으로 대체
//	static List<List<Integer>> friends = new ArrayList<List<Integer>>();
//	static List<List<Integer>> friends = new ArrayList<>();
	static Friend[] friends;
	static boolean done; // 성공 여부
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		friends = new boolean[N][N];
//		for (int i = 0; i < N; i++) {
//			friends.add(new ArrayList<>());
//		}
		
		friends = new Friend[N]; // 배열 생성
		for (int i = 0; i < N; i++) { // 배열 각 항목에 Friend 객체 생성 연결
			friends[i] = new Friend(i);
		}
		
		
		// 입력 처리
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
//			friends[a][b] = friends[b][a] = true;
//			friends.get(a).add(b);
//			friends.get(b).add(a);
			friends[a].list.add(new Friend(b));
			friends[b].list.add(new Friend(a));
			
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
		
		friends[num].list.forEach( (f) ->{
			if( (visit & ( 1 << f.num )) == 0 ) {
				dfs(f.num, cnt + 1, (visit | ( 1 << f.num)));
			}
		});
	
	}

	// 정점
	static class Friend {
		int num; // 정점 번호
		List<Friend> list = new ArrayList<>();
		public Friend(int num) {
			this.num = num;
		}
	}
	
}
