package test;

public class Permutation2 {

	static int src[] = {1,2,3,4,5};
	static int tgt[];
	static boolean select[];
	static int cnt = 0;
	public static void main(String[] args) {
		
		tgt = new int[4];
		select = new boolean[5];
		perm(0);
		
		System.out.println(cnt);
	}
	
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			cnt++;
			for(int i=0;i<tgt.length;i++) {
				System.out.print(tgt[i] +" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0;i<src.length;i++) {
			if(select[i]) continue;
			
			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm( tgtIdx + 1);
			select[i] = false;
			
			
		}
		
	}
}
