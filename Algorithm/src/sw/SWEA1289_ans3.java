package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Scanner -> BufferedReader
// local 변수 중 주요 변수들을 -> static 
// int[] -> char[]
public class SWEA1289_ans3 {

	static int T, count;
	static char[] input, memory;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
//		sc.nextLine();

		for (int t = 1; t <= T; t++) {
			int count = 0;
			input = br.readLine().toCharArray(); // 배열을 새로 생성해서 return

			// 현재 문자
			char current = '0';

			int cnt = input.length;

			// 입력 완료

			// 현재의 값과 input[i]를 비교해서 다르면 변경횟수가 증가하는 시점
			for (int i = 0; i < cnt; i++) {
				if (input[i] != current) {
					count++;
				}
				current = input[i];
			}

			System.out.println("#" + t + " " + count);
		}

	}

}
