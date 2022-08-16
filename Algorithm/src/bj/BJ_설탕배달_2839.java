package bj;

import java.util.Scanner;

public class BJ_설탕배달_2839 {

	static int N, min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		min = 5000;
		
		comb(0, 0);
	
		min = min == 5000 ? -1 : min;
		System.out.println(min);
	}
	
	static void comb(int five, int three) {
		
		int sum = five*5 + three*3;
		
		if( sum == N ) { // 2종류의 봉투로 N kg을 만들었다.
			min = Math.min(min, five + three);
			return;
		} else if( sum > N ) {
			return;
		}
		
		// 5kg 하나 더 쓰던가, 3kg 하나 더 쓰던가
		comb(five + 1, three);
		comb(five, three + 1);
	}

}
