package sw;

import java.util.Scanner;

public class SWEA7733치즈도둑 {
	  static int[][] cheese;
	    static boolean[][] visited;
	    static int[] dx={-1, 1, 0, 0};
	    static int[] dy={0, 0, -1, 1};
	     
	    public static void main(String args[]) throws Exception
	    {
	        Scanner sc = new Scanner(System.in);
	        int T;
	        T=sc.nextInt();
	 
	        for(int test_case = 1; test_case <= T; test_case++)
	        {
	            int N = sc.nextInt();
	            int max = Integer.MIN_VALUE;
	            int cnt;
	            cheese = new int[N][N];
	             
	            for(int i=0; i<N; i++) {
	                for(int j=0; j<N; j++) {
	                    cheese[i][j]=sc.nextInt();
	                }
	            }
	             
	            for(int day=0; day<=100; day++) {
	                cnt=0;
	                visited = new boolean[N][N];
	                eat(cheese, day);
	                 
	                for(int i=0; i<N; i++) {
	                    for(int j=0; j<N; j++) {
	                        if(visited[i][j]==false && cheese[i][j]!=0) {
	                            cnt++;
	                            count(i, j);
	                        }
	                    }
	                }
	                 
	                if(max<cnt)
	                    max=cnt;
	            }
	            System.out.println("#"+test_case+" "+max);
	        }
	    }
	     
	    public static void eat(int[][] cheese, int day) {
	        for(int i=0; i<cheese.length; i++) {
	            for(int j=0; j<cheese.length; j++) {
	                if(cheese[i][j]==day) {
	                    cheese[i][j]=0;
	                    visited[i][j]=true;
	                }
	            }
	        }
	    }
	     
	    public static void count(int X, int Y) {
	        int originX=X;
	        int originY=Y;
	        int x=0;
	        int y=0;
	         
	        visited[originX][originY]=true;
	         
	        for(int i=0; i<4; i++) {
	            x=originX+dx[i];
	            y=originY+dy[i];
	             
	            if(x>=0 && x<cheese.length && y<cheese.length && y>=0 && !visited[x][y] && cheese[x][y]!=0)
	                count(x, y);
	        }
	    }
}
