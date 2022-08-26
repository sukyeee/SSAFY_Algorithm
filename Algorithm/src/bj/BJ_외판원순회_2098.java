package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_외판원순회_2098 {

	static int N, min;
	static int[][] matrix;
	static int[] src, tgt;
	static boolean[] select;
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		matrix = new int[N][N];
		src = new int[N];
		tgt = new int[N];
		select = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			src[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		perm(0);
		
		System.out.println(min);
		
	}
	
	static void perm(int tgtIdx) {
		// 기저 조건
		if( tgtIdx == N ) {
			// complete code 
			int sum = 0;
			for (int i = 0; i < N-1; i++) {
				if( matrix[ tgt[i] ][ tgt[i+1] ] == 0 ) return;
				sum += matrix[ tgt[i] ][ tgt[i+1] ];
			}
			if( matrix[ tgt[N-1] ][ tgt[0] ] == 0 ) return;
			sum += matrix[ tgt[N-1] ][ tgt[0] ];
			
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if( select[i] ) continue;
			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm( tgtIdx + 1 );
			select[i] = false;
		}
	}

}
