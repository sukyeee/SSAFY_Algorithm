package add.add0811;

import java.io.*;
import java.util.*;

public class BfsDfs {

	static int[] di = {-1, 0, 1, 0}; // 상 우 하 좌 
	static int[] dj = {0,  1, 0 ,-1}; 
	static int N = 5, C = 1;
	static int[][] a;
	static boolean[][] v;
	
	static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		v[i][j] = true;
		q.offer(new int[] {i,j});
		while(!q.isEmpty()) {
	
			int[] ij = q.poll();
			i = ij[0];
			j = ij[1];
			a[i][j] = C++;
			for (int[] b:a) 	System.out.println(Arrays.toString(b)); System.out.println();
			
			for (int d = 0; d < 4; d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(0 <= ni && ni < N && 0 <= nj && nj < N && !v[ni][nj]) {
					v[ni][nj] = true;
					q.offer(new int[] {ni,nj});
				}
			}
		}
		
		
		
	}
	
	static void dfs(int i, int j) {
		
		v[i][j] = true;
		a[i][j] = C;
		C++;
		for (int[] b:a) 	System.out.println(Arrays.toString(b)); System.out.println();
		
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0 <= ni && ni < N && 0 <= nj && nj < N && !v[ni][nj]) {
				dfs(ni, nj);
			}
		}
		
	}
	public static void main(String[] args) throws Exception {

		a = new int[N][N];
		v = new boolean[N][N];
//		dfs(N/2, N/2);
		bfs(N/2, N/2);
		
		
		
		
	}
}
