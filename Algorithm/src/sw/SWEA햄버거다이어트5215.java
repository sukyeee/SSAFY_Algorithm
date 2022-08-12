package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA햄버거다이어트5215 {
	
	static int T, N, L;
	static int taste[];
	static int kal[];
	static int tgt_taste[];
	static int tgt_kal[];
	static int taste_sum, kal_sum;
	static int Max;
	static boolean[] select;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Max = 0;
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			taste = new int[N];
			kal = new int[N];
			tgt_taste = new int[N];
			tgt_kal = new int[N];
			select = new boolean[N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken()); // 맛에 대한 점수
				kal[i] = Integer.parseInt(st.nextToken()); // 칼로리
			}
			
			// 제한 칼로리 이내 조합, 가장 맛에 대한 점수가 높은 햄버거의 점수는 ? -> 부분집합
			subset(0);
			
			System.out.println("#" + t + " " + Max);
		} //testacase
		

		
	}

	
	static void subset(int srcIdx) {
		
		if( srcIdx == N) {	
			chk();
			return;
		}
		
		tgt_taste[srcIdx] = taste[srcIdx];
		tgt_kal[srcIdx] = kal[srcIdx];
		select[srcIdx] = true;
		subset(srcIdx + 1);
		
		tgt_taste[srcIdx] = 0;
		tgt_kal[srcIdx] = 0;
		select[srcIdx] = false;
		subset(srcIdx + 1);
		
	}
	
	static void chk() {
		taste_sum = 0; // 맛에 대한 점수 조합의 합!
		kal_sum = 0; // 칼로리에 대한 조합의 합
		
		for(int i=0;i<N;i++) {
			taste_sum += tgt_taste[i]; 
			kal_sum += tgt_kal[i];
		}
		
		if(kal_sum <= L) { // 제한 칼로리보다 작은 것만 Max 갱신
			Max = Math.max(Max, taste_sum); 
		}
		
	}
}
