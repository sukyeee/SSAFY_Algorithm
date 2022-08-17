package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NP {

	static int N;
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(Arrays.toString(input));

		
		while (np(input)) {
			System.out.println(Arrays.toString(input));
		}
		
		

	}

	static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		int i = N-1;
		while(i > 0 && numbers[i-1] >= numbers[i]) i--;
		
		if( i == 0 ) return false;
		int j = N-1;
		
		while( numbers[i-1] >= numbers[j]) j--;
		
		swap(numbers, i-1, j);
		
		int k = N-1;
		while(i < k) swap(numbers, i++, k--);
		
		return true;
		
	}

	static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
