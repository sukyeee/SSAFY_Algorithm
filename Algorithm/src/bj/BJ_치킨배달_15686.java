package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_치킨배달_15686 {

	static int N, M, min;
	static List<int[]> house, src, tgt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/bj/BJ15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		house = new ArrayList<>();
		src = new ArrayList<>(); // 전체 치킨집
		tgt = new ArrayList<>(); // 선택된 치킨집 M개
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 초기화

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if( n == 1 ) house.add(new int[] {i, j});
				else if( n == 2 ) src.add(new int[] {i, j});
			}
		}
		
		min = Integer.MAX_VALUE;
		comb(0, 0);
		
		System.out.println(min);
		
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		// 기저조건
		if( tgtIdx == M ) {
			// complete code
			// 현재 치킨집 조합을 기준으로 모든 집에서 최단거리 치킨집을 계산 해서 모두 더한다. => 그 합의 최소값 선택
			
			
			int sum = 0; // 치킨 거리의 합
			int size = house.size();
			for (int i = 0; i < size; i++) { // 모든 집 각각에 대해서 고려
				int dist = Integer.MAX_VALUE; // i번째 집의 치킨 거리
				int[] h = house.get(i); // 이 집으로부터 선택된 M개의 치킨집의 거리를 계산해서 최소값을 선택
				for (int j = 0; j < M; j++) {
					int[] c = tgt.get(j);
					dist = Math.min(dist, Math.abs( h[0] - c[0] ) + Math.abs( h[1] - c[1] ) );
				}
				sum += dist; // 현재 M 개 선택의 치킨거리의 누적합
			}
			// 최솟값 갱신
			min = Math.min(min, sum);
			return;
		}
		
		if( srcIdx == src.size() ) return;
		
		tgt.add(src.get(srcIdx));
		comb( srcIdx + 1, tgtIdx + 1);
		tgt.remove(src.get(srcIdx)); // 배열이 아닌 List 이므로 명시적으로 삭제 코드
		comb( srcIdx + 1, tgtIdx );
	}
}











