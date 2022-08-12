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
//	static List<int[]> A = new ArrayList<>();
//	static List<int[]> B = new ArrayList<>();
//	static int taste[][];
	static List<Food> synergy = new ArrayList<>();
	static List<Food> tgt = new ArrayList<>();
	
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
			
			for(int i=0;i<N;i++) {
				for(int j=i;j<N;j++) {
					if(i == j) continue;
					synergy.add(new Food(map[i][j] + map[j][i], i, j));
					
				}
			}
			
			min = Integer.MAX_VALUE;
			comb(0, 0);
			
			System.out.println("#" +t+ " " + min);
		}
		
	
		
		
		
	}
	static void comb(int srcIdx, int tgtIdx) {
		
		if(tgtIdx == 2) {
			// 두 조합 중 차이 가장 작은 것 구하기
			if(tgt.get(0).a != tgt.get(1).a && tgt.get(0).b != tgt.get(1).b
					&& tgt.get(0).a != tgt.get(1).b && tgt.get(0).b != tgt.get(1).a) {
				min = Math.min(min, Math.abs( tgt.get(0).s - tgt.get(1).s ));
			}
			
			return;
		}
		
		if(srcIdx == synergy.size()) return;
		
		tgt.add(synergy.get(srcIdx));
		comb(srcIdx + 1, tgtIdx + 1);
		tgt.remove(synergy.get(srcIdx));
		comb(srcIdx + 1, tgtIdx);
		
	}
	
	static class Food {
		int s, a, b;

		public Food(int s, int a, int b) {
			this.s = s;
			this.a = a;
			this.b = b;
		}
		
	}

}
