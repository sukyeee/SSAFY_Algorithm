package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ24479알고리즘수업깊이우선탐색 {

	static int N, M, R, u, v;

	static List<Integer>[] node;	
	static boolean isVisited[];
	static int result[];
	static int idx = 1;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		result = new int[N+1];

		// 2차원 리스트 초기화 
		node = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			node[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			node[u].add(v);
			node[v].add(u);
		}
		
		for(int i=0;i<=N;i++) Collections.sort(node[i], (a,b) -> a - b);
		
		dfs(R);
		
		
		for(int i=1;i<=N;i++) System.out.println(result[i]);
		
	
	}
	
	static void dfs(int V) {
		
		if(isVisited[V]) return;
		
		isVisited[V] = true;
		 
		result[V] = idx++;
		for(int i=0;i<node[V].size();i++) {
			if(isVisited[node[V].get(i)]) continue;
			
			dfs(node[V].get(i));
		}
		
	}

}


/* 
 *
5 5 1
1 4
1 2
2 3
2 4
3 4

 * */
