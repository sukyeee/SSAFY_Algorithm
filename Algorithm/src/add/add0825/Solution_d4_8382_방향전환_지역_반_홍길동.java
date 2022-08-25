package add.add0825;

import java.io.*;
import java.util.*;

// 2t+|x-t|+|y-t|
public class Solution_d4_8382_방향전환_지역_반_홍길동{
	public static void main(String args[]) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_8382.txt"));
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();

		for(int tc=1; tc<=T; tc++){
			int x1=sc.nextInt();
			int y1=sc.nextInt();
			int x2=sc.nextInt();
			int y2=sc.nextInt();
			
			int x=Math.abs(x1-x2);
			int y=Math.abs(y1-y2);
			
			System.out.print("#"+tc+" ");
			if((Math.abs(x+y))%2==0) System.out.println(Math.max(x, y)*2);
			else                     System.out.println(Math.max(x, y)*2-1);
		}
		sc.close();
	}
}
