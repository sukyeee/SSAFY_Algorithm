package sw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
class SWEA1767프로세서연결하기7
{
    static int T, N;
	static int dx[] = {0,0,-1,1}; // 상 하 좌 우
    static int dy[] = {-1,1,0,0};
    static int map[][];
    static boolean visit[][];
    static List<Dist> processers = new ArrayList<>();
    static int tgt[];
    static int min; // 전선 합 최소 
    static int max; // 프로세서 개수 최대 
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	processers.clear();
        	min = Integer.MAX_VALUE;
        	max = 0;
        	
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if( i != 0 && j != 0 && i != N-1 && j != N-1) processers.add(new Dist(i, j));
						visit[i][j] = true;
					}
				}
			}
			
			dfs( 0, 0, 0 ); // 프로세서 번호 
			
			System.out.println("#" + t + " " + min);
        } // testcase
        
    }
    static void dfs( int idx, int wireSum, int processerCnt ) {
    
    	if(idx == processers.size()) {
    		// 갱신 
    		if(max < processerCnt ) {
    			max = processerCnt;
    			min = wireSum;
    		}
    		if(max == processerCnt) {
    			min = Math.min(min, wireSum);
    		}
    		return;
    	}
    	
    	// 프로세서 하나씩 뽑기 
    	Dist e = processers.get(idx);
    	for (int d = 0; d < 4; d++) {
	    	int py = e.y;
	    	int px = e.x;
	    	int cnt = 0;
	    	
	    	while(true) {
	    	
				py += dy[d];
				px += dx[d];
				
				// 프로세서에 닿기 전에 벽에 닿았으면 ? 거리 저장 
				
				if(py < 0 || px < 0 || py >= N || px >= N) break;
				// 도중에 프로세서를 만난다면 종료 . 연결불가 
				if( visit[py][px] ) {
					cnt = 0;
					break;
				}
				
				cnt++;
				
	    	}
    	
	    	py = e.y;
	    	px = e.x;
	    	for (int i = 0; i < cnt; i++) {
				py += dy[d];
				px += dx[d];
				visit[py][px] = true;
			}
    	
    	
	    	if(cnt == 0) {
	    		dfs(idx+1, wireSum, processerCnt);
	    	}
	    	else {
	    		dfs(idx+1, wireSum+cnt, processerCnt+1);
	    		
	    	   	py = e.y;
		    	px = e.x;
		    	for (int i = 0; i < cnt; i++) {
					py += dy[d];
					px += dx[d];
					visit[py][px] = false;
				}
	    	
	    	}
    	
    	
    	}
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


/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0

*/