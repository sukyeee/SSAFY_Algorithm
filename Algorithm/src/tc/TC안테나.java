package tc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC안테나 {

	static int N;
	static int house[];
	static int Min;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		house = new int[N];
		for(int i=0;i<N;i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		int idx = -1;
		int Min = Integer.MAX_VALUE;
		// 집의 위치 중 하나를 안테나 위치로 지정
		for(int i=0;i<N;i++) {
			sum = 0;
			for(int j=0;j<N;j++) {
				sum += Math.abs(house[i] - house[j]);
			}
			if( sum < Min ) {
				Min = sum;
				idx = house[i];
			}
		}
		
	
		System.out.println(idx);
		
	}

}
