package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1247최적경로 {

	static int N;
	static int ans;
//	static List<Dist> customer = new ArrayList<>();
	static Dist customer[];
	static int home_x, home_y;
	static int office_x, office_y;
	static int min;
	static boolean isVisited[];
	static Dist tgt[];
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/sw/SWEA1247최적경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		
		for(int t=1;t<=10;t++) {
			
			min = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			customer = new Dist[N];
			tgt = new Dist[N];
			isVisited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			
			office_x = Integer.parseInt(st.nextToken());
			office_y = Integer.parseInt(st.nextToken());
			home_x = Integer.parseInt(st.nextToken());
			home_y = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<N;i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customer[i] = new Dist(x,y);
			}
			// 입력 완
			
			// 이동거리 ->  |x1-x2| + |y1-y2|
		
			perm(0); // 회사에서 출발
			
			
			
			System.out.println("#" + t + " " + min);
		} //testcase
		
	}
	
	
	static void perm(int tgtIdx) {

		
		// 모든 고객을 방문했을 때
		if( tgtIdx == N ) {
			//거리에 대한 계산 : 회사 - 고객 - 집
			int d = Math.abs(office_x - tgt[0].x) + Math.abs(office_y - tgt[0].y); 
			for(int i=1;i<tgt.length;i++) {
				d += Math.abs(tgt[i-1].x - tgt[i].x) + Math.abs(tgt[i-1].y - tgt[i].y); 
			}
			d += Math.abs( home_x - tgt[N-1].x) + Math.abs( home_y - tgt[N-1].y); 
			
			
			min = Math.min(min, d); // 최솟값 갱신
			return;
		}
		
		
		for(int i=0;i<N;i++) {
			if(isVisited[i]) continue;
			
			tgt[tgtIdx] = customer[i];
			isVisited[i] = true;
			perm( tgtIdx + 1 );
			isVisited[i] = false;
		}
		
		
		
	}
	
	static class Dist{
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

}
