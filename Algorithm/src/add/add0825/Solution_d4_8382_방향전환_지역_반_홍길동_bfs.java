package add.add0825;

import java.io.*;
import java.util.*;

public class Solution_d4_8382_방향전환_지역_반_홍길동_bfs{
	static int[] di={-1,0,1,0};//상0좌1하2우3
	static int[] dj={0,-1,0,1};
	
	public static void main(String args[]) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_8382.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());

		for(int tc=1; tc<=T; tc++){
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			int x1=Integer.parseInt(st.nextToken())+100;
			int y1=Integer.parseInt(st.nextToken())+100;
			int x2=Integer.parseInt(st.nextToken())+100;
			int y2=Integer.parseInt(st.nextToken())+100;
			
			Queue<int[]> q=new ArrayDeque<>();
			boolean[][][] v=new boolean[201][201][2];//가로0,세로1
			q.offer(new int[]{x1,y1,0,0});//i,j,dir,cnt
			v[x1][y1][0]=true;//가로0
			q.offer(new int[]{x1,y1,1,0});
			v[x1][y1][1]=true;//세로1
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int i=p[0], j=p[1], dir=p[2], cnt=p[3];
				if(i==x2 && j==y2){
					sb.append("#").append(tc).append(" ").append(cnt).append("\n");
					break;
				}				
				for(int d=dir; d<4; d+=2){//dir==0 가로0, dir==1 세로1
					int ni=i+di[d];
					int nj=j+dj[d];
					if(0<=ni&&ni<=200 && 0<=nj&&nj<=200 && !v[ni][nj][dir==0? 1:0]){
						v[ni][nj][dir==0? 1:0]=true;
						q.offer(new int[]{ni,nj,dir==0? 1:0,cnt+1});
					}
				}
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}