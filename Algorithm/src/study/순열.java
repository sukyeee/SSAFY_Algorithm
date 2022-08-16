package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Permission;
import java.util.StringTokenizer;

public class 순열 {

	static int N;
	static boolean select[];
	static int tgt[];
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		tgt = new int[N];
		select = new boolean[N];
		perm(0);
		
	}
	
	static void perm(int tgtIdx) {
	
		if(tgtIdx == N) {
			for(int i=0;i<N;i++) {
				if(select[i]) {
					System.out.print(tgt[i]+1 +" ");
				}
			}
			System.out.println();
			cnt++;
			return;
		}
		
		
		for(int i=0;i<N;i++) {
			if(select[i]) continue;
			
			tgt[tgtIdx] = i;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	
	}

}
