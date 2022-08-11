package add.add0811;

import java.io.*;
import java.util.*;

// A형의 가장 기본적인 코드
public class Solution_d4_1227_미로2_부울경_3반_이수경 {

	static int ans;
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/add0811/input_d4_1227.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력할 때 stringBuilder 쓰기
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
					
			//
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		
		br.close();
	}

}
