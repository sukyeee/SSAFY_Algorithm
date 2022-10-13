package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class SWEA2383점심식사시간 {
    static int N, M, S;
    static int match[];

    static Point[] man;
    static Point[] stair;
    static int[] length;

    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = 0; // 사람 수
            S = 0; // 계단 수

            man = new Point[10]; // 최대 사람은 10명(위치 좌표를 저장)
            stair = new Point[2]; // 계단은 2개(위치 좌표를 저장)
            length = new int[2];// 계단의 길이 저장
            for (int i = 1; i <= N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == 1)
                        man[M++] = new Point(i, j); // 사람의 위치 좌표 저장 
                    else if (tmp != 0) {
                        stair[S] = new Point(i, j);
                        length[S] = tmp;
                        S++;
                    }
                }
            }

            answer = Integer.MAX_VALUE;
            match = new int[M];
            dfs(0); // 모든 경우의 수를 구한다.
            System.out.println("#" + tc + " " + answer);
        }

    }

    // 중복순열: 모든 경우의 수를 구한다 최대 2^10
    private static void dfs(int depth) {
        if (depth == M) {
            solve();
            return;
        }
        for (int i = 0; i < 2; i++) {
            match[depth] = i;
            dfs(depth + 1);
        }
    }

    // 각사람이 어느 계단을 이용할 지 정해졌을 때에 필요한 시간을 계산하는 함수
    private static void solve() {
        PriorityQueue<Integer> pq0 = new PriorityQueue<>();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            if (match[i] == 0)
                pq0.add(dist(man[i], stair[0])); // 계단까지의 거리 저장 
            else
                pq1.add(dist(man[i], stair[1]));
        }

        int man = M; // 남은 이용자
        int[] stair0 = new int[3]; // 계단을 이용하는 사람들의 남은 이용시간
        int[] stair1 = new int[3];

        int time = 0;
        while (true) {

            // 종료 조건 : 계단을 모든 이용자가 이용할 때 까지.
            if (man == 0) {
                boolean flag = true;
                for (int i = 0; i < 3; i++) {
                    if (stair0[i] != 0) {
                        flag = false;
                        break;
                    }
                    if (stair1[i] != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    break;
            }

            for (int i = 0; i < 3; i++) { // 계단은 최대 3명까지 동시에 이용할 수 있다.
                if (stair0[i] == 0) { // 현재 이용하는 사람이 없을때
                    if (!pq0.isEmpty()) { // 이용할 사람이 있고(큐가 비어있지 않다면)
                        if (pq0.peek() <= time) { // 도착했다면
                            man--; // 남은 이용자수를 내린다.
                            stair0[i] = length[0];// 해당 계단의 이동시간을 주고
                            pq0.poll();
                        }
                    }
                } else { // 현재 계단을 사용하고 있다면
                    stair0[i]--; // 값을 내리고
                    if (stair0[i] == 0) {// 계단을 이용하고 있지 않다면.
                        if (!pq0.isEmpty()) {
                            if (pq0.peek() <= time) {
                                man--; // 이용자를 내린다.
                                stair0[i] = length[0];
                                pq0.poll();
                            }
                        }
                    }
                }

                if (stair1[i] == 0) { // 현재 이용하는 사람이 없을때
                    if (!pq1.isEmpty()) { // 이용할 사람이 있고(큐가 비어있지 않다면)
                        if (pq1.peek() <= time) { // 도착했다면
                            man--; // 남은 이용자수를 내린다.
                            stair1[i] = length[1];// 해당 계단의 이동시간을 주고
                            pq1.poll();
                        }
                    }
                } else { // 현재 계단을 사용하고 있다면
                    stair1[i]--; // 값을 내리고
                    if (stair1[i] == 0) {// 계단을 이용하고 있지 않다면.
                        if (!pq1.isEmpty()) {
                            if (pq1.peek() <= time) {
                                man--; // 이용자를 내린다.
                                stair1[i] = length[1];
                                pq1.poll();
                            }
                        }
                    }
                }

            } // end of for

            time++;
        } // end of while

        if (time < answer)
            answer = time;
    }

    private static int dist(Point man, Point stair) {
        return Math.abs(man.r - stair.r) + Math.abs(man.c - stair.c);
    }
    static class Point { // 방에서의 위치를 나타내는 클래스
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
/*
10
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0
6
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 0 0
2 0 1 0 0 0
0 0 2 0 0 0
6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 0
0 0 0 2 0 4
7
0 0 0 0 0 0 0
0 0 0 0 0 0 4
0 0 0 0 1 0 0
1 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 2 0 0 0 0 0
7
0 0 0 0 0 0 0
7 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
2 0 0 0 0 1 0
0 0 0 0 0 0 0
8
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 0
8
3 0 0 0 0 0 5 0
0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 0 1 1 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
9
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 8
7 0 0 0 0 1 0 0 0
0 0 0 0 0 1 1 0 0
0 0 0 0 0 0 0 0 0
1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
3 0 1 0 1 0 0 0 0 2
1 1 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
*/