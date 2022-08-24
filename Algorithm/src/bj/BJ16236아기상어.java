package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16236아기상어 {

	static int N;
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dx[] = {  0, 0,-1, 1 };
	static int map[][];
	static boolean visit[][];
	static Node shark;
	static int time;
	static int y,x;
	static int sum;
	static int cnt, depth;
	static List<Node> eatable = new ArrayList<>();
	static boolean flag;
	static Node mulgogi;
	static Deque<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shark = new Node(i, j, 2); // 상어의 현재 위치, 크기
			}
		}
		// 입력 완
		
		// 아기상어 크기 2로 시작
		
		// 자신 크기보다 큰 물고기 잇는칸은 못지나감
		// 크기가 같은 물고기는 먹을 수 없지만 지나갈 수는 있음
		// 먹을 수 있는 물고기 1마리면 그 물고기 먹음
		// 1마리 이상이면 거리가 가장 가까운 물고기 먹음 ( 1. 가장 위, 2. 가장 왼쪽 )
		// 물고기를 먹으면 그 칸은 빈칸이 됨
		// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가.
		
		// 1~6 : 물고기의 크기
		// 9 : 아기 상어의 위치
		
		while(true) {
			
			eatable.clear();
			depth = 0;
			
			// 먹을 수 있는 물고기 탐색
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if( map[i][j] != 0 && shark.size > map[i][j] && !(i == shark.y && j == shark.x)) {
						int d = Math.abs(shark.y - i ) + Math.abs(shark.x - j);
						eatable.add(new Node(i, j, d)); // eatable에서는 거리 저장
					}
				}
			}
			
			// 거리 가장 가까운 순으로 정렬, 많다면 ? 
			// 우선순위 : 1. 가장 위에 있는 물고기, 2. 가장 왼쪽에 있는 물고기
			if(eatable.size() >= 1) {
				Collections.sort(eatable, 
						(a,b) -> a.size == b.size ?  
								a.y == b.y ?
										a.x - b.x : a.y - b.y 
								: a.size - b.size 
						);	
			} else break;
			
			// 제일 앞에 있는 물고기 한마리 먹기
			mulgogi = new Node(eatable.get(0).y, eatable.get(0).x, eatable.get(0).size);
			
			// 물고기한테 갈 수 있는지 체크 ( 큰 물고기가 있으면 지나가지 못함)
			bfs(shark.y, shark.x);
			
			if(!flag) continue;
			
			time += depth-1; // 물고기와 상어 거리 차이만큼 시간 증가
			
			// 자신보다 물고기 크기가 작다면 잡아먹음
			if( shark.size > map[mulgogi.y][mulgogi.x] ) {
				cnt++; // 물고기 먹은 횟수
				map[mulgogi.y][mulgogi.x] = 0; 
			}
			if(cnt == shark.size ) {
				shark.size++; // 물고기를 자신의 크기만큼 먹으면 상어크기 +1
				cnt = 0;
			}
			
			// 물고기 위치로 이동
			shark.y = mulgogi.y;
			shark.x = mulgogi.x;
			
		}
		
		
		System.out.println(time);
		
		
	}
	
	static void bfs(int y, int x) {
		// 물고기의 위치에 도달할 수 있으면 return 
		q.offer(new Node(y, x, map[y][x]));
		visit[y][x] = true;
		
		while( ! q.isEmpty() ) {
			int q_size = q.size();
			depth++;
			
			for(int i=0;i<q_size;i++) {
				
				Node e = q.poll();
				
				for(int d=0;d<4;d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					
					if( py < 0 || py >= N || px < 0 || px >= N 
							|| map[py][px] > shark.size || visit[py][px]) continue;
					
					if( py == mulgogi.y && px == mulgogi.x) {
						flag = true;
						break;
					}
					visit[py][px] = true;
					q.offer(new Node(py, px, map[py][px]));
					
					
				}
				if(flag) break;
			}
			
			if(flag) break;
		}
		
		
	}
	
	static class Node {
		int y, x, size; // d 는 상어의 크기

		public Node(int y, int x, int size) {
			super();
			this.y = y;
			this.x = x;
			this.size = size;
		}
		
	}

}
