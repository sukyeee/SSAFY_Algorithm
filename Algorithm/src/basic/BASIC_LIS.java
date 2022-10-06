package basic;

import java.util.Arrays;

public class BASIC_LIS {
	
	static int[] intArray = { 2,3,4,2,5,1,7,3,2,9,10,3,12,8,21,37,12,35,40 };
	static int N = intArray.length;
	static int[] LIS = new int[N];
	
	public static void main(String[] args) {

		for (int i = 0; i < N; i++) {
			// 현재 따지는 i를 초기값 1로 설정
			LIS[i] = 1;
			
			for (int j = 0; j < i; j++) {
				// 맨 앞에서 i 이전까지 i와 j 의 값을 비교해서 
				// i의 값보다 j의 값이 작으면 
				if( intArray[j] < intArray[i] && LIS[i] <= LIS[j] ) {
					LIS[i] = LIS[j] + 1;
				}
			}
		}
		
		System.out.println(Arrays.toString(LIS));
		
	}

}
