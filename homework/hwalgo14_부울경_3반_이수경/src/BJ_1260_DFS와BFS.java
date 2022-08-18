
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1260_DFS와BFS {

	static int N, M, V;
	static StringBuilder sb = new StringBuilder();
	static List<Integer> node[];
	static boolean isVisited[];
	static Deque<Integer> q = new ArrayDeque<>(); 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 초기화
		isVisited = new boolean[N+1];
		// 노드 리스트 초기화
		node = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			node[i] = new ArrayList<Integer>();
		}
			
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			node[u].add(v);
			node[v].add(u);
		}
		// 입력 완
		
		for(int i=1;i<=N;i++) Collections.sort(node[i]); // 오름차순 정렬
		
		sb.append(V).append(" ");
		dfs(V); // 시작 정점 V부터
		
		sb.append("\n");
		
		Arrays.fill(isVisited, false);
		bfs(V);
		
		System.out.println(sb);
		
	}
	
	static void dfs(int V) {
		
		if(isVisited[V]) return;
		isVisited[V] = true;
		
		for(int i=0;i<node[V].size();i++) {
			if(isVisited[ node[V].get(i) ]) continue;
			sb.append(node[V].get(i)).append(" ");
			dfs(node[V].get(i));
			
		}
	}
	
	static void bfs(int V) {
		
		// 시작 정점 추가
		q.add(V);
		sb.append(V).append(" ");
		isVisited[V] = true;
		
		while(!q.isEmpty()) {
			
			int top = q.peek();
			q.poll();
			
			for(int i=0;i< node[top].size() ;i++) {
				if(isVisited[node[top].get(i)]) continue;
				
				q.add(node[top].get(i));
				isVisited[node[top].get(i)] = true;
				sb.append( node[top].get(i) ).append(" ");
			}
			
		}
		
	}

}
