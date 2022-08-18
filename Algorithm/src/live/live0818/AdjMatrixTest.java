package live.live0818;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/**
 * @author THKim
 */
public class AdjMatrixTest {

	static int[][] adjMatrix;
	static int N;
	static boolean visited[];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		
		adjMatrix = new int[N][N]; // 0으로 자동 초기화
		visited = new boolean[N];
		
		for (int i = 0; i < E; i++) { // 간선정보에 해당하는 부분만 덮어씀
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1; // 무향그래프
		}
				
//		bfs();
		dfs(0);
	}
	
	public static void dfs(int cur) {
		
		visited[cur] = true;
		System.out.print((char)(cur+'A'));
		
		// 현 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for (int i = 0; i < N; i++) {
			if(!visited[i] && adjMatrix[cur][i] != 0) { // 방문하지 않았으며 인접한 경우
				dfs(i);
			}
		}
	}

	
	private static void bfs() {
		//0정점 시작점
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];  // 방문관리 배열
		
		visited[0] = true;
		queue.offer(0);
		
		while(!queue.isEmpty()) {
			
			int cur = queue.poll();
			System.out.print((char)(cur+'A'));
			
			// 현 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[cur][i] != 0) { // 방문하지 않았으며 인접한 경우
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println();
	}

}
