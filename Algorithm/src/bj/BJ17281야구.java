package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17281야구 {

	static int N;
	static int inning[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		inning = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<9;j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 한 이닝에 3아웃 발생하면 이닝 종
			// 타순은 이닝이 변경되어도 순서를 유지 
			// 1번 선수는 4번 타자
			
			//첫째 줄 : 이닝 수
			//둘째 줄 : 각 선수가 각 이닝에서 얻는 결과가 1번 이부터 N번 이닝까지 순서대로 주어짐 
			
		}
		
	}

}


/*

2
4 0 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
AZ
*/