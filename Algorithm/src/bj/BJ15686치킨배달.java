package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15686치킨배달 {

	static int N, M;
	static int map[][];
	static Dist tgt[];
	static List<Dist> home = new ArrayList<>();
	static List<Dist> chicken = new ArrayList<>();
	static int minSum;
	static int min = Integer.MAX_VALUE;
	static int result;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/bj/BJ15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//초기화
		map = new int[N][N];
		tgt = new Dist[M];
	
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완
		
		// 조합?
		
		// 모든 집과 치킨집에 대한 치킨거리 구하기
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				if(map[i][j] == 1) {
					home.add(new Dist(i, j));
				}
				if(map[i][j] == 2) {
					chicken.add(new Dist(i,j));
				}
			}
		}
		
		// M개 (3개) 뽑기
		result = Integer.MAX_VALUE;
		comb(0, 0);
		
		System.out.println(result);
		
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		// 기저 조건
		if(tgtIdx == M) {
		// 해당 거리와 모든 집과 거리 최솟값 구하기
			minSum = 0;
			for(int i=0;i<home.size();i++) {
				min = Integer.MAX_VALUE;
				for(int j=0;j<M;j++) {
					int dx = Math.abs( home.get(i).x - tgt[j].x );
					int dy = Math.abs( home.get(i).y - tgt[j].y );
					
					min = Math.min(min, dx + dy);
				}
				minSum += min;
			}
			result = Math.min(result, minSum);
			return;
		}
		
		
		if( srcIdx == M ) return;
		tgt[tgtIdx] = chicken.get(srcIdx);
		
		comb( srcIdx + 1, tgtIdx + 1);
		comb( srcIdx + 1, tgtIdx );
		
	
	}

	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			this.y = y;
			this.x = x;
			
		}
		
	}
	
}
