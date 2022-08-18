package live0818;

import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
/**
 * @author THKim
 */
public class NQueenTest2 {

	static int N, cols[], ans;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		cols = new int[N+1]; // 1행부터 사용
		ans = 0;
		
		setQueen(1);
		System.out.println(ans);
	}
	
	public static void setQueen(int rowNo) { // 하나의 퀸만 가능한 모든 곳에 놓아보기
		
		if(rowNo>N) { // 퀸을 다 놓았으면 ( 모든 퀸의 배치에 성공한 상황 ) 
			ans++;
			return;
		}
		for (int i = 1; i <= N; i++) {
			cols[rowNo] = i;
			if(isAvailable(rowNo)) setQueen(rowNo+1);
		}
	}

	private static boolean isAvailable(int rowNo) {
		
		for (int j =1; j < rowNo; j++) {
			if(cols[j] == cols[rowNo] || 
				rowNo - j == Math.abs(cols[rowNo]-cols[j]) ) return false;
		}
		return true;
	}
	
	
	
	
	
	

}





