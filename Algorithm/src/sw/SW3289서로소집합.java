package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3289서로소집합 {
	static int T, n, m;
	static int c, a, b;
	static int parent[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sb.setLength(0);
			parent = new int[n+1];
			for(int i=1;i<=n;i++) parent[i] = i;
			
			for(int i=0;i<m;i++) { // 입력 개수만큼
				st = new StringTokenizer(br.readLine());
				c = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if( c == 0 ) {
					// 합친다
					Union(a,b);
					
				} else { // c == 1
					// 같은 집합인지 확인
					int parent_a = Find(a);
					int parent_b = Find(b);
					
					if(parent_a == parent_b) sb.append(1);
					else sb.append(0);
				
				}
				
			}
			System.out.println("#" + t + " " + sb);
		}
		
		
	}
	
	static int Find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = Find(parent[x]);
	}
	
	static void Union(int a, int b) {
		a = Find(a);
		b = Find(b);
		
		if(a != b) parent[b] = a;
	}

}
