package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383점심식사시간 {

	static int T, N;
	static int map[][];
	static List<Dist> people = new ArrayList<>();
	static Dist stair[] = new Dist[2];
	static int tgt[];
	static int time;
	static PriorityQueue<Dist> pq = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
	static Deque<Dist> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			time = 0;
			
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			int idx = 0;
			int stair_idx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) people.add(new Dist(i, j, 0, idx++, 0));
					if(map[i][j] >= 2) {
						stair[stair_idx++] = new Dist(i, j, map[i][j], 0, 0); 
					}
				}
			}
			
			// 1. 계단 입구까지 이동 시간 
			
			// 현재 위치 -> 계단 입구 
			// 이동 시간 = | PR - SR | + | PC - SC | 
			
			// 2. 계단을 내려가는 시간 
			
			// 계단 입구 도착 후 -> 계단 완전히 내려갈 때까지 시간 
			// 계단 입구 도착 ? 1분 후 아래칸 이동 가능 
			// 계단 위에는 동시 3명까지 가능 
			// 이미 계단을 3명이 내려가고 잇는 경우 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기해야 한다.
			// 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K 분이 걸린다.
			
			// 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소 ??? 
			
			
			
			// 1번, 2번 계단에 따라 사람 나누기 
			tgt = new int[people.size()];
			perm(0);
			
			
			
			
			
			
			
		} // testcase 
		
		
		
		
		
	}
	
	static void goStair() {
		
		
		for (Dist e : people) {
			e.dist = getDistance(e.y, e.x, stair[e.n].y, stair[e.n].x);
			pq.offer(e);
		}
		
		
		int t = 0;
		
		while(!(pq.isEmpty() && q.isEmpty())) {
			
			// 계단 입구까지 계산 
			while(!pq.isEmpty()) {
				if(q.size() >= 3) break;
				Dist e = pq.poll();
				t += e.dist - t; 
				q.offer(e);
				
				while(true) {
					// 같은 시간을 가진 사람들은 pq에서 빼고 q에 넣기 
					if(q.size() >= 3) break;
					if( t == pq.peek().dist ) {
						q.offer(pq.poll());
					}
					else break;
				}
				
				
			}
			
			
			// q에 사람이 있다면(계단 입구에 사람이 있다면) 계단 내려가기
			while(!q.isEmpty()) {
				
				Dist e = q.poll();
				t += stair[e.n].n - t; 
				
			}
			
			
		}
		

		
		
		
	}
	static void perm(int tgtIdx) {
		if( tgtIdx == people.size()) {
			
			// 0은 1번 계단, 1은 2번 계단 
			for (int i = 0; i < tgt.length; i++) {
				if(tgt[i] == 0) { // 0번 계단 
					people.get(i).n = 0;
				}
				else { // 1번 계단 
					people.get(i).n = 1;
				}
			}
			pq.clear();
			q.clear();
			// 1. 계단 입구까지 이동 시간 계산 
			goStair();
			
			
			// 최솟값 갱신 
			
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
		}
		
		
		
	}
	static int getDistance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
	
	static class Dist {
		int y, x, n, pIdx, dist;

		public Dist(int y, int x, int n, int pIdx, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.n = n;
			this.pIdx = pIdx;
			this.dist = dist;
		}

	}

}

/*
10
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0
6
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 0 0
2 0 1 0 0 0
0 0 2 0 0 0
6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 0
0 0 0 2 0 4
7
0 0 0 0 0 0 0
0 0 0 0 0 0 4
0 0 0 0 1 0 0
1 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 2 0 0 0 0 0
7
0 0 0 0 0 0 0
7 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
2 0 0 0 0 1 0
0 0 0 0 0 0 0
8
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 0
8
3 0 0 0 0 0 5 0
0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 0 1 1 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
9
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 8
7 0 0 0 0 1 0 0 0
0 0 0 0 0 1 1 0 0
0 0 0 0 0 0 0 0 0
1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
3 0 1 0 1 0 0 0 0 2
1 1 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
*/