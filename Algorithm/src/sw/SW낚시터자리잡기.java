package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW낚시터자리잡기 {

	static int T, N;
	static Item gate[] = new Item[4];
	static int tgt[] = new int[3];
	static boolean select[] = new boolean[3];
	static int seats[];
	static boolean visited_seat[];
	static int min;
	static int sum;
	static int ans;
	static int dx[] = { 0, -1, 1 }; // 해당 위치, 좌 우 
	static int fisherCnt;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/sw/SW낚시터자리잡기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			ans = Integer.MAX_VALUE;
			sum = 0;
			N = Integer.parseInt(br.readLine());
			seats = new int[N+1];
			visited_seat = new boolean[N+1];
			select = new boolean[4];
			for (int i = 1; i <= 3; i++) { // 게이트는 항상 3
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				gate[i] = new Item(a,b);
			}
			// 입력 완
			
			DFS(0, 0);
			
			System.out.println("#" + t + " " + min);
			
		} // testcase
	}
	static void DFS(int cnt, int sum ) {
		
		if( cnt == 3 ) {
			min = Math.min(min, sum);
			return;
		}
			
			
			
		
		//  비어있는 가장 가까운 게이트 찾기! 
		for (int i = 1; i <= 3; i++) {
			
			if(select[i]) continue; // 순열 
			
			select[i] = true;
			
			// 낚시꾼 배치 
			DFS( cnt + 1, sum + inFishers(i, gate[i].person, true)); // 게이트 한 개당 DFS 실행
			outFishers(i);
			
			if( gate[i].person % 2 == 0 ) { //  사람이 짝 수 일때 
				DFS( cnt + 1, sum + inFishers(i, gate[i].person, false));
				outFishers(i);
			}
			
			select[i] = false;
			
		}
		
		
		
		
		
	}
	static int inFishers(int idx, int fishers, boolean flag) {
		int distance = 0;
		fisherCnt = 0;
		int sum = 0;
		
		//모든 낚시꾼들을 다 배치할 때까지 
		while( fisherCnt < fishers ) {
			sum += flag ? left(idx, distance ) : right(idx, distance);
			
			if(fisherCnt == fishers)break;
			
			sum += flag ? right(idx, distance ) : left(idx, distance);
			distance++;
			
		}
		return sum; // 낚시꾼 모두 배치 후 늘어난 이동 거리 
	}
	static int left(int idx, int distance ) {
		int left = gate[idx].position - distance;
		
		if( left > 0 && seats[left] == 0 ) {
			seats[left] = idx;
			fisherCnt++;
			return distance + 1;
		}
		return 0;
	}
	static int right(int idx, int distance ) {
		int right = gate[idx].position + distance;
		
		if( right <= N && seats[right] == 0 ) {
			seats[right] = idx;
			fisherCnt++;
			return distance + 1;
		}
		
		return 0;
		
	}
	// 낚시꾼들 원래 상태로 되돌리기 
	static void outFishers(int idx) {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(seats[i] == idx) {
				seats[i] = 0;
				cnt++;
			}
			if(cnt == gate[idx].person) return;
		}
	}
	
	
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			// 게이트 번호 순서가 정해지면, 해당 게이트 넘버 순서대로 낚시꾼 배치 
			
//			for (int t : tgt) { // 게이트 순서대로!
//				search(0, gate[t].position, gate[t].person, gate[t].position);  // 누적 거리합, 배치 할 자리번호, 남은 사람 수, 게이트 위 
//				sum += min;		
//			}
//			ans = Math.min(ans, sum);
			
//			DFS(0, 0);
			return;
		}
	
		for (int i = 0; i < 3; i++) {
			if(select[i]) continue;
			
			tgt[tgtIdx] = i;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}
	
	
	static class Item {
		int position; // 게이트의 위치 
		int person; // 통과하는 사람의 수 
		public Item(int position, int person) {
			super();
			this.position = position;
			this.person = person;
		}
		
		
	}
	
	/*
	 
5
10
4 5
6 2
10 2
10
8 5
9 1
10 2
24
15 3
20 4
23 7
39
17 8
30 5
31 9
60
57 12
31 19
38 16

	 
	 
	 */

}
