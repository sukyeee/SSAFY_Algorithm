package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14502연구소 {
	
	static int N, M;
	static int map[][];
	static int mapCopy[][];
	static boolean visit[][];
	static List<Dist> wall = new ArrayList<>();
	static List<Dist> virus = new ArrayList<>();
	static int max;
	static int sum;
	static int tgt[] = new int[3]; // 벽은 항상 3개 
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우 
	static int dx[] = {  0, 0,-1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		mapCopy = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mapCopy[i][j] = Integer.parseInt(st.nextToken());
				if(mapCopy[i][j] == 0) wall.add(new Dist(i, j)); // 벽이 될 수 있는 곳 
				if(mapCopy[i][j] == 2) virus.add(new Dist(i, j));
			}
		}
		// 입력 완 
		
		// 0 빈 칸, 1은 벽, 2는 바이러스가 있는 곳 
		
		// 벽은 무조건 3개를 세워야 함
		// 0 인 칸 골라서 조합으로 3개 뽑기 
		
		 comb(0, 0);
		
		
		
		System.out.println(max);
	}
	static void comb(int srcIdx, int tgtIdx) {
		
		if(tgtIdx == 3) {
			visit = new boolean[N][M];
			copy();
			bfs();
			return;
		}
		
		if( srcIdx == wall.size() ) return;
		
		tgt[tgtIdx] = srcIdx;
		comb(srcIdx + 1 , tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static void bfs() {
		
		// 벽 표시 
		for (int idx : tgt) {
			int y = wall.get(idx).y;
			int x = wall.get(idx).x;
			map[y][x] = 1; 
		}
		
		Deque<Dist> q = new ArrayDeque<>();
		
		for (Dist e : virus) {
			q.offer(new Dist(e.y, e.x)); // 바이러스 큐에 추가 
			visit[e.y][e.x] = true;
		}
		
		
		while(!q.isEmpty()) {
			
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
				Dist e = q.poll();
			
				for (int d = 0; d < 4; d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					if(py < 0 || px < 0 || py >= N || px >= M || visit[py][px] || map[py][px] != 0) continue;
					q.offer(new Dist(py, px));
					visit[py][px] = true;
					map[py][px] = 2;
				}
				
			}
			
			
		}
		
		
		// 남아있는 안전영역 개수 구하기 
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) sum++;
			}
		}
		
		max = Math.max(max, sum);
	}
	
	static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}
	}
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
