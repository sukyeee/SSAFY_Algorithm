package live.live0818;

import java.util.Scanner;

/**
 * @author THKim
 */
// n개의  자연수를 입력받고 목표합이 주어지면 목표합에 해당하는 부분집합을 출력
public class SubSetSumTest {

	static int N,totalCnt,S;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt(); // 목표합
		totalCnt = 0;
		input = new int[N];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		subset(0,0);
		System.out.println("총 경우의 수 :" +totalCnt);
	}

	private static void subset(int index,int sum) { // index:부분집합에 고려할 대상 원소의 인덱스,  cnt: 직전까지 고려한 원소 수
	
		if(sum == S) {
			totalCnt++;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) System.out.print(input[i]+"\t");
			}
			System.out.println();
			return;			
		}
		
		// sum>S
		if(sum > S || index == N) return;
		
		// sum<S 
		// 원소 선택
		isSelected[index] = true;
		subset(index+1,sum+input[index]);
		// 원소 미선택
		isSelected[index] = false;
		subset(index+1,sum);
	}
	
}
