package sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA8382방향전환 {

	static int T, x1, y1, x2, y2, ans;
	 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
 
        for (int index = 1; index <= T; index++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            ans = 0;
 
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
 
            if( dy > dx ) {
            	int temp = dx;
            	dx = dy;
            	dy = temp;
            }
            System.out.println(dx);
            
            ans = (dx/2)*4 + (dx%2);
            
            if(dy%2 == 1) {
            	if(dx%2==1) ans++;
            	else ans--;
            }
            
 
            bw.write("#" + index + " " + ans + "\n");
        }
        br.close();
        bw.close();
   
    }

}
