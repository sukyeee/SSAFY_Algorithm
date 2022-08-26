package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import bj.BJ_최단경로_1753.Edge;

//dijstra
public class BJ4485녹색옷입은애가젤다지 {
	static int N;
	static int ans;
	static boolean[][] visit;
	static int[][] cost; // 핵심
	static int[][] map;
	static List<Edge> node[];
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Edge> pq = new PriorityQueue<>( (e1, e2) -> e1.c - e2.c);
	static int INF = Integer.MAX_VALUE;
	static int min = Integer.MAX_VALUE;
	static int zero_cnt;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;;t++) {
			
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			visit = new boolean[N][N];
			cost = new int[N][N];
			node = new ArrayList[N];
			for(int i=0;i<N;i++) node[i] = new ArrayList<>();
			
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}	
			// 입력 완
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cost[i][j] = INF;
				}
			}
			
		
			dijstra();
	
			ans = cost[N-1][N-1];
		
			sb.append("Problem ").append(t).append(": ").append(ans).append("\n");
		} // testcase
		
		
		System.out.println(sb);
		
		
	}
	static void dijstra() {
		
		// 가중치가 제일 작은 경로 찾아서 0,0 ->  N-1 N-1 로 이동
		// 시작은 항상 0,0 부터
		cost[0][0] = map[0][0];
		
		pq.offer(new Edge(0, 0, cost[0][0]));
		
		while( ! pq.isEmpty() ) {
			//꺼내면 비용이 가장 작은 것
			Edge e = pq.poll();
			
			for(int d=0;d<4;d++) {
				
				int py = e.y + dy[d];
				int px = e.x + dx[d];
				if( py < 0 || py >= N || px < 0 || px >= N || visit[py][px]) continue;
		
				// visit check
				if( visit[py][px] ) continue;
		
				if( ! visit[py][px] && cost[e.y][e.x] + map[py][px] < cost[py][px] ) {
					cost[py][px] = cost[e.y][e.x] + map[py][px];
					visit[py][px] = true;
					
					pq.offer(new Edge(py, px, cost[py][px]));
				}
				
			}
			
		
			
			
		}
		
	}
	
	static class Edge {
		int y;
		int x;
		int c;
		public Edge(int y, int x, int c ) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
		}
	
		
	}
}
