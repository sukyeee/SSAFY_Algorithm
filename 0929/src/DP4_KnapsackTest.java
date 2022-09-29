
import java.util.Arrays;
import java.util.Scanner;


//배낭채우기 : 물건 1개 버전
/*
 

==============================
Input Data 
5 675
331 4015
120 8001
265 9209
13 6705
359 809


Result
23915


==========================
4
10
5 10
4 40
6 30
3 50

==>
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10]
[0, 0, 0, 0, 40, 40, 40, 40, 40, 50, 50]
[0, 0, 0, 0, 40, 40, 40, 40, 40, 50, 70]
[0, 0, 0, 50, 50, 50, 50, 90, 90, 90, 90]
result
90

4 
16
2 40
5 30
10 50
5 10

result
90
 */
/**
 * 
 * @author THKim
 *
 */
public class DP4_KnapsackTest {
 
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();
		
		int[] weights = new int[N+1];
		int[] profits = new int[N+1];
		
		// i=0은 0으로 그대로 둠.
		for (int i = 1; i <=N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}
		
		int[][] D = new int[N+1][W+1];
		// 모든 아이템에 대해서 반복
		for (int i = 1; i <= N; i++) {
			
			// 현 아이템의 1부터 목표무게 까지의 가치테이블을 만든다.
			for (int w = 1; w <= W; w++) {
				// 현 아이템의 무게가 가치테이블을 만들기 위한 무게보다 작거나 같다면
				// 선택 가능하며, 아래 둘중 최대 가치를 선택한다.
				//  1) 현 아이템을 선택하지 않았을 경우의 가치는 가치테이블에서 같은 무게의 이전아이템까지의 가치
				//  2) 현 아이템을 선택했을때의 가치와 가치테이블에서 해당 아이템의 무게만큼 뺀 무게의 이전아이템까지의 가치
				if(weights[i] <= w ){	
					D[i][w] = Math.max(D[i-1][w], profits[i]+D[i-1][w-weights[i]]);
				}else{//현 아이템의 무게가 가치테이블을 만들기 위한 무게보다 크다면 현 아이템 선택불가하므로 
					  //최적의 가치는 가치테이블에서 같은 무게의 이전아이템까지의 가치
					D[i][w] =  D[i-1][w];
				}
			}
		}
		System.out.println(D[N][W]);
	}
}
