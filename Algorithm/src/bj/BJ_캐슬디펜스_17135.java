package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_캐슬디펜스_17135 {

	static int N, M, D, max;
	static int[][] map;
	static int[] archer = new int[3]; // x 좌표만 필요 // 조합에서 tgt 역할
	static List<Enemy> enemy = new ArrayList<>(); // 적 정보 관리
	static List<Enemy> enemyCopy = new ArrayList<>(); // 적 정보 원본 관리
	
	// 적 중에 가장 가까운 적을 꺼내는 자료구조
	static PriorityQueue<Enemy> pqueue 
		= new PriorityQueue<>((e1, e2)-> e1.d == e2.d ? e1.x - e2.x : e1.d - e2.d);
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 사정거리
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if( n == 1 ) enemyCopy.add(new Enemy(i, j));
			}
		}
		
		// 조합이 완성되면 시뮬레이션 진행 -> 새로운 조합이 완성되면 시뮬레이션 다시 진행
		comb(0,0);
		
		System.out.println(max);
		
	}
	
	
	// 현재 궁사의 위치 조합 각각에 대한 시뮬레이션
	static void check() {
		// 적 초기화
		enemy.clear();
		for (Enemy e : enemyCopy) {
			enemy.add(new Enemy(e.y, e.x));
		}
		
		// 시뮬레이션 시작
		int dead = 0;
		while(true) {
			
			// 궁사가 활을 발사 (1명씩)
			for (int i = 0; i < 3; i++) {
				
				// PriorityQueue 를 이용해서 가장 가까운 적 선별
				pqueue.clear();
				int ac = archer[i]; // 첫번째 궁사 ~~ 세번째 궁사 ( 위치 x좌표 )
				// enemy -> pqueue 로 이동하면서 거리 계산
				int size = enemy.size();
				for (int j = 0; j < size; j++) {
					Enemy e = enemy.get(j);
					e.d = Math.abs(ac - e.x) + Math.abs(N - e.y); // 사정거리 계산
					
					if( e.d <= D ) { // 사정거리 안에 드는 적만 pqueue 에 담는다.
						pqueue.offer(e);
					}
				}
				
				// pqueue 가 비워있지 않으면 제거 대상이 있다 -> 표시
				if( !pqueue.isEmpty()) {
					pqueue.poll().dead = true;
				}
				
			}
			// 적 정리 ( 죽은 적 제거, 적 이동 )
			Iterator<Enemy> iter = enemy.iterator();
			while(iter.hasNext()) {
				Enemy e = iter.next();
				if( e.dead ) { // 적군 사망 처리
					iter.remove();
					dead++;
				} else if( e.y == N-1 ) { // 맨 마지막 칸 적 제외
					iter.remove();
				} else  // 남은 적 한 칸 아래로
					e.y++;
			}
			
			// 모든 적이 다 사라지면
			if( enemy.size() == 0 ) break;
		}
		
		max = Math.max(max, dead);
	}
	static void comb(int srcIdx, int tgtIdx) {
		// 기저 조건
		if( tgtIdx == 3 ) {
			// complete code
			// 궁사의 위치가 결정되었으므로, 시뮬레이션을 시도 => 최대값
			check();
			return;
		}
		
		if( srcIdx == M ) return;
		
		archer[tgtIdx] = srcIdx;
		
		comb( srcIdx + 1, tgtIdx + 1); // 선택하고 간다
		comb( srcIdx + 1 , tgtIdx ); // 다시 보겠다.
	}
	
	// 적
	static class Enemy {
		int y, x, d; // d: 궁수와의 거리
		boolean dead; // 사망 여부
		public Enemy(int y, int x ) { // d, dead 는 문제 풀이 과정에서 set
			this.y = y;
			this.x = x;
		}
		
	}
}
