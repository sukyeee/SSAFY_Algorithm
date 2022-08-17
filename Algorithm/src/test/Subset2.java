package test;

public class Subset2 {

	static int[] src = {1,2,3,4,5};
	static int[] tgt;
	static boolean[] select = new boolean[src.length];
	
	public static void main(String[] args) {

		
		subset(0);
		
	}
	
	static void subset(int srcIdx) {
		
		if( srcIdx == src.length) {
			
			for(int i=0;i<src.length;i++) {
				if(select[i]) {
					System.out.print(src[i] + " ");
				}
			}System.out.println();
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}

}
