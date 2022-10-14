package sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA2105디저트카페2 {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static boolean[] dessert;

    static int[] di = {1, 1, -1, -1};
    static int[] dj = {1, -1, -1, 1};

    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            ans = -1;

            N = sc.nextInt();
            map = new int[N][N];
            visit = new boolean[N][N];
            dessert = new boolean[101];

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    map[i][j] = sc.nextInt();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        Arrays.fill(visit[k], false);
                    }
                    Arrays.fill(dessert, false);

                    dfs(i, j, i, j, 1, 0);
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void dfs(int si, int sj, int i, int j, int cnt, int dir) {

    	
    	if( i == si + 1 && j == sj - 1 ) {
    		if( cnt == 2 ) return;
    		ans = Math.max(ans, cnt);
    		return;
    	}
    	
    	visit[i][j] = true;
    	dessert[map[i][j]] = true;
    	
    	for (int d = dir; d < 4; d++) {
			int py = i + di[d];
			int px = j + dj[d];
			
			if(py < 0 || px < 0 || py >= N || px >= N || visit[py][px] || dessert[map[py][px]] ) continue;
			
			dfs(si, sj, py, px, cnt + 1, d);
			
		}
    	
    	visit[i][j] = false;
    	dessert[map[i][j]] = false;
    	
    	
    }
}