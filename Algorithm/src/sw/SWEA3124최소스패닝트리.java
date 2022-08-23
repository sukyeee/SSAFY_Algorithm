package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA3124최소스패닝트리 {

	
	static int T, V, E;
	static int A, B, C;
	
	static List<Node> node[];
	static int parent[];
	static int cnt;
	static boolean visit[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			parent = new int[V+1];
			visit = new boolean[V+1];
			node = new ArrayList[V+1];
			
			for (int i = 0; i <= V; i++) {
				node[i] = new ArrayList<>();
			}
			
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				node[A].add(new Node(B, C));
				node[B].add(new Node(A, C));
				
			}
			
			// 가중치 순서대로 오름차순 정렬
			for(int i=0;i<=V;i++) {
				Collections.sort(node[i], (a,b)->a.value-b.value);
			}
			
			int i= node[1].get(0).n;
			visit[node[1].get(0).n] = true;
			
			while(true) {
				// V-1 개 만큼 연결되면 중지
				if( cnt == V-1 ) break;
				
				// 사이클 체크 ( 사이클이 아니라면 cnt 증가 )
				if( !visit[node[i].get(B).n] )
				{
					// 제일 작은 가중치를 가진 간선부터 연결
					union(node[i].get(A).n, node[i].get(B).n);
					cnt++;	
				}
				
				
				
				i++;
			}
			
		}
		
		
	}

	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find( parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if( px < py ) parent[py] = px;
		else parent[px] = py;
	}
		
	static class Node {
		int value;
		int n;
		public Node(int n, int value) {
			super();
			this.value = value;
			this.n = n;
		}
		
		
	}

}
