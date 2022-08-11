package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2563색종이 {

	static int N, x, y;
	static int[][] paper;
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("BJ2309.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int sum = 0;
		paper = new int[101][101];
		
		for(int t=0;t<N;t++) {
			
			st = new StringTokenizer(br.readLine());
			// 입력 x y
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			// 15 7 부터 100 만큼.  3 7 부터 100만큼. 5 2 부터 100만큼.
			// 15~25 , 7~17     / 3~13 , 7~17,   /  5~15, 2~12
			
			for(int i=x+1;i<=x+10;i++) {
				for(int j=y+1;j<=y+10;j++) {
					paper[i][j] = 1;
				}
			}
		}
		
		for(int i=1;i<=100;i++) {
			for(int j=1;j<=100;j++) {
				if(paper[i][j] == 1)sum++;  
				
			}
		}
		
		System.out.println(sum);
		
	}

}


/*

3
3 7
15 7
5 2

*/