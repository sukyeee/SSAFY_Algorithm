package live0818;


import java.util.Scanner;
/**
 * @author THKim
 */
public class AdjListTest {

	static class Node{
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static Node[] adjList;
	static int N;
	static boolean visited[];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		
		adjList = new Node[N];
		visited = new boolean[N];
		
		for (int i = 0; i < E; i++) { // 간선정보에 해당하는 부분만 덮어씀
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		dfs(0);
	}

	private static void dfs(int cur) {
		
		visited[cur] = true;
		System.out.print((char)(cur+'A'));
		
		// 현 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for (Node temp= adjList[cur]; temp != null; temp = temp.next) {
			if(!visited[temp.to]) { // 방문하지 않았으며 인접한 경우
				dfs(temp.to);
			}
		}
		
	}

}
