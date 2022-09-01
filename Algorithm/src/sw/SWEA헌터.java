package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA헌터 {
	static int T, N;
	static int map[][];
	static int tgt[];
	static boolean select[];
	static int num;
	static List<Edge> monsters = new ArrayList<>();
	static List<Edge> customers = new ArrayList<>();
	static int time;
	static boolean visitedMonsters[];
	static boolean visitedCustomers[];
	static int hunter_x = 0;
	static int hunter_y = 0;
	static int min = Integer.MAX_VALUE;
	static int result[] = new int[6];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			//초기화 
			map = new int[N][N];
			monsters.clear();
			customers.clear();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] < 0) customers.add(new Edge(i,j, -map[i][j]));
					if(map[i][j] > 0) monsters.add(new Edge(i,j, map[i][j]));
				}
			}
			
			visitedMonsters = new boolean[monsters.size() + 1];
			visitedCustomers = new boolean[customers.size() + 1];
			min = Integer.MAX_VALUE;
			dfs(0, 0, 0, 0);
			
			System.out.println("#" +t+ " " + min);
		} //testcase
		
		
		
		
	}
	
	static void dfs(int hunter_y, int hunter_x, int time, int depth ) {
		if( depth == monsters.size()*2 ) {
			min = Math.min(min, time);
			
			for (int a : result) {
				System.out.print(a + " ");
			}
			System.out.println();
		
			return;
		}
		// 몬스터 먼저 방문
		for ( Edge monster : monsters) {
		
			if( ! visitedMonsters[ monster.n ] ) {
				
				int d = getDistance( monster.x, hunter_x, monster.y ,  hunter_y );
				
				result[depth] = monster.n;
				
				visitedMonsters[monster.n] = true;
				// 헌터 위치를 몬스터 위치로 갱신 
				dfs( monster.y, monster.x, time + d, depth+1);
				visitedMonsters[monster.n] = false;
				

			}
			
			
		}
		
		// 몬스터 방문 후 고객 방문
		for ( Edge customer : customers) {
			
			if( visitedMonsters[ customer.n ] && ! visitedCustomers[ customer.n ] ) {
				
				int d = getDistance( customer.x, hunter_x, customer.y , hunter_y );
				
				result[depth] = -customer.n;
				
				visitedCustomers[customer.n] = true;
				// 헌터 위치를 고객 위치로 갱신
				dfs( customer.y, customer.x, time + d, depth+1);
				visitedCustomers[customer.n] = false;
				
			}
			
		}
		
		
		
		
	}
	static int getDistance(int x1, int x2, int y1, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	static class Edge {
		int y;
		int x;
		int n;
		public Edge(int y, int x, int n) {
			super();
			this.y = y;
			this.x = x;
			this.n = n;
		}
		
	}
	

}


/*

5
3
0 0 0
0 1 -1
0 0 0
4
-3 -1 1 0
-2 0 0 3
0 0 0 0
0 0 2 0
5
0 0 -3 0 0
0 0 0 3 0
0 0 0 0 2
0 0 1 0 0
-1 0 0 -2 0
6
-1 0 0 0 0 -4
0 0 0 0 2 0
-3 -2 0 4 0 0
3 0 0 0 0 1
0 0 0 0 0 0
0 0 0 0 0 0
8
3 0 0 0 -2 0 0 0
0 0 0 0 -4 0 0 0
0 0 0 0 0 0 0 0
0 0 -1 0 0 0 0 0
0 -3 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 2 4 0 1 0 0
0 0 0 0 0 0 0 0


*/