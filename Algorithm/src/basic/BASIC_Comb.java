package basic;

import java.util.Arrays;

public class BASIC_Comb {

	static int COUNT = 0;
	static int[] src = { 1, 2, 3, 4, 5 };
	static int[] tgt = new int[3]; // _ _ _

	public static void main(String[] args) {
		
		comb(0, 0); //
		System.out.println(COUNT);
		
	}
	
	static void comb(int srcIdx, int tgtIdx) {
			
		// 기저 조건
		if(tgtIdx == tgt.length) {
			System.out.println(Arrays.toString(tgt));
			COUNT++;
			return;
		}
		
		// 현재 srcIdx 수부터 남은 수 체크
		// 현재 srcIdx 수를 tgt 에 넣을 것인가 말 것인가?
		for (int i = srcIdx; i < src.length; i++) { // srcIdx 2 => 3, 4, 5
			tgt[tgtIdx] = src[i]; //선택
			comb(i+1, tgtIdx+1); // 다음 수 
			// for 문의 반복되는 과정에서 src는 선택과 비선택이 반복 i가 증가하면서 이전 i의 선택이 취소
		}
	}

}
