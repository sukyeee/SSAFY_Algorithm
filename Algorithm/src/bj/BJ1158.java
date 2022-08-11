package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1158 {

	static int N, K;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<int[]> list = new ArrayList<>();
		List<int[]> result = new ArrayList<>();
		
		for(int i=1;i<=N;i++)list.add(new int[] {i,i}); //숫자, 인덱스
		sb.append("<");
		int i = K-1;
		while(!list.isEmpty()) {
			
				sb.append(list.get(i)[0]);
				list.remove(i);
				if(list.isEmpty()) break;
				sb.append(", ");
				i = (i+K-1)%list.size();
				
		}
		
		sb.append(">");
	
		System.out.println(sb);
	}

}
