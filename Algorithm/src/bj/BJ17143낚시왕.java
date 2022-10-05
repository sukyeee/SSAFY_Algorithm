package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17143낚시왕 {

	static int R, C, M;
	static int r, c, s, d, z;
	static int dy[] = { 0, -1, 1, 0, 0 }; // 1위 2아래 3오른쪽 4왼쪽 
	static int dx[] = { 0,  0, 0, 1,-1 };
	static int sum;
	static List<Dist> sharks = new ArrayList<>();
	static int map[][];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(map[i], -1);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1; // 상어 위치 y
			c = Integer.parseInt(st.nextToken())-1; // 상어 위치 x
			s = Integer.parseInt(st.nextToken()); // 속력 
			d = Integer.parseInt(st.nextToken()); // 이동 방향 
			z = Integer.parseInt(st.nextToken()); // 크기 
		
			sharks.add(new Dist(r,c,s,d,z)); 
			map[r][c] = z; 
		}
		
		// 1. 낚시왕이 오른쪽으로 한 칸 이동
		// 2. 낚시왕의 열에 있는 상어 중 땅과 가장 가까운 (가장 위) 상어 잡는다
		// 3. 상어 이동 
		
		// 상어 이동 칸 경계를 넘으면 방향 반대로 
		// 상어 이동 마치면, 한 칸에 상어 두 마리 이상 가능. 이 때는 크기가 큰 상어만 남긴다 
		// 낚시왕이 잡은 상어 크기의 합 
		
		// 상어 크기 순으로 내림차순 
		Collections.sort(sharks, (a,b)-> b.z - a.z );
		// i는 낚시왕의 위치 
		for (int i = 0; i < C; i++) {
			
			// 땅과 가장 가까운 상어 잡기 
			fishing(i);
			
			// 상어 이동 
			move();
			
		}
		
		System.out.println(sum);
		
	}
	
	static void move() {
		
		Dist temp[] = new Dist[sharks.size()]; // 임시 상어 위치 저장 
		// 모든 상어에 대하여 
		int idx = 0;
		for (Dist shark : sharks) {
		
			int py = shark.y;
			int px = shark.x;
			int d = shark.d;
			
			for (int i = 0; i < shark.s; i++) {
				py += dy[d];
				px += dx[d];
				
				// 벽에 닿으면 방향 바꾸기 
				if(py < 0 || px < 0 || py >= R || px >= C) {
					if( d == 1 ) d = 2;
					else if ( d == 2 ) d = 1;
					else if ( d == 3 ) d = 4;
					else if ( d == 4 ) d = 3;
					py += dy[d]*2;
					px += dx[d]*2;
					
				}
				
				
			}
			
			map[shark.y][shark.x] = -1;
			
			temp[idx] = shark; 
			temp[idx].y = py;
			temp[idx].x = px;
			temp[idx].d = d;
			idx++;
		}
		
		 idx = 0;
		for (Dist shark : temp) {
			
			// 맵이 비었거나
			if(map[shark.y][shark.x] == -1 ) {
				map[shark.y][shark.x] = shark.z;
				idx++;
			}
			// 현재 상어가 더 작다면 제거  
			else if(map[shark.y][shark.x] >= 0 && map[shark.y][shark.x] > shark.z ) {
				sharks.remove(idx);
			}
		
		}
		
		
		
	}
	
	static void fishing(int x) {
		
		int y = 0;
		while( y < R && map[y][x] == -1) y++;
		
		if( y == R ) return;
		else {
			// 해당 위치의 상어 찾아서 상어 크기 더하기 
			int idx = 0;
			for (Dist shark : sharks) {
				if( y == shark.y && x == shark.x ) {
					sum += shark.z;
					sharks.remove(idx);
					break;
				}
				idx++;
			}
			
			map[y][x] = -1; // 잡은 상어 위치는 -1 으로 표시
		}
		
	}
	
	static class Dist {
		
		int y,x,s,d,z;

		public Dist(int y, int x, int s, int d, int z) {
			super();
			this.y = y;
			this.x = x;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	
		
	}

}
