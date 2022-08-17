package test;

public class Permutation {

	static int src[] =  {1,2,3,4,5};
	static int tgt[];
	static boolean select[];
	static int cnt;
	public static void main(String[] args) {
		
		tgt = new int[3];
		select = new boolean[src.length];
		perm(0);
		System.out.println(cnt);
		
	}

	static void perm(int tgtIdx) {
		if(tgt.length == tgtIdx) {
			cnt++;
			for(int i=0;i<tgt.length;i++) {
				System.out.print(tgt[i] + " ");
				
			}
			System.out.println();
			return;
		}
		
		
		for(int i=0;i<src.length;i++) {
			if(select[i]) continue;
			
			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
			
		}
		
		
		
	}
}
