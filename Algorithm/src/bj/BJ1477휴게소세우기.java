package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1477휴게소세우기 {

	static int N, M, L;
	static List<Integer> inp = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 이분 탐색 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		inp.add(0);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			inp.add(Integer.parseInt(st.nextToken()));
		}
		inp.add(L);
		// 비교 해야 할 것 : 0 ~ 휴게소, 휴게소 ~ 휴게소, 휴게소 ~ L
		
		Collections.sort(inp);
		
		int left = 0; // 인덱스 
		int right = L;
		
		for (int t = 0; t < M; t++) { // 휴게소 개수 만큼 반복 
		
			int mid = 0;
			int max = 0;
			for (int i = 0; i < inp.size()-1; i++) { // 전체 고속도로 중에서 가장 차이가 큰 구간 찾기 
				if(inp.get(i+1) - inp.get(i) > max ) {
					max = inp.get(i+1) - inp.get(i);

					left = i;
					right = i+1;
					mid = inp.get(left) + (inp.get(i+1) - inp.get(i)) / 2 ;
				}
				
			} // for: i
			inp.add(right, mid);
			
			
		} // for t
		
		
		int ans = 0;
		// 전체 고속도로 중에서 가장 차이가 큰 구간 찾기
		for (int i = 0; i < inp.size()-1; i++) {  
			if(inp.get(i+1) - inp.get(i) > ans ) {
				ans = inp.get(i+1) - inp.get(i);
			}
			
		}
		
		System.out.println(ans);		
	}


}
