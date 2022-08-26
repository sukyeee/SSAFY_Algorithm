package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_녹색옷을입은애가젤다지_4485 {

	static int N, min;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();
	static int callCnt; //**
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while(true) {
			
			callCnt = 0; //**
			
			N = Integer.parseInt(br.readLine());
			if( N == 0 ) break;
			
			map = new int[N][N];
			visit = new boolean[N][N];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visit[0][0] = true;
			dfs(0, 0, map[0][0]);
			
			sb.append("Problem ").append(t).append(": ").append(min).append("\n");
			sb.append("Problem ").append(t).append(": ").append(callCnt).append("\n"); //**
			t++;
		}
		System.out.println(sb);
		
	}

	static void dfs( int y, int x, int c ) {
		callCnt++; //**
		// 기저 조건
		if( y == N-1 && x == N-1 ) {
			min = Math.min(min, c); // 최소 비용 갱신
			return;
		}
		
		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if( ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
			
			// 가지치기
			if( c + map[ny][nx] >= min ) continue;
			visit[ny][nx] = true;
			dfs(ny, nx, c + map[ny][nx]);
			visit[ny][nx] = false;
		}
		
	}
}
