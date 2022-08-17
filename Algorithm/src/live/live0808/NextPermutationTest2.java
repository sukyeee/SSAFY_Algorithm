package live.live0808;

import java.util.Arrays;
import java.util.Scanner;

// N개중에 R개의 뽑는 경우는 안됌! nPn만 가능
public class NextPermutationTest2 {

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
	
	static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		int i = N-1;
		while(i > 0 && numbers[i-1] >= numbers[i]) i--;
		
		if(i == 0) return false;
		
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) j--;
		
		swap(numbers, i-1, j);
		
		int k = N-1;
		while(i < k) {
			swap(numbers, i++, k--);
		}
		
		return true;
		
	}
	
	static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
