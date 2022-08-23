package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_서로소집합_3289 {

	static int T, n, m; // n 개의 집합, m 개의 연산
	static int[] parent;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			// 초기화
			parent = new int[n+1]; // 0 dummy
			makeSet();
			
			sb = new StringBuilder("#").append(t).append(' '); // #1 
			// 풀이
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if( op == 0 ) {
					union(x,y);
				}
				else if( op == 1 ) {
					if( findSet(x) == findSet(y) ) sb.append('1');
					else sb.append('0');
				}
			}
		}
	}
	
	// 서로소 집합 관련 메소드들
	static void makeSet() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	// 부모를 찾는 ( 부모 번호 == 집합 )
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet( parent[x]);
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if( px < py ) parent[py] = px;
		else parent[px] = py;
	}

}
