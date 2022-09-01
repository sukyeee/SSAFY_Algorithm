package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NP3 {

	static int N;
	static int[] input;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input); // 오름차순 정렬 
		System.out.println(Arrays.toString(input));

		while( np() ) {
			
			System.out.println(Arrays.toString(input));
			
			
			
		}
		System.out.println(cnt);
		

	}

	static boolean np() {
		cnt++;
		int N = input.length;
		
		int i = N-1;
		while(i > 0 && input[i-1] >= input[i]) i--;
		
		if( i == 0 ) return false;
		int j = N-1;
		
		while( input[i-1] >= input[j]) j--;
		
		swap(input, i-1, j);
		
		int k = N-1;
		while(i < k) swap(input, i++, k--);
		
		return true;
	}
	

	
	static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
