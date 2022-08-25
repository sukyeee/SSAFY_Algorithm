package add.add0825;

import java.io.*;
import java.util.*;

public class DijkstraMain{
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int[][] g=new int[N][N];
		boolean[] v=new boolean[N];
		int[] dist=new int[N]; //다익스트라
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				g[i][j]=sc.nextInt();
			}
			dist[i]=Integer.MAX_VALUE;
		}
		//for(int[] b:g) System.out.println(Arrays.toString(b));
		
		
		dist[0]=0;
		System.out.println(Arrays.toString(dist));System.out.println();
		for(int i=0; i<N; i++){
			int minVertext=-1;
			int min=Integer.MAX_VALUE;
			for(int j=0; j<N; j++){
				if(!v[j] && min>dist[j]) {
							minVertext=j;
							min=dist[j];
				}
			}
				
			v[minVertext]=true;
			
			System.out.println("minVertext="+minVertext);
			System.out.println("min="+min);
			System.out.println("result=xxx");
			System.out.println(Arrays.toString(v));
			if(minVertext == -1) break;
			//if(minVertext == N-1) break;
			
			for(int j=0; j<N; j++){
				if(!v[j] && g[minVertext][j]!=0 && dist[j]>min+g[minVertext][j]){
					                               dist[j]=min+g[minVertext][j];
				}
			}
			System.out.println(Arrays.toString(dist));System.out.println();
		}
		System.out.println(dist[N-1]);
		sc.close();
	}
}

/*
============= 인접행렬 테스트케이스 

5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

output==> 8


6
0 3 5 0 0 0
0 0 2 6 0 0
0 1 0 4 6 0
0 0 0 0 2 3
3 0 0 0 0 6
0 0 0 0 0 0

output==> 12


10
0 4 6 0 0 0 0 0 0 0
0 0 0 9 8 0 0 0 0 0
0 3 0 0 2 3 0 0 0 0
0 0 0 0 0 0 6 0 0 0
0 0 0 2 0 1 3 7 0 0 
0 0 0 0 0 0 0 4 8 0
0 0 0 0 0 0 0 0 0 13
0 0 0 0 0 0 0 0 0 9
0 0 0 0 0 0 0 0 0 4
0 0 0 0 0 0 0 0 0 0


output ==> 21

============= 인접리스트 테스트케이스 
10 17
0 1 4
0 2 6
1 3 9
1 4 8
2 1 3
2 4 2
2 5 3
3 6 6
4 3 2
4 5 1
4 6 3
4 7 7
5 7 4
5 8 8
6 9 13
7 9 9
8 9 4

output ==> 21
*/