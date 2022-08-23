package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13023_ABCDE {

	static int N, M;
	static int from, to;
	static List<Integer> friend[];
	static boolean visit[];
	static int cnt;
	static boolean flag;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N];
		friend = new ArrayList[N];
		for(int i=0;i<N;i++) friend[i] = new ArrayList<>();
		
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			friend[from].add(to);
			friend[to].add(from);
		}
		int ans = 0;
		for(int i=0;i<N;i++) {
			cnt = 0;
			dfs(i, cnt);
			// 한 번에 연결 된 것 찾으면 break
			if(flag) break;
		}
		
		if(flag) System.out.println(1);
		else System.out.println(0);
		
	}

	static void dfs(int V, int cnt) {
		if(visit[V] || cnt == 4) {
			flag = true;
			return;
		}
		
		for(int i=0;i<friend[V].size();i++) {
			int v = friend[V].get(i);
			if(visit[v]) continue;
			
			// 다른 정점과 연결 될 때 true
			visit[V] = true;	
			dfs(v, cnt+1);
			visit[V] = false;
		}
		
	}
}
