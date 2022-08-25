
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BJ_적록색약_10026 {

	static int N;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1,  0, 0 }; // 상 하 좌 우 
	static int dx[] = {  0, 0, -1, 1 };
	static int ans1, ans2;
	static Deque<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완
		
		// 적록색약이 아닌 사람
		for (int i = 0; i < N; i++) {
			for(int j=0;j<N;j++) {
				if(!visit[i][j]) {
					bfs(i, j);
					ans1++;
				}
			}
		}
		
		for(int i=0;i<N;i++) Arrays.fill(visit[i], false);
		
		// 색약의 경우, R과 G는 같은 것으로 본다
		for (int i = 0; i < N; i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 'G') map[i][j] = 'R';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for(int j=0;j<N;j++) {
				if(!visit[i][j]) {
					bfs(i, j);
					ans2++;
				}
			}
		}
		
		
		System.out.println(ans1 + " " + ans2);
		
		
	}
	
	static void bfs(int y, int x ) {
		
		q.offer(new Node(y,x));
		visit[y][x] =true;
		
		while(! q.isEmpty() ) {
			int q_size = q.size();
			
			for(int i=0;i<q_size;i++) {
				
				Node e = q.poll();
				
				for(int d=0;d<4;d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					
					if(py < 0 || py >= N || px < 0 || px >= N || visit[py][px] || 
							map[y][x] != map[py][px]) continue;
					
					visit[py][px] = true;
					q.offer(new Node(py,px));
					
				}
				
			}
			
		}
		
		
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		
	}

}
