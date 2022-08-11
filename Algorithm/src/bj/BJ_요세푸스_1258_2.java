package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// Circular Array Index
public class BJ_요세푸스_1258_2 {
	static int N, K;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=N;i++) {
			queue.offer(i);
		}
		
		// 변수들
		int alive = 1; // 1 부터 계속 증가, 살아 있는 번호에서만 증가
		
		
		sb.append("<");
		
		while(! queue.isEmpty()) {

			int num = queue.poll();
			
			// alive % K == 0
			if( alive % K == 0 ) { // K 번째 해당
				sb.append(num).append(", ");
			} else {
				queue.offer(num);
			}
			
			alive++;
		}
		
		// 뒷 부분 출력 정리
	sb.setLength(sb.length() - 2);
	sb.append(">");
	System.out.println(sb.toString());
	}

}
