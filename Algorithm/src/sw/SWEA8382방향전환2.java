package sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA8382방향전환2 {

	static int T, x1, y1, x2, y2, ans;
	 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            
            // x1 y1 에서 x2 y2 로 이동
            
            int dy = Math.abs(y2 - y1);
            int dx = Math.abs(x2 - x1);
            
            // 큰 것을 dx에 둔다
            if( dx < dy ) {
            	int temp = dy;
            	dy = dx;
            	dx = temp;
            }
            
            // dx%2 가 아니라 dx/2인것 주의!
            int ans = (dx/2) * 4 + (dx%2);
            
            if(dy%2 == 1) {
            	if(dx%2 == 1) ans++;
            	else ans--;
            }
            
            System.out.println("#" + t + " " + ans);
        } // testcase
        
    }

}
