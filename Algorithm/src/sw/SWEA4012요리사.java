package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4012요리사 {

	static int T, N;
	static int map[][];
	static List<Integer> A = new ArrayList<>();
	static List<Integer> B = new ArrayList<>();
	static int tgt[];
	static int min, sum;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/sw/SWEA4012요리사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			// 초기화
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
//			A = new int[N/2][N/2];
//			B = new int[N/2][N/2];
			tgt = new int[N/2];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 완
			
			// 식재료를 각각 N/2 로 나누어 두개의 요리.
			// A음식 시너지 구하기 , B음식 시너지 구하기
			// 두 음식의  차 구하기
			// 위를 반복해서 음식의 차가 가장 작은 경우 구하기
			
			// 각각 시너지 값 구하기
			// 모든 식재료에 21 12 , 31 13 등 더한 2차원 배열 만들기
			
			min = Integer.MAX_VALUE;
			comb(0, 0);
			
			System.out.println("#" +t+ " " + min);
		}
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		
		if( tgtIdx == N/2 ) {
			chk();
			return;
		}
		if( srcIdx == N ) return;
		
		//tgt.add
		tgt[tgtIdx] = srcIdx;
		comb(srcIdx + 1, tgtIdx + 1);
		//tgt.remove
		comb(srcIdx + 1, tgtIdx);
		
	}
	
	static void chk() {
		
		int A_sum = 0;
		int B_sum = 0;
		
		for(int i=0;i<N/2;i++) {
			// 현재 tgt 가 가지고 있는 원소 -> A, 포함하지 않은 것은 B로
			A.add(tgt[i]);
		}
		for(int i=0;i<N;i++) {
			if(!A.contains(i)) B.add(i);
		}
		
		// 해당 인덱스들의 합 구하기
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				if(i == j) continue;
				A_sum += map[ A.get(i)][ A.get(j)];
				B_sum += map[ B.get(i)][ B.get(j)];
			}
		}
		
		min = Math.min(min, Math.abs(A_sum - B_sum));
		A.clear();
		B.clear();
	}

}
