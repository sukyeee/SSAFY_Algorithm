package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Scanner -> BufferedReader
// local 변수 중 주요 변수들을 -> static 
// int[] -> char[]
public class SWEA1289_ans2 {

	static int T, count;
	static char[] input, memory;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
//		      sc.nextLine();

		for (int t = 1; t <= T; t++) {
			int count = 0;
			input = br.readLine().toCharArray();

			// 두 개의 배열
			// 1. inputArray, 2. memArray
			int cnt = input.length;
			memory = new char[cnt]; // 0으로 초기화
			for (int i = 0; i < cnt; i++) {
				memory[i] = '0';
			}
			// 입력 완료

			// 두 배열을 같은 index 로 이동하면서 값을 비교
			for (int i = 0; i < cnt; i++) {
				if (memory[i] != input[i]) {
					// memArray 를 뒤까지 변경
					for (int j = i; j < cnt; j++) {
						memory[j] = input[i];
					}
					count++;
				}
			}

			System.out.println("#" + t + " " + count);
		}

	}

}
