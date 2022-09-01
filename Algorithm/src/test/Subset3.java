package test;

public class Subset3 {

	static int[] src = {1,2,3,4,5};
	static boolean[] select = new boolean[src.length];
	static int cnt;
	
	public static void main(String[] args) {
		
		subset(0);
		System.out.println(cnt);
	}
	
	static void subset(int srcIdx ) {
		
		if(srcIdx == src.length) {
			cnt++;
			for (int i = 0; i < src.length; i++) {
				
				if(select[i]) System.out.print( src[i]+ " ");
					
				
			}
			System.out.println();
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
		
		
	}

}
