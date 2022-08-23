package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA7465창용마을무리의개수 {

	static int T, N, M;
	static int a,b;
	static int parent[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N+1];
			makeSet();
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			
			// 다 묶은 후, find로 부모 가리키게 하기
			for(int i=1;i<=N;i++) {
				parent[i] = find(i);
			}
			
			int cnt = 1;
			Arrays.sort(parent);
			for(int i=1;i<N;i++) {
				if(parent[i] != parent[i+1]) {
					cnt++;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
		
	}
	
	static void makeSet() {
		for(int i=1;i<=N;i++) parent[i] = i;
	}
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a!=b) parent[b] = a;
	}

}


/*



2
6 5
1 2
2 5
5 1
3 4
4 6
6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3


*/