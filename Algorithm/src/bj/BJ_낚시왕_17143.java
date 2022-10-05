package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_낚시왕_17143 {

	static int R, C, M, sum;
	static Shark[][] map;
	static List<Shark> list = new ArrayList<Shark>();
	
	// delta 는 상어의 방향에 주어지는 순서를 고려 상-하-우-좌
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = {  0, 0, 1,-1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1]; // 0 dummy
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r,c,s,d,z);
			list.add(shark);
			map[r][c] = shark;
			
		}
		
		// 시뮬레이션 
		// 낚시왕이 왼쪽 -> 오른쪽 한칸 씩 이동하면서 처리 
		for (int i = 1; i <= C; i++) {
			
			// 상어를 잡는다.
			catchShark(i); // 현재 선택된  col을 파라미터로 전달 
			// 상어가 이동
			moveShark();
			// 겹치는 상어를 잡아 먹는다.
			killShark();
		}
		System.out.println(sum);
	}

	static void catchShark(int col) {
		// col은 고정, 땅에서부터 가장 가까운 상어를 r 을 움직이면서 찾고 획득한다.
		for (int i = 1; i <= R; i++) {
			if( map[i][col] != null ) {
				sum += map[i][col].z;
				list.remove(map[i][col]);
				// map[i][col] 에 null 처리 => killShark() 어차피 초기화
				break; // 첫 번째 상어만 획득 
			}
		}
	}
	
	// list에도 Shark, map에도 Shark => list 에 있는 Shark만 이동
	// 고민 거리 1 : 상 하 좌 우 모두 따져야 하는가? => 상,하 묶어서, 좌, 우 묶어서 처리
	// 고민 거리 2 : 좌우 움직이는 속도가 500이고 좌우 폭이 5라면 500번 ++, -- 처리할 것인가?
	static void moveShark() {
		for (Shark shark : list) {
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			
			switch(d) {
				case 0: // 상, 하
				case 1:
					s = s % ( R*2 - 2 ); // s 보정 
					for (int i = 0; i < s; i++) {
						// 위, 아래 양 끝에 있는 상어의 경우 방향 전환 
						if( r == 1 ) d = 1; // 상 -> 하 
						else if ( r == R ) d = 0; // 하 -> 상
						r += dy[d];
					}
					shark.r = r;
					shark.d = d;
					break;
				case 2: // 좌, 우
				case 3:
					s = s % ( C*2 - 2 ); // s 보정 
					for (int i = 0; i < s; i++) {
						// 위, 아래 양 끝에 있는 상어의 경우 방향 전환 
						if( c == 1 ) d = 2; // 좌 -> 우
						else if ( c == C ) d = 3; // 우 -> 좌 
						c += dy[d];
					}
					shark.r = r;
					shark.d = d;
					break;
			}
		}
		
	}
	
	// 실질적인 shark 들의 정보가 list에 있으므로
	// map 초기화 하고 list에 있는 것을 map에 넣어보면서 중복이면 큰 것을 선택 
	static void killShark() {
		// map 초기화
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = null;
			}
		}
		
		int size = list.size();
		for (int i = size-1; i >= 0; i--) { // collection 에서 remove 시 손실 오류 방지를 위해 뒤에서 시작 
			Shark s = list.get(i);
			if( map[s.r][s.c] == null ) {
				map[s.r][s.c] = s;
			} else {
				if( s.z > map[s.r][s.c].z ) {
					list.remove(map[s.r][s.c]);
					map[s.r][s.c] = s;
				} else { // 먼저 선택된 상어 우선 
					list.remove(s);
				}
			}
		}
	}
	
	static class Shark {
		int r,c,s,d,z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}

}
