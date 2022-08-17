package jo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JO_냉장고_1828 {

	static int N, max, count;
	static int[][] input; // input[i][0] : i 재료의 최저온도, input[i][1] : i 재료의 최고온도
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken()); // 최저
			input[i][1] = Integer.parseInt(st.nextToken()); // 최고
		}
		
		// 회의실과 같은 문제로 Greedy 하게 해결 => 최고 온도로 asc 정렬
		Arrays.sort(input, (o1, o2) -> o1[1] - o2[1]);
		
		// max, count 초기화
		max = Integer.MIN_VALUE;
		count = 0;
		
		// 순회하면서 최저온도가 현재 max (최고온도 의미) 보다 더 높으면 (같이 보관 X) 냉장고 추가로 필요
		for (int i = 0; i < N; i++) {
			if( input[i][0] > max ) { // 맨 첫 약품은 자동으로 1개 냉장고가 추가
				count++;
				max = input[i][1]; // 새로운 냉장고에서 보관할 가장 높은 온도로 저장
			}
		}
	
		System.out.println(count);
	}
}
