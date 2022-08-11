package add.add0811;

public class Array5 {

	static int[] di = {-1, 0, 1,  0};
	static int[] dj = {0,  1, 0, -1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a = {{1,2,3},
					{4,5,6},
					{7,8,9}};
		int N = a.length;
		int M = a[0].length;
		
		// 4방 탐색 : 상우하좌 2684
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(a[i][j] == 5) {
					for (int d = 0; d < 4; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 if(0 <= ni && ni < N && 0 <= nj && nj < M) {
							 System.out.println(a[ni][nj]);	 
						 }
						 
					}
				}
			}
		}
	
		
		
		
		
		
		
	}

}
