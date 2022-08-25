package add.add0825;
import java.io.*;
import java.util.*;

public class PrimMain{
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int[][] g=new int[N][N];
		boolean[] v=new boolean[N];
		int[] minEdge=new int[N]; //
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				g[i][j]=sc.nextInt();
			}
			minEdge[i]=Integer.MAX_VALUE;
		}
		//for(int[] b:g) System.out.println(Arrays.toString(b));
		
		int result=0, cnt=0;
		minEdge[0]=0;
		System.out.println(Arrays.toString(minEdge));System.out.println();
		for(int i=0; i<N; i++){
			int minVertext=-1;
			int min=Integer.MAX_VALUE;
			for(int j=0; j<N; j++){
				if(!v[j] && min>minEdge[j]) {
							minVertext=j;
							min=minEdge[j];
				}
			}
				
			v[minVertext]=true;
			result+=min;
			System.out.println("minVertext="+minVertext);
			System.out.println("min="+min);
			System.out.println("result="+result);
			System.out.println(Arrays.toString(v));
			if(cnt++ == N-1) break;
			
			for(int j=0; j<N; j++){
				if(!v[j] && g[minVertext][j]!=0 && minEdge[j]>g[minVertext][j]){
					                               minEdge[j]=g[minVertext][j];
				}
			}
			System.out.println(Arrays.toString(minEdge));System.out.println();
		}
		System.out.println(result);
		sc.close();
	}
}

/*
5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0

output==>10

7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0

output==>175
*/