package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 산악구조로봇 {
   static int T;
   static int N;
   static int[][] map;
   static int[][] visited;
   static int[] dy = { -1, 1, 0, 0 };
   static int[] dx = { 0, 0, -1, 1 };
   static int[][] cost;
   
   static int answer;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      T = Integer.parseInt(br.readLine());

      for (int t = 1; t <= T; t++) {
         N = Integer.parseInt(br.readLine());

         map = new int[N + 1][N + 1];
         visited = new int[N + 1][N + 1];
         cost = new int[N+1][N+1]; // 차이값 누적  
         // cost 배열 초기화 
         for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < N+1; j++) {
				cost[i][j] = Integer.MAX_VALUE; 
			}
		}
         
         answer = 0;
         for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
            }
         }
         resetVisited();
         bfs(1, 1);
         System.out.println("#" + t + " " + cost[N][N]);

      }
   }

   static void resetVisited() {
      for (int i = 1; i <= N; i++) {
         for (int j = 1; j <= N; j++) {
            visited[i][j] = Integer.MAX_VALUE;
         }
      }
   }

   static void bfs(int y, int x) {
      PriorityQueue<Node> pq = new PriorityQueue<>();

      pq.offer(new Node(y, x, 0));
      cost[y][x] = 0; // 첫 시작 cost는 항상 0
      
      while (!pq.isEmpty()) {
         int size = pq.size();

         for (int s = 0; s < size; s++) {
            Node cur = pq.poll();

            for (int d = 0; d < 4; d++) {

               int ny = cur.y + dy[d];
               int nx = cur.x + dx[d];

               if (ny < 1 || nx < 1 || ny > N || nx > N)
                  continue;
 
               int diff = 0; // 차이값 계산 

               // 현재의 값에 다음 위치으 값을 누적
               // 같은 곳
               if (map[cur.y][cur.x] == map[ny][nx]) {
            	   diff = 1;
               }
               // 낮은 곳
               else if (map[cur.y][cur.x] > map[ny][nx]) {
            	   diff = 0;
               }
               // 높은 곳
               else {
            	   diff = Math.abs(map[cur.y][cur.x] - map[ny][nx]) * 2;
               }
               
               if( cost[ny][nx] > cost[cur.y][cur.x] + diff ) { // 기존 cost의 diff보다,  거쳐가는 diff값이 더 작을때만 갱신
            	   cost[ny][nx] = cost[cur.y][cur.x] + diff ;
            	   pq.offer(new Node(ny, nx, diff));
               }

            }
         }
      }

   }

   static class Node implements Comparable<Node> {
      int y;
      int x;
      int cost;

      public Node(int y, int x, int cost) {
         this.y = y;
         this.x = x;
         this.cost = cost;
      }

      @Override
      public int compareTo(Node o) {
         return this.cost - o.cost;
      }
   }

}

