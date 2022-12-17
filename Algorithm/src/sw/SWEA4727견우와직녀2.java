package sw;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA4727견우와직녀2 {
    static int ans, T, N, M;
    static int[][] map;
    static boolean[][] visit;
    static List<int[]> list = new ArrayList<>();

    // 상하좌우
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            list.clear();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int m = map[i][j] = Integer.parseInt(st.nextToken());
                    if (m == 0) list.add(new int[] { i, j }); // 절벽!!!
                }

            }

            ans = Integer.MAX_VALUE;

            for (int[] yx : list) {
                int y = yx[0];
                int x = yx[1];

                visit = new boolean[N][N];
                map[y][x] = M;

                bfs();

                map[y][x] = 0;

            }

            System.out.println("#" + t + " " + ans);
        } // testcase

    }


    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 1));
        visit[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int y = node.y;
            int x = node.x;
            int time = node.time;

            if (y == N - 1 && x == N - 1) {
                ans = Math.min(ans, time - 1);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visit[ny][nx] || map[ny][nx] == 0) continue;
          
                if (map[ny][nx] == 1) {
                    q.offer(new Node(ny, nx, time + 1));
                    visit[ny][nx] = true;
                } else if (map[ny][nx] > 1) {
                    if (map[y][x] > 1) continue; // 교차로? 면 continue

                    if (time % map[ny][nx] == 0) {
                        q.offer(new Node(ny, nx, time + 1));
                        visit[ny][nx] = true;
                    } else q.offer(new Node(y, x, time + 1));

                }

            }

        }

    } // end bfs


    static class Node {
        int y, x;
        int time;


        public Node(int y, int x, int time) {
            super();
            this.y = y;
            this.x = x;
            this.time = time;
        }

    }
}