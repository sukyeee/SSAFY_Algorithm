package add.add0825;

import java.io.*;
import java.util.*;

public class KruskalMain{
	static int N;
	static int[][] edges;
	static int[] p; //
	
	static void makeSet(){
		p=new int[N];
		for(int i=0; i<N; i++) p[i]=i;
	}
	static int findSet(int a){
		if(a==p[a]) return a;
		return p[a]=findSet(p[a]);
	}
	static boolean union(int a,int b){
		int aRoot=findSet(a);
		int bRoot=findSet(b);
		if(aRoot==bRoot) return false;
		p[bRoot]=aRoot;
		return true;
	}
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		int E=sc.nextInt();
		edges=new int[E][3];
		for(int e=0; e<E; e++){
			int from=sc.nextInt();
			int to=sc.nextInt();
			int weight=sc.nextInt();
			edges[e]=new int[]{from,to,weight};
		}
		Arrays.sort(edges, (o1,o2)->Integer.compare(o1[2],o2[2]));
		makeSet();
		int result=0, cnt=0;
		for(int[] edge:edges){
			if(union(edge[0],edge[1])){
				result+=edge[2];
				if(cnt++ == N-1) break;
			}
		}
		System.out.println(result);
		sc.close();
	}
}

/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

output==>10

7 11
0 1 32
0 2 31
0 5 60
0 6 51
1 2 21
2 4 46
2 6 25
3 4 34
3 5 18
4 5 40
4 6 51

output==>175
*/