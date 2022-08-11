package bj;

import java.util.Arrays;
import java.util.Scanner;

// N개중에 R개의 뽑는 경우는 안됌! nPn만 가능
public class NextPermutationTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		// 전처리 : 순열에 쓰일 수들을 오름차순 정렬
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input)); // 순열 완성
		} while(np(input));
		
	}
	
	public static boolean np(int[] numbers) { // numbers 배열의 상태를 근거로 다음 순열 생성, 다음 수열 존재하면 true, 아니면 false
		
		int N = numbers.length;
		// 1. 꼭대기 찾기 ( 맨 뒤부터 올라가는 모습인지 보기 . )
		int i = N-1;
		while(i > 0 && numbers[i-1] >= numbers[i]) --i;
		
		// i 가 맨 앞 (0) 까지 왔거나 내 앞에있는 녀석이 나보다 작으면 while문 빠져나옴
		if(i == 0) return false; // 다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태!
		
		// 2. 꼭대기의 바로 앞자리(i-1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
		
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) --j;
		
		// 3. i-1 위치 값과 j 위치값 교환
		swap(numbers, i-1, j);
		
		// 4. i 위치부터 맨 뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N-1;
		while(i < k) swap(numbers, i++, k--);
		
		return true;
		
	}

	public static void swap(int[] numbers, int i, int j) {
		
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
