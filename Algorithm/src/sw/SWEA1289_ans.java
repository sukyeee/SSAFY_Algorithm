package sw;

import java.util.Scanner;

public class SWEA1289_ans {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		sc.nextLine();

		for (int t = 1; t <= T; t++) {
			int count = 0;
			String str = sc.nextLine();
			// 두 개의 배열
			// 1. inputArray, 2. memArray
			int cnt = str.length();
			int[] inputArray = new int[cnt];
			int[] memArray = new int[cnt]; // 0 으로 초기화

			for (int i = 0; i < cnt; i++) {
				inputArray[i] = str.charAt(i) - '0';
			}
			// 입력 완료

			// 두 배열을 같은 index 로 이동하면서 값을 비교
			for (int i = 0; i < cnt; i++) {
				if (memArray[i] != inputArray[i]) {
					// memArray 를 뒤까지 변경
					for (int j = i; j < cnt; j++) {
						memArray[j] = inputArray[i];
					}
					count++;
				}
			}

			System.out.println("#" + t + " " + count);
		}

	}

}
