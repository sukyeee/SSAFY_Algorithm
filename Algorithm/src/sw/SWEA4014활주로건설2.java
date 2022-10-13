package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA4014활주로건설2 {

	static int T, N, X;
	static int map[][];
	static int map2[][];
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			map2 = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = map2[j][i] =  Integer.parseInt(st.nextToken());
				}
			}
			
			process();
			
			
			System.out.println("#" + t + " " + ans);
			
		} // testcase 
	}
	
	static void process() {
		
		for (int i = 0; i < N; i++) { // 행 별로 하나씩 
			if(makeRoad(map[i])) ans++;
			if(makeRoad(map2[i])) ans++;
		}
		
	}
	
	static boolean makeRoad(int[] map) {
		
		int cur = map[0];
		int size = 0;
		for (int j = 0; j < N; j++) {
			
			if( cur == map[j] ) {
				size++;
			}
			else if ( cur == map[j] - 1 ) { // 한 칸 큰 것을 만날 경우 
				if( size < X ) return false; // 건설 실패 
				
				size = 1;
				cur = map[j]; // 현재 활주로 높이 갱신 
				
			}
			else if ( cur == map[j] + 1 ) { // 한 칸 작은 것을 만날 경우
				size = 0;
				int count = 0;
				cur = map[j];
				for (int k = 0; k < X; k++) {
					if(j+k >= N) return false; // X개를 채우기 전에 범위 벗어나면 실패 
					
					if( cur == map[j+k] ) count++;
				}
				
				if( count < X ) return false;
				
				j += X-1;
			}
			else { // 두 칸 이상 차이 나는 경우 
				return false;
			}
			
		}
		
		
		return true;
	}

}
/*
10
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2
6 4
3 2 2 2 1 2 
3 2 2 2 1 2 
3 3 3 3 2 2 
3 3 3 3 2 2 
3 2 2 2 2 2 
3 2 2 2 2 2 
7 2
1 1 1 1 2 1 1 
1 1 1 2 2 2 1 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
2 2 2 2 2 2 2 
8 3
2 2 2 3 3 4 2 2 
2 2 2 3 3 4 2 2 
2 2 2 2 2 2 2 2 
2 2 2 2 2 2 2 2 
2 2 2 2 1 1 2 2 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
8 4
1 1 1 1 1 1 1 1 
1 1 1 1 1 1 1 1 
1 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
1 1 1 1 1 1 1 2 
1 1 1 1 1 1 1 2 
9 4
4 4 3 3 3 3 2 2 2 
4 4 3 3 1 1 2 2 3 
3 3 2 2 1 1 1 1 2 
1 1 1 1 1 1 1 1 1 
1 1 1 1 1 1 1 1 1 
2 2 1 1 1 1 1 1 1 
2 2 1 1 1 1 1 1 1 
2 2 2 2 2 2 1 1 1 
3 3 3 3 2 2 2 2 1 
10 2
2 2 3 5 3 1 1 1 1 1 
2 2 3 5 3 1 1 1 1 1 
3 3 4 5 4 3 2 1 1 2 
3 3 4 5 4 3 2 1 1 2 
5 5 5 5 5 5 3 1 1 3 
4 4 4 5 5 5 4 3 3 3 
4 4 4 5 5 5 5 5 5 3 
4 4 4 4 4 5 5 5 5 3 
4 4 4 4 4 5 5 5 5 3 
5 5 4 4 4 5 5 5 5 4 
12 4
4 4 4 5 5 4 4 4 4 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 4 
4 4 4 5 5 4 4 4 5 5 5 4 
3 3 4 5 5 4 3 4 5 5 5 4 
3 3 4 5 5 4 3 4 5 5 5 4 
2 2 3 4 4 4 4 4 4 4 4 5 
2 2 3 4 4 4 4 4 4 4 4 5 
2 2 3 3 3 4 5 3 2 2 4 4 
3 3 3 4 4 4 5 4 3 3 4 4 
3 3 4 5 5 5 5 5 5 5 5 4 
3 3 4 5 5 5 5 5 5 5 5 4 
4 4 4 4 4 4 5 5 5 5 5 4 
15 3
5 4 4 3 3 3 2 2 2 3 4 4 4 4 4 
5 4 4 3 3 3 2 2 2 3 4 4 4 4 4 
5 5 5 5 4 4 2 2 3 4 4 4 4 4 5 
5 4 4 3 3 3 2 2 3 4 4 4 4 4 4 
5 3 3 1 2 2 3 3 3 4 4 4 4 4 4 
3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 
3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 
2 3 3 4 3 3 3 3 3 3 3 4 4 4 3 
2 3 3 4 3 3 3 3 3 3 3 4 4 4 3 
3 4 4 4 4 4 3 3 3 3 3 3 4 4 4 
5 5 5 4 4 4 4 4 3 3 3 3 4 4 5 
5 5 5 4 4 4 4 4 3 3 3 3 4 4 5 
5 5 5 5 4 4 4 4 3 3 2 2 3 3 4 
5 5 5 5 5 5 4 4 3 3 2 1 2 2 3 
5 5 5 5 5 5 4 4 3 3 2 1 2 2 3 
20 3
3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 
3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 
5 3 3 2 2 2 2 2 3 3 4 4 4 4 5 5 5 5 5 5 
4 3 3 1 1 1 1 1 2 3 4 4 4 5 5 5 5 5 5 5 
4 2 2 1 1 1 1 1 2 3 4 5 5 5 5 5 5 5 5 5 
4 3 3 2 2 2 2 1 2 3 4 5 5 5 5 5 5 5 5 5 
4 4 4 4 4 3 3 2 3 4 5 5 5 5 5 5 5 5 5 5 
4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 
4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 
4 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 
3 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 
3 3 3 3 3 4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 
3 3 3 3 4 4 4 4 5 5 5 5 5 5 5 5 5 5 5 5 
4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 
4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 4 4 4 4 4 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 3 3 4 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 
5 5 5 5 5 5 5 5 4 4 4 4 3 3 3 3 4 4 4 4 

*/