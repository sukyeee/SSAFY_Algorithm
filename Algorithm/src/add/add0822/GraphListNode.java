package add.add0822;

import java.io.*;
import java.util.*;

public class GraphListNode {
	static int N;
	static Node[] g;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		g = new Node[N];
		v = new boolean[N];
		
		for(int e = 0;e< E;e++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			g[from] = new Node(to, g[from]);
			g[to] = new Node(from, g[to]);
		}
		
//		for(List a : g) System.out.println(a);
		for (int i = 0; i < args.length; i++) {
			
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println((char)(i+'A') + "(" + i + "):" + " ");
		}
		
//		dfs(0);
		bfs(0);
		sc.close();
	}
	
	   static void dfs(int i){
	        v[i]=true;
	        System.out.print((char)(i+'A')+" ");
	        for(Node j = g[i]; j != null ; j= j.next){
	            if(!v[j.to]){
	                dfs(j.to);
	            }
	        }
	    }
	
	 static void bfs(int i){
	        Queue<Integer> q=new ArrayDeque<>();
	        v[i]=true;
	        q.offer(i);
	        while(!q.isEmpty()){
	            i=q.poll();
	            System.out.print((char)(i+'A')+" ");
	            for(Node j = g[i]; j != null ; j= j.next){
		            if(!v[j.to]){
		            	v[j.to] = true;
		                q.offer(j.to);
		            }
		        }
	        }
	  }
	 
    static class Node{
        int to;
        Node next;
        
        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
        @Override
        public String toString() {
            return (char)(to+'A')+"("+to+")->"+next;
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