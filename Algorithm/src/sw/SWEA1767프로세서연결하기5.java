package sw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
class SWEA1767프로세서연결하기5
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
						// 가장자리가 아닌 것들만 넣기 
						if(i != 0 && j != 0 && i != N-1 && j != N-1 ) {
							processers.add(new Dist(i, j));
						}
					}
				}
			}
			
			tgt = new int[processers.size()];
			
			// 중복순열으로 프로세서들 방향 선택 
			perm(0);
			
			System.out.println("#" + t + " " + min);
			
			
			
		} // testcase
        
    }
    static void perm(int tgtIdx) {
    	
    	if( tgtIdx == tgt.length ) {
    		
    		visit = new boolean[N][N];
    		for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 1) visit[i][j] = true;
				}
			}

    		// 방향이 정해진 상태 
    		simulation();
    		return;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
		}
    	
    	
    }
    static void simulation() {
    	
    	int wireSum = 0;
    	int processerCnt = 0;
    	int wire = 0;
    	for (int i = 0; i < tgt.length; i++) {
			
    		Dist process = processers.get(i);
    		int d = tgt[i]; // 현재 프로세스의 방향 
    		
    		int py = process.y;
    		int px = process.x;
    		wire = 0;
    		while(true) {
    			
    			py += dy[d];
    			px += dx[d];
    			
    			if( py < 0 || px < 0 || px >= N || py >= N ) break;
    			
    			// 중간에 전선이 있다면 wire 초기화 
    			if( visit[py][px] ) {
    				wire = 0;
    				break;
    			}
    			
    			wire++;
    			visit[py][px] = true;
    		
    		}
    		
    		// 중간에 전선을 만나지 않았다면 전체 합 + wire, 프로세서 개수 +1
    		if(wire != 0) {
    			wireSum += wire;
        		processerCnt++;
    		}
    		
		} 
    	
    	if( max < processerCnt ) {
    		max = processerCnt;
    		min = wireSum;
    	}
    	else if( max == processerCnt ) {
    		min = Math.min(min, wireSum);
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