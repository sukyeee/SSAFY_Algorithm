package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NP {

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

		System.out.println(Arrays.toString(input));

		Arrays.sort(input); // 오름차순 정렬 

		while (np(input)) {
			System.out.println(Arrays.toString(input));
		}
		
		System.out.println(cnt);

	}

	static boolean np(int[] numbers) {
		cnt++;
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

/*
 
 5
1 2 3 4 5
[1, 2, 3, 4, 5]
[1, 2, 3, 5, 4]
[1, 2, 4, 3, 5]
[1, 2, 4, 5, 3]
[1, 2, 5, 3, 4]
[1, 2, 5, 4, 3]
[1, 3, 2, 4, 5]
[1, 3, 2, 5, 4]
[1, 3, 4, 2, 5]
[1, 3, 4, 5, 2]
[1, 3, 5, 2, 4]
[1, 3, 5, 4, 2]
[1, 4, 2, 3, 5]
[1, 4, 2, 5, 3]
[1, 4, 3, 2, 5]
[1, 4, 3, 5, 2]
[1, 4, 5, 2, 3]
[1, 4, 5, 3, 2]
[1, 5, 2, 3, 4]
[1, 5, 2, 4, 3]
[1, 5, 3, 2, 4]
[1, 5, 3, 4, 2]
[1, 5, 4, 2, 3]
[1, 5, 4, 3, 2]
[2, 1, 3, 4, 5]
[2, 1, 3, 5, 4]
[2, 1, 4, 3, 5]
[2, 1, 4, 5, 3]
[2, 1, 5, 3, 4]
[2, 1, 5, 4, 3]
[2, 3, 1, 4, 5]
[2, 3, 1, 5, 4]
[2, 3, 4, 1, 5]
[2, 3, 4, 5, 1]
[2, 3, 5, 1, 4]
[2, 3, 5, 4, 1]
[2, 4, 1, 3, 5]
[2, 4, 1, 5, 3]
[2, 4, 3, 1, 5]
[2, 4, 3, 5, 1]
[2, 4, 5, 1, 3]
[2, 4, 5, 3, 1]
[2, 5, 1, 3, 4]
[2, 5, 1, 4, 3]
[2, 5, 3, 1, 4]
[2, 5, 3, 4, 1]
[2, 5, 4, 1, 3]
[2, 5, 4, 3, 1]
[3, 1, 2, 4, 5]
[3, 1, 2, 5, 4]
[3, 1, 4, 2, 5]
[3, 1, 4, 5, 2]
[3, 1, 5, 2, 4]
[3, 1, 5, 4, 2]
[3, 2, 1, 4, 5]
[3, 2, 1, 5, 4]
[3, 2, 4, 1, 5]
[3, 2, 4, 5, 1]
[3, 2, 5, 1, 4]
[3, 2, 5, 4, 1]
[3, 4, 1, 2, 5]
[3, 4, 1, 5, 2]
[3, 4, 2, 1, 5]
[3, 4, 2, 5, 1]
[3, 4, 5, 1, 2]
[3, 4, 5, 2, 1]
[3, 5, 1, 2, 4]
[3, 5, 1, 4, 2]
[3, 5, 2, 1, 4]
[3, 5, 2, 4, 1]
[3, 5, 4, 1, 2]
[3, 5, 4, 2, 1]
[4, 1, 2, 3, 5]
[4, 1, 2, 5, 3]
[4, 1, 3, 2, 5]
[4, 1, 3, 5, 2]
[4, 1, 5, 2, 3]
[4, 1, 5, 3, 2]
[4, 2, 1, 3, 5]
[4, 2, 1, 5, 3]
[4, 2, 3, 1, 5]
[4, 2, 3, 5, 1]
[4, 2, 5, 1, 3]
[4, 2, 5, 3, 1]
[4, 3, 1, 2, 5]
[4, 3, 1, 5, 2]
[4, 3, 2, 1, 5]
[4, 3, 2, 5, 1]
[4, 3, 5, 1, 2]
[4, 3, 5, 2, 1]
[4, 5, 1, 2, 3]
[4, 5, 1, 3, 2]
[4, 5, 2, 1, 3]
[4, 5, 2, 3, 1]
[4, 5, 3, 1, 2]
[4, 5, 3, 2, 1]
[5, 1, 2, 3, 4]
[5, 1, 2, 4, 3]
[5, 1, 3, 2, 4]
[5, 1, 3, 4, 2]
[5, 1, 4, 2, 3]
[5, 1, 4, 3, 2]
[5, 2, 1, 3, 4]
[5, 2, 1, 4, 3]
[5, 2, 3, 1, 4]
[5, 2, 3, 4, 1]
[5, 2, 4, 1, 3]
[5, 2, 4, 3, 1]
[5, 3, 1, 2, 4]
[5, 3, 1, 4, 2]
[5, 3, 2, 1, 4]
[5, 3, 2, 4, 1]
[5, 3, 4, 1, 2]
[5, 3, 4, 2, 1]
[5, 4, 1, 2, 3]
[5, 4, 1, 3, 2]
[5, 4, 2, 1, 3]
[5, 4, 2, 3, 1]
[5, 4, 3, 1, 2]
[5, 4, 3, 2, 1]
  
 */
