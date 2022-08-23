package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13023_ABCDE_2 {

	static int N, M;
	static boolean visit[];
	static List<Integer> vertex[];
	static int cnt;
	static boolean flag = false;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N];
		
		vertex = new ArrayList[N];
		for(int i=0;i<N;i++) {
			vertex[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			vertex[a].add(b);
			vertex[b].add(a);
		}
		
		for(int i=0;i<N;i++) {
			cnt = 0;
			dfs(i, cnt);
			if(flag)break;
			
		}
		System.out.println(flag ? 1 : 0);
		
	}
	
	static void dfs(int V, int cnt) {
		if(visit[V]) return;
		if(cnt == 4) {
			flag = true;
			return;
		}

		
		for(int i=0;i<vertex[V].size();i++) {
			if(visit[vertex[V].get(i)]) continue;
			visit[V] = true;
			dfs(vertex[V].get(i), cnt+1);
			visit[V] = false;
		}
		
		
	}

}

/* 
 
5 4
0 1
1 2
2 3
3 4
 
 */