package live.live0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class TopologySortTest {
	static int V, E;
	static Node[] adjList;
	static int[] inDegree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
	
		adjList = new Node[V+1]; // 각 정점별 인접리스트
		inDegree = new int[V+1]; // 정점별 진입차수
		
		
		for (int i = 0; i < E; i++) {
			 st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
		
			// 유향 처리
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
			ArrayList<Integer> list = topologySort();
			if(list.size() == V) {
				for (Integer i : list) {
					System.out.println(i+" ");
				}
			}
			else {
				System.out.println("cycle..");
			}
			
		
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

}
