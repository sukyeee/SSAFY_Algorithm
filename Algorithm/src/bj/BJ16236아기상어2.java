package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16236아기상어2 {

	static int N;
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dx[] = { 0, 0, -1, 1 };
	static int map[][];
	static boolean visit[][];
	static Node shark;
	static int time;
	static int depth;
	static int eatCnt;
	static int minDis;
	static Deque<Node> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					shark = new Node(i, j, 2); // 상어의 현재 위치, 크기
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

		map[shark.y][shark.x] = 0; // 상어가 출발한 자리

		while (true) {

			// 물고기한테 갈 수 있는지 체크 ( 큰 물고기가 있으면 지나가지 못함)

			for (int i = 0; i < N; i++)
				Arrays.fill(visit[i], false);
			q.clear();
			depth = 0;
			minDis = Integer.MAX_VALUE;

			bfs(shark.y, shark.x); // 상어의 위치에서 시작
			
			if (minDis == Integer.MAX_VALUE)
				break; // 먹을 수 있는 물고기가 없는 경우

			eatCnt++;
			if (eatCnt == shark.size) {
				shark.size++;
				eatCnt = 0;
			}

			map[shark.y][shark.x] = 0; // 먹은 물고기 자리는 빈칸
			time += minDis;

		}

		System.out.println(time);

	}

	static void bfs(int y, int x) {
		// 물고기의 위치에 도달할 수 있으면 return
		q.offer(new Node(y, x, map[y][x]));
		visit[y][x] = true;

		while (!q.isEmpty()) {
			int q_size = q.size();
			depth++;

			for (int i = 0; i < q_size; i++) {
				Node e = q.poll();

				for (int d = 0; d < 4; d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];

					if (py < 0 || py >= N || px < 0 || px >= N || map[py][px] > shark.size || visit[py][px])
						continue;

					// 물고기를 먹을 수 있는 경우, 자신보다 작으면 바로 먹기!!
					if (map[py][px] != 0 && map[py][px] < shark.size) {

						// 바로 종료하는게 아니라, 위에 있는거, 왼쪽에 있는거 우선순위 판단해줘야함.

						if (depth < minDis) { // 거리가 제일 작으면 갱신
							minDis = depth;
							shark.y = py;
							shark.x = px;
						} else if (depth == minDis) { // 거리가 같다면

							if (py < shark.y) { // 위치가 더 위에 있으면 갱신

								minDis = depth;
								shark.y = py;
								shark.x = px;
							} else if (py == shark.y) {

								if (px < shark.x) { // 위치가 더 왼쪽에 있으면 갱신
									minDis = depth;
									shark.y = py;
									shark.x = px;
								}

							}

						}

					}

					// 물고기를 먹을수는 없고 지나는 것만 가능한 경우
					visit[py][px] = true;
					q.offer(new Node(py, px, map[py][px]));

				}

			}
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

/*
 * 2 9 3 3 1
 */
