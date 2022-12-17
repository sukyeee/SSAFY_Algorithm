package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1824혁진이의프로그램검증 {
	
	   static int R, C;
	    static boolean visited[][][][];
	    static char oper[][];
	    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}; // 동,남,서,북
	    static int res; // 1: 성공
	    static class Info {
	        int x, y, dir, mem;
	 
	        public Info(int x, int y, int dir, int mem) {
	            super();
	            this.x = x;
	            this.y = y;
	            this.dir = dir;
	            this.mem = mem;
	        }
	        
	        
public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            oper = new char[R][C]; 
            res = -1;
            
            for (int i = 0; i < R; i++) {
                oper[i] = br.readLine().toCharArray();
                
                boolean isAno = false;
                for (int j = 0; j < C; j++) {
                    if(res != 0 && oper[i][j] == '@') isAno = true;
                }
                
                if(isAno) res = 0;
            }
            
            visited = new boolean[4][16][R][C];
            // @ 가 있으면 시작
            if(res == 0) process(new Info(0, 0, 0, 0));    
            
            System.out.println("#" + tc + " " + (res == 1 ? "YES" : "NO"));
        }
        
    }
 
    private static void process(Info info) {
        
        if(res != 0) return;
        
        // 이미 방문한 곳이면 return
        if(visited[info.dir][info.mem][info.x][info.y]) return;
        
        Info newInfo = new Info(info.x, info.y, info.dir, info.mem);
        
        char now = oper[newInfo.x][newInfo.y];
        visited[newInfo.dir][newInfo.mem][newInfo.x][newInfo.y] = true;
        
//        0~9    메모리에 문자가 나타내는 값을 저장한다.
        if(now >= '0' && now <= '9') {
            newInfo.mem = now - '0';
        } 
//        < 이동 방향을 왼쪽으로 바꾼다.
        else if(now == '<') {
            newInfo.dir = 2;
        }
//        >    이동 방향을 오른쪽으로 바꾼다.
        else if(now == '>') {
            newInfo.dir = 0;
        }
//        ^    이동 방향을 위쪽으로 바꾼다.
        else if(now == '^') {
            newInfo.dir = 3;
        }
//        v    이동 방향을 아래쪽으로 바꾼다.
        else if(now == 'v') {
            newInfo.dir = 1;
        }
//        _    메모리에 0이 저장되어 있으면 이동 방향을 오른쪽으로 바꾸고, 아니면 왼쪽으로 바꾼다.
        else if(now == '_') {
            newInfo.dir = (newInfo.mem == 0) ? 0 : 2;
        }
//        |    메모리에 0이 저장되어 있으면 이동 방향을 아래쪽으로 바꾸고, 아니면 위쪽으로 바꾼다.
        else if(now == '|') {
            newInfo.dir = (newInfo.mem == 0) ? 1 : 3;
        }
//        ?    이동 방향을 상하좌우 중 하나로 무작위로 바꾼다. 방향이 바뀔 확률은 네 방향 동일하다.
        else if(now == '?') {
            for (int d = 0; d < 4; d++) {
                int nextDir = (newInfo.dir + d) % 4;
                
                int nextX = newInfo.x + dx[nextDir];
                int nextY = newInfo.y + dy[nextDir];
                // 2차원 격자의 바깥으로 이동하는 방향이면, 반대편에 있는 위치로 이동
                if(nextX < 0) nextX = R - 1;
                else if(nextX >= R) nextX = 0;
                else if(nextY < 0) nextY = C - 1;
                else if(nextY >= C) nextY= 0;
                
                process(new Info(nextX, nextY, nextDir, newInfo.mem));
            }
            
            return;
        }
//        .    아무 것도 하지 않는다.
//        @    프로그램의 실행을 정지한다.
        else if(now == '@') {
            res = 1;
        }
//        +    메모리에 저장된 값에 1을 더한다. 만약 더하기 전 값이 15이라면 0으로 바꾼다.
        else if(now == '+') {
            newInfo.mem = (newInfo.mem == 15) ? 0 : newInfo.mem + 1;
        }
//        -    메모리에 저장된 값에 1을 뺀다. 만약 빼기 전 값이 0이라면 15로 바꾼다.
        else if(now == '-') {
            newInfo.mem = (newInfo.mem == 0) ? 15 : newInfo.mem - 1;
        }
 
        // 이동
        newInfo.x += dx[newInfo.dir];
        newInfo.y += dy[newInfo.dir];
        // 2차원 격자의 바깥으로 이동하는 방향이면, 반대편에 있는 위치로 이동
        if(newInfo.x < 0) newInfo.x = R - 1;
        else if(newInfo.x >= R) newInfo.x = 0;
        else if(newInfo.y < 0) newInfo.y = C - 1;
        else if(newInfo.y >= C) newInfo.y = 0;
        
        process(newInfo);
    }
}

}