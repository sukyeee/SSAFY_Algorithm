package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17471게리맨더링 {

	static int N;
	static int K;
	static int people[];
	static int list[][];
	static boolean src[];
	static int tgt[];
	static boolean visited[];
	static int sum;
	static int Min = Integer.MAX_VALUE;
	static int s;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		list = new int[N+1][N+1];
		src = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
	
		// 각 구역, 인접한 구역 정보 		
        for(int i = 1; i<= N; i++) { // 1 ~ N번 구역까지 
			st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken()); // 반복 입력 횟수 
            for(int j = 0; j < K; j++) {
                int a = Integer.parseInt(st.nextToken());
                list[i][a] = 1;  // 연결된 지역은 1로 표기
            }
        }
        
        // 입력 완 
        

    	// 0 ~ N/2 부분집합 구하기
        for(int i = 0; i<(N/2)+1; i++) { 
            K = i;
            Arrays.fill(src, false);
            subset(1,0);
        }
		
		System.out.println(Min == Integer.MAX_VALUE ? -1 : Min);
	}


		static void subset(int start, int srcIdx) {
		    if(srcIdx == K) {
		        sum = Integer.MAX_VALUE;
		        tgt = new int[N+1];
		        // 선택된 숫자는 1 , 선택되지 않은 숫자는 0 
		        for(int i = 1; i <= N; i++) {
		            if(src[i]) tgt[i] = 1;
		            else tgt[i] = 0;
		        }
		        
		        check();
		        Min = Math.min(Min, sum);
		        return;
		    }
		    for(int i = start; i <= N; i++) {
		        src[i]=true;
		        subset( i+1, srcIdx+1 );
		        src[i]=false;
		    }
		    
		}
		
		static void check() {
	        
	        visited = new boolean[N+1];
	        int people1 = 0;
	        int people2 = 0;
	        
	        for(int i = 1; i <= N; i++) {
	            // 1인 곳만 방문
	            if(tgt[i] == 1 && !visited[i]) {  
	                s = 0;
	                dfs(i);
	                people1 = s;
	                break;
	            }
	        }
	        
	        for(int i = 1; i <= N; i++) {
	        	// 0인 곳만 방문 
	            if(tgt[i] == 0 && !visited[i]) {  
	                s = 0;
	                dfs(i);
	                people2 = s;
	                break;
	            }
	        }
	        
	        // dfs완료 후에도 visited되지 않았다면? ->  나눌수 없는 경우 
	        for(int i = 1; i <= N; i++) {
	            if(!visited[i]) return;
	        }
	        sum = Math.abs(people2-people1);

			
		}
		
		  static void dfs(int x) {
			  
		        visited[x] = true;
		        s = s + people[x];
		        
		        for(int i = 1; i <= N; i++) {
		            // 1. 방문하지 않았고
		        	// 2. 같은 지역구에 속하면서
		        	// 3. 서로 연결되어있는 곳만 방문 
		            if(!visited[i] && tgt[i] == tgt[x] && list[i][x] == 1) {
		                dfs(i);
		            }
		        }
		 }
		 
}

/*

6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2

*/