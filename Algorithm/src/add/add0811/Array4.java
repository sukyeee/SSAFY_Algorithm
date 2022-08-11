package add.add0811;

// 아래 두 줄은 기본으로 다 설정해주기
import java.io.*;
import java.util.*;

// 사방 탐색
public class Array4 {

	public static void main(String[] args) throws Exception {
		int[][] a =  {{1,2,3},
					{4,5,6},
					{7,8,9}};
		
		
		// 8방 탐색 : 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상 : 23698741
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 5) {
					System.out.println("a["+i+"]["+j+"]="+a[i-1][j+0]); // 상

					System.out.println("a["+i+"]["+j+"]="+a[i-1][j+1]); // 우상 3
					System.out.println("a["+i+"]["+j+"]="+a[i+0][j+1]); // 우

					System.out.println("a["+i+"]["+j+"]="+a[i+1][j+1]); // 우하 9
					System.out.println("a["+i+"]["+j+"]="+a[i+1][j+0]); // 하

					System.out.println("a["+i+"]["+j+"]="+a[i+1][j-1]); // 좌하 7
					System.out.println("a["+i+"]["+j+"]="+a[i][j-1]); // 좌

					System.out.println("a["+i+"]["+j+"]="+a[i-1][j-1]);   // 좌상 1
					
					int[] di = {-1,-1,0,1,1,1,0,-1};
					int[] dj = {0,1,1,1,0,-1,-1,-1};
					for (int d = 0; d < 8; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 System.out.println(a[ni][nj]);
					}
				}
			}
		}
		
		
		
		
		
		// 4방 탐색x : 우상 우하 좌하 좌상 3 9 7 1
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 5) {
					System.out.println("a["+i+"]["+j+"]="+a[i-1][j+1]); // 우상 3
					System.out.println("a["+i+"]["+j+"]="+a[i+1][j+1]); // 우하 9
					System.out.println("a["+i+"]["+j+"]="+a[i+1][j-1]); // 좌하 7
					System.out.println("a["+i+"]["+j+"]="+a[i-1][j-1]);   // 좌상 1
					
					int[] di = {-1, 1, 1,  -1};
					int[] dj = {1,  1, -1, -1};
					for (int d = 0; d < 4; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 System.out.println(a[ni][nj]);
					}
				}
			}
		}
		
		// 4방 탐색 : 상우하좌 2684
				for (int i = 0; i < a.length; i++) {
					for (int j = 0; j < a[i].length; j++) {
						if(a[i][j] == 5) {
							System.out.println("a["+i+"]["+j+"]="+a[i-1][j+0]); // 상
							System.out.println("a["+i+"]["+j+"]="+a[i+0][j+1]); // 우
							System.out.println("a["+i+"]["+j+"]="+a[i+1][j+0]); // 하
							System.out.println("a["+i+"]["+j+"]="+a[i][j-1]); // 좌
							
							int[] di = {-1, 0, 1,  0};
							int[] dj = {0,  1, 0, -1};
							for (int d = 0; d < 4; d++) {
								 int ni = i + di[d];
								 int nj = j + dj[d];
								 System.out.println(a[ni][nj]);
							}
						}
					}
				}
		
		
		// 2방 탐색 : 상하  2 8
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 5) {
					System.out.println("a["+i+"]["+j+"]="+a[i-1][j+0]); // 상 2
					System.out.println("a["+i+"]["+j+"]="+a[i+1][j+0]); // 하 8
					
					int[] di = {-1, 1};
					int[] dj = {0,  0};
					for (int d = 0; d < 2; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 System.out.println(a[ni][nj]);
					}
				}
			}
		}
		
		// 2방 탐색 : 좌우 4 6
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 5) {
					System.out.println("a["+i+"]["+j+"]="+a[i+0][j+1]); // 우 6
					System.out.println("a["+i+"]["+j+"]="+a[i+0][j-1]); // 좌 4
					
					int[] di = {0, 0};
					int[] dj = {1, -1};
					for (int d = 0; d < 2; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 System.out.println(a[ni][nj]);
					}
				}
			}
		}
		
		
		// 1방 탐색 : 5
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] == 5) {
					System.out.println("a["+i+"]["+j+"]="+a[i+0][j+0]);
					
					int[] di = {0};
					int[] dj = {0};
					for (int d = 0; d < 1; d++) {
						 int ni = i + di[d];
						 int nj = j + dj[d];
						 System.out.println(a[ni][nj]);
					}
				}
			}
		}
		
		
		
		//for(int[] b:a) System.out.println(Arrays.toString(b));
		
	}

}
