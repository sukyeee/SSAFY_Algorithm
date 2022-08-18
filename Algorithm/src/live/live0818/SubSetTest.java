package live.live0818;

import java.util.Scanner;

// n개의 자연수를 입력받고 목표합이 주어지면 목표합에 해당하는 부분집합을 출력
public class SubSetTest {

	static int N, S, totalCnt;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt(); // 목표 합
		totalCnt = 0;
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0;i<N;i++) {
			input[i] = sc.nextInt();
		}
		subset(0, 0);
		System.out.println("총 경우의 수:" + totalCnt);
		
	}

	private static void subset(int index, int sum) {
		
		if(sum == S) {
			totalCnt++;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) System.out.print(input[i] + " ");
			}
			System.out.println("\n");
			return;
		}
		// sum > S
		if(sum>S || index == N) return;
		
		// sum < S
		// 원소 선택
		isSelected[index] = true;
//		if(index+1 < N) subset(index+1); // index == N return; 대신 이렇게 한줄로 써도 됨
		subset(index+1, sum + input[index]);
		// 원소 미선택
		isSelected[index] = false;
		subset(index+1, sum);
		
	}
}
