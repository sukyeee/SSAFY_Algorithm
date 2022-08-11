package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 규영이와인영이의카드게임 {
	
	static int N = 9;
	static List<Integer> gy = new ArrayList<>();
	static List<Integer> iy = new ArrayList<>();
	static int win, lose;
	static int T;
	static int tgtIdx;
	static int tgt[] = new int[9];
	static boolean isVisited[] = new boolean[9];
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("BJ6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 초기화
			gy.clear();
			iy.clear();
			win = lose = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1;i<=9;i++) {
				gy.add(Integer.parseInt(st.nextToken()));
			}
			for(int i=1;i<=18;i++) {
				if( !gy.contains(i) ) {
					iy.add(i);
				}
			}
			// 입력 완 
			
			// 인영이뽑은 카드에 대해 순열 확인 
			perm(0);
			
			System.out.println("#" + t + " " + win + " " + lose);
			
		}
		
		
	}
	
	static void perm(int tgtIdx) {
		
		if(tgtIdx == 9) {
			chk();
			return;
		}
		
	
			for(int i=0;i<9;i++) {
				if(isVisited[i]) continue;
				tgt[tgtIdx] = iy.get(i); //인영이 카드 
				isVisited[i] = true;
			
				perm( tgtIdx + 1 );
				
				isVisited[i] = false;
			}
		
		
		
	}
	static void chk() {
		int gy_sum = 0;
		int iy_sum = 0;
		
		for(int i=0;i<9;i++) {
			if( gy.get(i) > tgt[i]) {
				gy_sum += gy.get(i) + tgt[i];
			}
			else if (gy.get(i) < tgt[i]) {
				iy_sum += gy.get(i) + tgt[i];
			}
		}
		
		if(gy_sum > iy_sum) win++;
		else if( gy_sum < iy_sum ) lose++;
		
	}

}