/*
5
4
9 5 7 9
8 4 2 5
7 6 5 4
8 8 9 5
6
1 1 1 1 1 1
9 9 9 9 9 1
9 9 1 1 1 1
9 9 1 9 9 9
9 9 1 9 9 9
9 9 1 1 1 1
10
4 0 8 1 6 9 6 8 6 2
2 7 7 7 9 9 7 2 9 8
5 2 5 0 0 6 4 5 0 5
0 0 3 9 1 8 9 2 8 3
0 0 5 5 3 4 1 2 4 4
9 5 4 4 0 5 7 0 6 0
3 2 8 2 3 4 7 8 3 5
0 2 1 5 0 2 6 1 9 2
5 9 5 5 0 1 6 4 2 5
4 2 2 6 1 8 2 8 5 4
20
4 7 9 8 7 1 4 2 3 4 7 7 6 0 2 7 7 7 2 8
2 8 2 7 2 9 1 2 6 3 0 4 6 7 7 9 4 9 1 5
7 5 9 8 1 9 0 2 3 8 7 0 1 8 9 6 8 4 4 6
8 0 3 4 3 9 7 3 8 9 5 1 5 9 8 9 8 0 3 8
6 2 8 2 7 3 9 8 3 2 5 7 8 1 0 8 5 2 1 5
3 8 3 4 5 5 2 4 5 2 2 7 1 3 4 7 0 6 2 0
5 5 5 3 4 4 3 4 2 4 5 8 8 9 5 2 1 1 6 4
2 2 8 1 1 8 7 7 5 1 4 3 4 9 3 1 6 2 1 0
4 3 3 8 9 1 5 5 2 4 5 9 6 5 9 4 2 8 0 6
3 6 7 2 5 1 1 0 9 2 7 3 8 8 9 3 5 2 4 6
4 1 9 8 6 5 4 1 8 2 2 7 8 1 3 3 8 0 7 8
7 7 5 5 2 1 3 2 8 1 1 3 5 7 1 8 3 5 4 6
3 5 1 1 4 8 1 9 1 1 3 4 6 8 6 7 0 7 3 2
0 3 7 3 3 5 4 7 7 7 7 8 3 7 3 6 4 0 8 2
3 6 3 1 8 2 8 7 5 9 2 1 4 1 6 1 3 8 6 3
8 4 6 2 3 0 9 1 2 5 7 1 0 2 1 8 5 6 0 6
2 2 1 8 0 6 2 5 8 9 8 0 8 8 3 2 0 3 4 8
4 2 2 4 5 2 1 6 6 3 1 4 1 7 5 9 6 7 1 3
2 4 3 8 5 6 3 5 0 6 3 1 2 1 9 8 8 3 9 7
2 1 9 9 0 3 4 2 1 7 9 7 9 6 5 6 1 1 9 6
30
1 8 8 5 8 8 7 5 5 5 2 6 7 4 9 6 0 5 6 9 9 5 9 0 6 8 8 1 7 4
2 7 7 9 9 6 5 7 9 7 6 6 8 2 5 5 3 5 0 3 2 3 5 4 3 7 1 1 4 1
5 9 8 9 1 2 0 0 7 8 8 2 6 4 4 3 2 9 5 0 5 7 8 6 5 2 1 8 4 5
3 5 7 0 1 1 4 5 7 8 4 5 4 7 2 3 2 4 5 0 1 3 8 1 8 0 6 7 3 5
5 0 2 4 3 0 9 8 0 3 0 2 2 8 2 3 7 9 4 1 0 4 9 5 8 6 7 2 0 6
5 5 8 0 8 7 4 2 3 5 2 2 4 4 5 2 0 4 3 0 4 3 4 7 4 9 7 8 9 2
1 7 1 9 3 3 4 1 5 4 7 4 9 1 6 9 0 4 4 5 7 4 3 9 6 5 3 4 6 5
8 3 5 5 9 3 0 5 3 3 8 7 1 4 3 9 0 0 6 0 3 0 5 4 8 1 9 7 4 1
2 3 1 7 3 0 2 2 9 7 6 0 6 7 6 3 5 8 8 9 2 1 5 5 2 7 2 1 1 3
9 5 9 4 2 5 7 1 5 2 8 5 1 8 9 6 0 7 4 4 0 4 2 5 8 1 1 1 6 8
6 9 1 5 2 6 4 9 3 2 5 0 4 3 0 0 6 7 6 5 6 6 9 2 4 4 7 1 2 9
9 3 2 5 2 1 8 9 7 7 8 1 0 0 0 5 1 8 8 9 5 8 8 5 3 5 7 6 0 5
2 1 6 0 5 7 8 2 9 0 5 1 5 6 4 1 9 5 3 8 1 3 0 4 1 9 4 8 4 1
5 2 6 9 8 7 7 3 3 8 8 1 7 3 6 1 4 8 9 9 9 8 4 6 1 5 3 5 5 1
0 9 1 0 8 9 2 1 6 1 2 9 4 3 7 8 7 7 8 8 8 9 7 8 9 2 1 2 4 3
8 8 4 6 2 0 1 8 8 6 6 0 9 0 1 2 2 6 9 7 2 8 3 9 1 1 7 7 1 5
9 1 2 8 4 7 1 6 2 2 6 7 9 4 0 2 9 7 2 6 6 7 7 0 3 3 9 4 7 8
9 1 5 5 6 4 4 6 6 7 7 4 7 9 8 2 9 7 5 9 3 6 8 8 9 7 0 4 4 4
0 5 1 2 9 9 5 0 5 3 1 6 1 1 5 9 0 4 4 1 4 2 2 7 7 6 5 3 5 8
0 7 4 6 6 2 4 5 1 6 0 8 5 9 8 7 8 2 2 3 0 9 1 3 1 3 4 9 9 5
3 6 5 2 7 7 6 6 2 5 8 9 7 9 0 5 9 7 5 8 4 7 4 3 4 6 6 6 0 3
3 9 6 1 6 0 7 6 5 1 3 6 0 5 7 4 8 0 9 8 2 6 4 7 1 4 5 5 6 4
1 0 2 0 9 0 2 8 2 3 6 3 8 2 2 7 7 1 0 5 6 5 7 2 7 1 1 6 6 5
3 8 0 7 2 9 1 2 9 7 0 2 9 8 6 7 5 0 1 3 7 2 5 7 7 7 4 4 8 7
7 2 3 8 6 6 7 2 8 9 9 0 4 6 2 7 5 5 0 9 1 1 7 6 3 6 3 7 8 8
5 7 6 4 9 7 3 0 4 6 9 8 6 8 5 7 8 6 6 2 5 3 2 9 8 0 2 3 5 7
3 2 4 2 7 1 9 6 0 6 5 5 3 1 6 1 4 3 4 6 8 6 1 6 6 2 6 9 0 2
9 2 9 0 9 5 2 7 4 7 7 6 0 6 2 7 9 6 5 4 4 8 3 9 0 5 1 0 3 0
2 9 7 6 6 4 3 5 3 3 6 2 9 4 0 1 8 4 5 6 8 6 8 9 4 4 2 2 3 7
4 8 9 6 6 0 0 0 0 6 0 2 5 5 9 2 8 5 4 1 4 6 2 3 8 6 6 6 0 6
*/