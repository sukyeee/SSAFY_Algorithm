package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SWEA5648원자소멸시뮬레이션2 {

	private static class Node {
		int x, y, dir, value;

		Node(int x, int y, int dir, int value) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.value = value;
		}
	}

	static int size, result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map = new int[4002][4002];
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tnum = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tnum; t++) {
			size = Integer.parseInt(br.readLine());
			result = 0;
			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				int x = 4000 - (Integer.parseInt(st.nextToken())+1000)*2;
				int dir = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				map[x][y] = value;
				q.add(new Node(x, y, dir, value));
			}
			
			bfs();
			System.out.println("#" + t + " " + result);
		}
	}

	private static void bfs() {

		while(!q.isEmpty()) {
			
			Node e = q.poll();
			
			if(map[e.x][e.y] != e.value) {
				result += map[e.x][e.y];
				map[e.x][e.y] = 0;
				continue;
			}
			
			int py = e.y + dy[e.dir];
			int px = e.x + dx[e.dir];
			
			if(py >= 0 && px >= 0 && py <= 4000 && px <= 4000 ) {
				if (map[px][py] == 0) {
					map[px][py] = e.value;
					q.add(new Node(px, py, e.dir, e.value));
				} else {
					map[px][py] += e.value;
				}
				
			}

			
			map[e.x][e.y] = 0;
			
		}
		
		
	}
}