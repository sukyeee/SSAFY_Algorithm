package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17135캐슬디펜스 {

	static int N, M, D; // 공격 거리 제한 D
	static int map[][];
	static int tgt[] = new int[3];
	static List<int[]> enemy = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
	
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완
		
		// 궁수 3명 배치. N+1 행의 바로 아래 모든칸에는 성이 있음. 성에 닿으면 적 제외
		comb(0, 0);
		
	}


	
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == 3) { // 궁수 3명 배치 완료
			
			attack();
			
			return;
		}
		
		if( srcIdx == M ) return;
		
		tgt[tgtIdx] = srcIdx; // 궁수가 몇번째 열에 있는지 저장 
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
		
		
	}
	

	static void attack() {
		// D 거리 안에 들어오는 적 공격


		for (int k = 0; k < M; k++) { // 궁수를 기준으로
			boolean flag = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 1 && position(i, N+1, j, tgt[k]) <= D) {
						enemy.add(new int[] {i,j}); // 제일 왼쪽 적의 좌표 저장
						flag = true; break;
					}
				}
			}
			
		}
		
		
		// 적 아래로 한칸 이동
		
		
	}
	
	
	static int position(int y1, int y2, int x1, int x2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
}
