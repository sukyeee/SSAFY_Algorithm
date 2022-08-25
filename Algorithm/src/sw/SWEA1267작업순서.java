package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA1267작업순서 {
	static int V; // 정점 수
	static int E; // 간선 수
	static int from, to;
	static List<Integer> node[];
	static Node[] adjList;

	static int[] inDegree;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int t=1;t<=10;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
//			node = new ArrayList[V+1];
//			for(int i=1;i<=V;i++) node[i] = new ArrayList<>();
			adjList = new Node[V+1]; // 각 정점별 인접리스트
			inDegree = new int[V+1]; // 정점별 진입차수
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<E;i++) {
				
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				// 유향 그래프 입력
//				node[from].add(to);
				adjList[from] = new Node(to, adjList[from]);

				inDegree[to]++;
			}
			
			
			ArrayList<Integer> list = topologySort();
			if(list.size() == V) {
				for (Integer i : list) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		
		} // testcase 10
		
		
	}
	
	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		// 진입차수가 0인 정점 큐에 넣기
		for(int i=1;i<=V;i++) {
			if(inDegree[i] ==0) queue.offer(i);
		}
		
		// BFS
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur); 
			//여기서 바로 sysout찍어도.
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if(--inDegree[temp.vertex] == 0) queue.offer(temp.vertex);
			}
			
		}
		return list;
			
	}
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
	
		}
		
	}
}
