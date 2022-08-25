package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 시뮬레이선 + bfs
public class BJ_아기상어_16236 {

	static int N, sy, sx, sSize, sEatCnt, ans;
	static int[][] map;
// 아기상어의 물고기를 찾는 과정 - bfs
	static boolean[][] visit;
	static Queue<Node> queue = new ArrayDeque<>();
// 사방 탐색
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		// 입력 처리
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 9) {
					sy = i;
					sx = j;
				}
				map[i][j] = n;
			}
		}

		// 초기화
		sSize = 2; // 최초 아기상어의 크기

		// 시물레이션
		while (true) {

			// 먹이 사냥이 가능한 동안, 먹이사냥 <= bfs(), bfs() 는 사냥하는 동안 걸린 시간(초)를 return
			// bfs() 가 0을 return 하면 더이상 먹을 고기가 없다.
			// bfs() 가 0이 아닌 값을 return 하면, 먹이 사냥에 걸린 시간이므로 ans 에 누적합으로 더해 준다.
			int cnt = bfs(); // 먹이 사냥에 걸리는 시간
			if (cnt == 0)
				break;
			ans += cnt; // 누적 시간
		}

		System.out.println(ans);

	}

// 호출될 때 마다 가장 가까운(여럿이면 가장 y 작은 그래도 여럿이면 가장 왼쪽의) 물고기를 한머리 먹던가, 못 먹던가
// 먹으면 먹으러 이동한 거리를, 못 먹으면 0 을 리턴
	static int bfs() {
		// 가장 작은 Y, X, Dis(거리)
		int minY = Integer.MAX_VALUE;
		int minX = Integer.MAX_VALUE;
		int minDis = Integer.MAX_VALUE;

		// visit 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}

		// 시작위치부터 queue에 담고 bfs 시작
		visit[sy][sx] = true; // 항상 상어 시작위치에서 출발
		queue.offer(new Node(sy, sx, 0));

		// 상어의 시작위치부터 갈 수 있는 곳을 사방 탐색하여 bfs 로 이동을 반복한다.
		// 이동하는 과정에서 물고기이면서 크기가 자신보다 작으면 => 먹을 수 있는 후보이므로 그 자리의 위치와 거리를 따진다.(가장 가까운....)
		while (!queue.isEmpty()) {

			Node node = queue.poll();

			// 물고기 && 먹을 수 있는
			if (map[node.y][node.x] != 0 && map[node.y][node.x] < sSize) {
				// 먹이 대한 계산

				if (node.d < minDis) { // 물고기까지의 거리가 가장 작으면
					minDis = node.d;
					minY = node.y;
					minX = node.x;
				} else if (node.d == minDis) { // 물고기까지의 거리가 같으면
					if (node.y < minY) { // 물고기의 y 가 작은 걸로
						minDis = node.d;
						minY = node.y;
						minX = node.x;
					} else if (node.y == minY) { // 물고기의 y 가 같으면 x가 작은 걸로
						if (node.x < minX) {
							minDis = node.d;
							minY = node.y;
							minX = node.x;
						}
					}
				}
			}

			// 가지치기
			if (node.d + 1 >= minDis)
				continue;

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				// 이동할 때는, 물고기의 크기가 상어의 크기와 같아도 이동할 수 있다.
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] > sSize)
					continue;

				visit[ny][nx] = true;
				queue.offer(new Node(ny, nx, node.d + 1));
			}
		}

		// 먹이를 먹었던가 못 먹었던가에 대한 결과 처리
		if (minDis == Integer.MAX_VALUE)
			return 0; // 먹을 물고기를 찾지 못했다.
		else {
			sEatCnt++; // 먹은 물고기 수 증가
			if (sEatCnt == sSize) {
				sSize++;
				sEatCnt = 0;
			}

			map[minY][minX] = 0; // 먹은 물고기 자리는 빈칸
			map[sy][sx] = 0; // 상어가 출발한 자리

			// 새로운 상어의 자리 - 먹은 물고기의 자리
			sy = minY;
			sx = minX;
		}

		return minDis; // 먹은 물고기로 이동하는 최소 거리
	}

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}