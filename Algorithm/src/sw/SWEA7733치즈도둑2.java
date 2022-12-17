package sw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA7733치즈도둑2 {
	static int n;
	static int t;
	static int map[][];
	static int result;
	static int ypos[]= {0,0,1,-1};
	static int xpos[]= {1,-1,0,0};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		t=sc.nextInt();
		for (int tc = 1; tc <=t; tc++) {
			n=sc.nextInt();
			map=new int[n][n];
			result=1; //처음은 1덩이이다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j]=sc.nextInt();
				}
			}
			check();
			for (int i = 1; i <= 100; i++) {
				bfs(i);
				check();
			}
			System.out.printf("#%d %d\n",tc,result);
		}
	}
	private static void check() {
		int cnt=0;
		boolean[][] vis=new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]!=0 && vis[i][j]==false) {
					vis[i][j]=true;
					Queue<Integer[]> q=new LinkedList<Integer[]>();
					q.add(new Integer[] {i,j});
					while(q.size()!=0) {
						int y=q.peek()[0];
						int x=q.peek()[1];
						q.poll();
						for (int k = 0; k < 4; k++) {
							int yy=y+ypos[k];
							int xx=x+xpos[k];
							if(xx<0 || yy<0 || xx>=n || yy>=n)continue;
							if(vis[yy][xx])continue;
							if(map[yy][xx]==0)continue;
							vis[yy][xx]=true;
							q.add(new Integer[] {yy,xx});
						}
					}
					cnt+=1;
				}
			}
		}
		if(cnt>result) {
			result=cnt;
		}
	}
	private static void bfs(int num) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]==num) {
					map[i][j]=0;
				}
			}
		}
	}
}
