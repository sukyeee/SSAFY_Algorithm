package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_창용마을무리의개수_7465 {

	static int T, N, M, ans;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			makeSet();
			
			// 관계 입력 처리 - union
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				union(x, y);
			}
			// 무리의 수, 집합의 수 => parent[i] == i 갯수 증가
			ans = 0;
			for (int i = 1; i <= N; i++) {
				if(parent[i] == i) ans++;
			}
			System.out.println("#" + t + " " + ans);
		}
		
		
	}
	
	static void makeSet() {
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if( parent[x] == x ) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static void union( int x, int y ) {
		int px = findSet(x);
		int py = findSet(y);
		
		if( px > py ) parent[py] = px;
		else parent[px] = py;
		
	}
}









