package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// DP + memoi + bitmask

// #1 모든 도시를 맨 앞에 두면서 따질 필요가 없다. 순환구조 3-1-2-5-4-(3) 이므로 맨 앞의 도시를 하나만 따져도 된다.
// #2 DP 점진적으로 계산한 결과를 저장
//	  dp[i][j] : 현재 i번 도시에 있고, 거쳐온 도시들이 j 이다. 값은 남은 도시를 방문하는 데 필요한 최소 비용
//	  1,2,3,4,5,6,7 도시가 있다면
//    dp[3][1,3,6,7] = 100 : 현재 3 도시에 있고, 거쳐온 도시는 1,3,6,7 도시이다. 남은  2,4,5 도시를 방문하는데 최소 비용이 100이다.
// #3 거쳐온 도시 1,3,6,7 표현 -> BitMasking :  dp[3][1100101]
//	  dp[3][0000001] : 첫번재, dp[3][0000100] : 세번째 ... dp[3][1000000] 7번째도시,,
//    dp[3][1100101] <예> 1,6,7 -> 3 /  6,7,1 -> 3

// 계산 방법
//		dp[3][1100101] => 1,3,6,7 방문 후, 남은 2,4,5 도시를 방문하는 최소 비용 ( 이후에 2번을 가는 경우, 또느 4번을 가는 경우, 또는 5번을 가는 경우를 모두 따져서 최소값 갱신
//      2번 도시를 간다고 가정 ( 4번도, 5번도 가봐야 한다.)
//      2번 : dp[2][1100111]            <<  만약 4번을 먼저 간다면 dp[4][1101101], 5번을 먼저 간다면 dp[5][1110101] >> 
//      dp[3][1100101]  ==>  [ dp[2][1100111] + 3  -> 2 비용 ] [ dp[4][1101101] + 3 -> 4 ]   dp[5][1110101] + 3 -> 5 비용 ] 중 최소 비용 선택

// 위 과정을 반복하게 되면 마지막  dp[5][1111111] = 5->1, dp[4][1111111] = 4->1. 
public class BJ_외판원순회_2098_4 {

	static int N, allMask, INF = 987654321;
	static int[][] W;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		allMask = ( 1 << N ) - 1; // 5개 도시 1<<5 -1  100000 -1  => 11111
		dp = new int[N][allMask];
		
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		System.out.println( tsp(0, 1) ); // 0번째 도시 0000..1    dp[0][...0001] <= 
		
	}
	
	static int tsp( int x, int mask ) {
		
		// 기저 조건
		if( mask == allMask ) {
			if( W[x][0] == 0 ) return INF;
			else return W[x][0];
		}
		
		// 이미 계산된 dp 가 있으면 그걸 return
		if ( dp[x][mask] != 0 )return dp[x][mask];
		
		// 이미 계산된 dp가 없으면 따진다.
		dp[x][mask] = INF; // 충분히 큰 값으로 초기화
		
		// x 로부터 갈 수 잇는 남은 도시를 탐색
		for (int i = 0; i < N; i++) {
			// 경로가 없거나, 또는 이미 방문했으면 skip
			if( W[x][i] == 0 || ( mask & ( 1 << i )) != 0 ) continue;
			
			dp[x][mask] = Math.min( dp[x][mask], tsp( i, mask | ( 1 << i )) + W[x][i] );
		}
		
		return dp[x][mask];
	}
}
