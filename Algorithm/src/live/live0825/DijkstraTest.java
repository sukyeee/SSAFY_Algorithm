package live.live0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[V][V];
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// start -> end로의 최단경로
		int start = 0; // 출발정점
		int end = V-1; // 도착정점
		
		// 다익스트라 알고리즘에 필요한 자료구조
		int[] D = new int[V]; // 출발지에서 자신으로 오는데 소요되는 최소뵈용
		boolean[] visited = new boolean[V]; // 처리한 정점 여부 
		
		Arrays.fill(D, Integer.MAX_VALUE);
		// 출발 정점 처리
		D[start] = 0;
		
		int min, minVertex;
		
		for (int i = 0; i < V; i++) {
			// step1 . 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			// 방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			min = Integer.MAX_VALUE;
			minVertex = -1;
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > D[j]) {
					min = D[j];
					minVertex = j;
				}
			}
			
			// step2. 방문처리
			visited[minVertex] = true;
			if(minVertex == end) break; // 문제가 start -> end로의 최단이면 탈출, 모든 도착지 구하는거면 주석 처리 
			
			// step3. 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
			for (int j = 0; j < V; j++) {
				if(!visited[j] && adjMatrix[minVertex][j] > 0 && D[j] > D[minVertex]+adjMatrix[minVertex][j] ) {
					D[j] = D[minVertex]+adjMatrix[minVertex][j];
				}
			}
			
		}
		System.out.println(Arrays.toString(D));
		System.out.println(D[end]);
		
	}

}
