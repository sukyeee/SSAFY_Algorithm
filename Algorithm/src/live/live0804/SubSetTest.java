package live.live0804;

import java.util.Scanner;

public class SubSetTest {

	static int N, totalCnt;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		totalCnt = 0;
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0;i<N;i++) {
			input[i] = sc.nextInt();
		}
		subset(0);
		System.out.println("총 경우의 수:" + totalCnt);
		
	}

	private static void subset(int index) {
		
		if(index == N ) { // 더이상 고려할 원소가 없다면 부분집합의 구성이 완성
			totalCnt++;
			for (int i = 0; i < N; i++) {
				System.out.print(isSelected[i]? input[i]:"X");
				System.out.print("\t");
			}
			System.out.println("\n");
			return;
		}
		// 원소 선택
		isSelected[index] = true;
//		if(index+1 < N) subset(index+1); // index == N return; 대신 이렇게 한줄로 써도 됨
		subset(index+1);
		// 원소 미선택
		isSelected[index] = false;
		subset(index+1);
		
	}
}
