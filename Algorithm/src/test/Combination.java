package test;

public class Combination {

	static int src[] = {1,2,3,4,5};
	static int tgt[];
	static int cnt;
	public static void main(String[] args) {
		
		tgt = new int[3];
		comb(0, 0);
		System.out.println(cnt);
	}
	static void comb(int srcIdx, int tgtIdx) {
		
		if(tgtIdx == tgt.length) {
			cnt++;
			for(int i=0;i<tgt.length;i++) {
				System.out.print(tgt[i] +" ");
			}
			System.out.println();
			return;
		}
		
		if( srcIdx == src.length) return;
		
		tgt[tgtIdx] = src[srcIdx];
		comb(srcIdx +1, tgtIdx +1);
		comb(srcIdx +1, tgtIdx );
		
	}

}