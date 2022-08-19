package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS로 풀면 메모리 초과
public class BJ_알파벳_1987_3 {

	static int R, C, max;
	static int[][] map;
//	static boolean[] visit = new boolean[26]; // 알파벳 수

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static Queue<Node> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j] - 'A';
			}
		}
		
		
		max = Integer.MIN_VALUE;
		// map[0][0] --> 어떤 integer 값이 있을 건데 그 값을 1을 << 연산 수행
		// map[0][0] : 3 ( D )  -> 1 << 3 : 1000
//		dfs( 0, 0, 1, 1 << map[0][0]);
		
		bfs();
		
		System.out.println(max);
	}
	
	static void bfs() {
		queue.offer(new Node(0, 0, 1, 1 << map[0][0]));
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			
			 max = Math.max(max, node.cnt);

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || (node.visit & 1 << map[ny][nx]) != 0 ) continue;
				queue.offer(new Node(ny, nx, node.cnt+1, node.visit | 1 << map[ny][nx]));
			}
		}
		
	}
	
	
	static class Node{
		int y, x, cnt, visit;
		public Node(int y, int x, int cnt, int visit) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.visit = visit;
		}
	}
	

}
