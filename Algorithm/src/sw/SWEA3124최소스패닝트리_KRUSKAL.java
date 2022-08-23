package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA3124최소스패닝트리_KRUSKAL {

	
	static int T, V, E;
	static int A, B, C;
	
	static int parent[];
	static int cnt;
	static Edge vertex[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			parent = new int[V+1];
			vertex = new Edge[E];
			for(int i=1;i<=V;i++) parent[i] = i;
			
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				vertex[i] = new Edge(A,B,C);
				
			}
			// vertex 가중치 오름차순 정렬
			Arrays.sort(vertex, (o1,o2) -> o1.value - o2.value);
			
			int cnt = 0;
			int sum = 0;
			for(int i=0; i<E; i++) {
				
				
				if(union( vertex[i].a , vertex[i].b )) {
					cnt++;
					sum += vertex[i].value;
				}
				
				if(cnt == V-1) break;
			}
		
			System.out.println("#" + t + " " + sum);
			
		}
		
		
	}
	static class Edge {
		int a;
		int b;
		int value;

		public Edge(int a, int b, int value) {
			super();
			this.a = a;
			this.b = b;
			this.value = value;
		}
		
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find( parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if( px == py )return false;
		
		if( px < py ) parent[py] = px;
		else parent[px] = py;
		return true;
	}
		
	

}

/* 

1
3 3
1 2 1
2 3 2
1 3 3

*/