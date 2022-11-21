package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// BFS
public class SWEA4727견우와직녀 {

	static int h, w, m;
    static int[][] map = new int[10][110];

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = w = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bfs();
            System.out.println("#" + tc + " " + ans);
        }
    }

    

    static Deque<Pos> q = new ArrayDeque<Pos>();
    static int[][][] visit = new int[10][10][2];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static int ans = Integer.MAX_VALUE;

    static void bfs() {
        int sx = 0;
        int sy = 0;
        q.add(new Pos(sx, sy, 0, 0));
        memset(visit);
        visit[sx][sy][0] = 0;
        visit[sx][sy][1] = 0;
        while (!q.isEmpty()) {
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;
            int t = now.t;
            int made = now.made; // 다리를 만든경우와 만들지않은경우 ?
            // System.out.println(x+", "+y);
            
            // 직녀에게 도착 시 최솟값 갱신 
            if (x == h - 1 && y == w - 1) {
                ans = Math.min(ans, t);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1)
                    continue;
                
                // 건너기 가능
                if (map[nx][ny] >= 1) {
                    // 길인 경우
                    if (map[nx][ny] == 1) {
                        if (visit[nx][ny][made] == -1 || visit[nx][ny][made] > t ) {
                            q.offer(new Pos(nx, ny, t + 1, made));
                            visit[nx][ny][made] = t + 1;
                            continue;
                        }
                    }
                    // 오작교인 경우
                    else {
                        if (map[x][y] <= 1) { // 기존 위치 ( 4방향 이동 전) 이 1이하라면 ?
                            if (visit[nx][ny][made] == -1 || visit[nx][ny][made] > t ) {
                                int nt = t + (map[nx][ny] - (t % map[nx][ny]));
                                q.offer(new Pos(nx, ny, nt, made));
                                visit[nx][ny][made] = t + 1;
                                continue;
                            }
                        }
                    }
                }
                // 절벽
                else {
                    // 임시 오작교 만들 수 있음 : 아직 만든 적 없고, 이전 블록이 오작교가 아니었음
                    if (made == 0 && map[x][y] <= 1) { // 이전블록 map[x][y]가 1이하면 오작교아님 
                        if (visit[nx][ny][1] == -1 || visit[nx][ny][1] > t ) { 
                            // 교차하는가
                            int garo = 0;
                            int sero = 0;
                            for (int c = 0; c < 4; c++) {
                                int cx = nx + dx[c];
                                int cy = ny + dy[c];
                                if (cx < 0 || cx > h - 1 || cy < 0 || cy > w - 1)
                                    continue;
                                // 상하좌우에 1이 2개이상없으면 ?오작교 이런
                                if (map[cx][cy] == 0) {
                                    if (i % 2 == 0)
                                        sero++;
                                    else
                                        garo++;
                                }
                            }
                            if (garo > 0 && sero > 0)
                                continue; // 건널 수 없
                            int nt = t + (m - (t % m));
                            q.offer(new Pos(nx, ny, nt, 1));
                            visit[nx][ny][1] = t + 1;
                            continue;
                        }
                    }
                }
            }
        }

    }

    static void memset(int[][][] arr) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < 2; k++) {
                    visit[i][j][k] = -1;
                }
            }
        }
    }
static class Pos {
        int x, y, t, made;

        public Pos(int x, int y, int t, int made) {
            super();
            this.x = x;
            this.y = y;
            this.t = t;
            this.made = made;
        }

        @Override
        public String toString() {
            return "Pos [x=" + x + ", y=" + y + ", t=" + t + "]";
        }

    }
}
