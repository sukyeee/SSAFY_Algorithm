package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1032명령프롬프트 {
	static int N;
	static String inp[];
	static String result[];
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inp = new String[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			inp[i] = st.nextToken();
		}
		
		String temp = inp[0]; // 비교 대상
		boolean flag[] = new boolean[temp.length()];
		for (int i = 1; i < N; i++) { // 한 줄 
			for (int j = 0; j < inp[i].length(); j++) {
				// 한 글자씩 비교 
				if( temp.charAt(j) != inp[i].charAt(j) ) {
					// 하나라도 다르면 => ?
					flag[j] = true;
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < temp.length(); i++) {
			if(flag[i]) sb.append("?");
			else sb.append(temp.charAt(i));
		}
		System.out.println(sb);
	}
}
