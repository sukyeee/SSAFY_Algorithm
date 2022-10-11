package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014활주로건설 {

	static int T, N, X;
	static int map[][];
	static int map2[][];
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken()); // 경사로 가로 길이 
			map = new int[N][N];
			map2 = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
				}
			} // 입력 완 
			
			// 행과 열 둘다 고려 해야함 ( 한 줄씩 )
			
			// 행 
			
			System.out.println("#" + t + " " + process());

			
			

		} // testcase 

	}
	
	static int process() {
		
		int ans = 0;
		for (int i = 0; i < map.length ; i++) {
			if(makeRoad(map[i])) ans++;
			if(makeRoad(map2[i])) ans++;
		}
		
		return ans;
	}
	
	static boolean makeRoad(int[] map) {
		 
		int cur = map[0]; // 현재 활주로 높이 
		int size = 1;
		for (int j = 1; j < map.length; j++) {
			
			// 3 3 3 2 1 1 한줄 
			if( cur == map[j] ) { // 현재 보는 칸과 높이가 같을 때 
				size++;
				
			}
			else if ( cur == map[j] - 1 ) { // 현재 칸보다 더 큰 칸을 만났을 때 
				
				if( size < X ) return false; // 활주로 건설 실패 
				
				size = 1;
				cur++;
				
			}
			else if ( cur == map[j] + 1 ) {  // 현재 칸보다 더 작은 칸을 만났을 때
				
				size = 0;
				int count = 0;
				cur--;
				for (int k = 0; k < X; k++) {
					if( j+k >= N ) return false;
					
					if(cur == map[j+k] ) count++;
				}
				
				if( count != X ) return false; 
				
				j += X-1;
			}
			else { // 높이가 2 이상일 때 
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