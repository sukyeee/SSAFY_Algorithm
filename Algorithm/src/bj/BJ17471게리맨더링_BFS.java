package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17471게리맨더링_BFS {

	static int N, K;
	static int population[];
	static List<Integer> matrix[];
	static boolean select[];
	static List<Integer> first = new ArrayList<>();
	static List<Integer> second = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static boolean visit[];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		matrix = new ArrayList[N+1];
		select = new boolean[N+1];
		visit = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			matrix[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) {
				
				int a = Integer.parseInt(st.nextToken());
				matrix[i].add(a);
				
			}
		}
		// 입력 완 
		
		
		
		// 부분집합으로 나누기 
		
		subset(1);
		
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
		
	}
	
	static void bfs() {
		
		int sum1 = 0;
		int sum2 = 0;
		flag = false;
		
		Deque<Integer> q = new ArrayDeque<>();
		// 선거 구들이 이어지는지 확인 
		// 첫 시작이 1이라 할 때, 1과 연결되는 2와 4 같이 봐야 
		
		int v = first.get(0);
			q.offer(v);
			visit[v] = true;
			while(!q.isEmpty()) {
				
				int q_size = q.size();
				for (int k = 0; k < q_size; k++) {
					int e = q.poll();
					
					for (int i = 0; i < matrix[e].size(); i++) {
						
						if( visit[matrix[e].get(i)] || !select[matrix[e].get(i)]  ) continue;
						
						q.offer(matrix[e].get(i));
						visit[matrix[e].get(i)] = true;
					}
				}
				
				
			}
		
		

		q = new ArrayDeque<>();
		// 선거 구들이 이어지는지 확인 
		
		v = second.get(0);
			q.offer(v);
			visit[v] = true;
			while(!q.isEmpty()) {
				int q_size = q.size();
				for (int k = 0; k < q_size; k++) {
					int e = q.poll();
					for (int i = 0; i < matrix[e].size(); i++) {
						
						if( visit[matrix[e].get(i)] || select[matrix[e].get(i)]   ) continue;

						q.offer(matrix[e].get(i));
						visit[matrix[e].get(i)] = true;
					}
				}
				
			}
		
		
		
		// visit 가 모두 true 가 아니면, 두 선거구로 나눌 수 없다. return -1
		for (int i = 1; i <= N; i++) {
			if( !visit[i] ) {
				flag = true;
			}
		}
		
		// 이어진다면 ? first 와 second의 차이 각각 계산 
		
		for (int a : first) {
			sum1 += population[a];
		}
		
		for (int a : second) {
			sum2 += population[a];
		}
		
		if(!flag) min = Math.min(min, Math.abs(sum1 - sum2));
		
	
	}
	
	static void subset(int srcIdx) {
		
		if( srcIdx == N+1 ) {
			
			// 부분집합으로 선택된 부분을 1 번째 구간으로 지정, 나머지를 2 번째 구간 
			
			for (int i = 1; i <= N; i++) {
				if(select[i]) {
					first.add(i);
				}
				else {
					second.add(i);
				}
			}
			
			if( first.size() != 0 && second.size() != 0 ) {
				visit = new boolean[N+1];
			
				bfs(); 
				
				
			}
			
			first.clear();
			second.clear();
			
			return;
		}
		
		
		
		select[srcIdx] = true;
		subset( srcIdx + 1 );
		select[srcIdx] = false;
		subset( srcIdx + 1 );
		
		
	}
	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		
	}

}
