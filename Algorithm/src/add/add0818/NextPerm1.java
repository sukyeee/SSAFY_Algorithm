package add.add0818;

import java.util.Arrays;

public class NextPerm1 {

	static int N=4, C=0;
	static int[] a = {0,1,1,1};
	
	public static void main(String[] args) {
		Arrays.sort(a);
		do {
			C++;
			System.out.println(Arrays.toString(a));
			for (int i = 0; i < N; i++) System.out.print(a[i] == 1? (char)(i+'A'):"");	
			System.out.println();
			//순열사용
		} while(np());
		System.out.println(C);
	}
	
	static void swap(int i, int j) {
		int T = a[i]; a[i] = a[j]; a[j] = T;
	}
	static boolean np() {
		
		int i = N-1;
		while(i>0 && a[i-1] >= a[i]) i--;
		if(i == 0) return false;
		
		int j = N-1;
		while(a[i-1] >= a[j]) j--;
		
		swap(i-1,j);
		
		int k = N-1;
		while(i < k) swap(i++,k--);
		
		return true;
	}

}
