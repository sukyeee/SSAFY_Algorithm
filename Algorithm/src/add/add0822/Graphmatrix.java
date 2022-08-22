package add.add0822;

import java.io.*;
import java.util.*;

public class Graphmatrix {
	static int N;
	static int[][] g;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		g = new int[N][N];
		v = new boolean[N];
		for(int e = 0;e< E;e++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			g[from][to] = g[to][from] = 1;
		}
		
		for(int[] a : g) System.out.println(Arrays.toString(a));
//		dfs(0);
		bfs(0);
		sc.close();
	}
	
	static void dfs(int i) {
		v[i] = true;
		System.out.println((char)(i + 'A'));
		for (int j = 0; j < N; j++) {
			if( !v[j] && g[i][j] != 0 ) { // 0 이 아니면 인접하다
				dfs(j);
			}
		}
		
	}
	
	static void bfs(int i) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		v[i] = true;
		q.offer(i);
		
		while(!q.isEmpty()) {
			
			i = q.poll();
			
			System.out.println((char)(i + 'A'));
			for (int j = 0; j < N; j++) {
				if( !v[j] && g[i][j] != 0 ) { // 0 이 아니면 인접하다
					v[j] = true;
					q.offer(j);
				}
			}
		}
		
		
	}
}


/*

	A
   / \
  B   C
 / \ /
D   E
  \ |
    F
    |
    G

7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6


정점 7개.
A B
A C
B D
B E
C E
D F
E F
F G
 DFS: A B D F E C G
 BFS: A B C D E F G

*/