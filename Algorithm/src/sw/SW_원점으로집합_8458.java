package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_원점으로집합_8458 {

static int T, N, max;
static int[] point; // 맨하탄 거리
public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    
    for (int t = 1; t <= T; t++) {
        N = Integer.parseInt(br.readLine());
        point = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        // 첫번째
        point[0] = Math.abs(x) + Math.abs(y);
        // max 도 첫 번째 값으로 시작
        max = point[0];
        
        // 전부다 짝수 또는 홀수 여부를 따진다.
        // max 값도 미리 계산
        boolean stop = false;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            point[i] = Math.abs(x) + Math.abs(y);
            max = Math.max(max, point[i]);
            
            if( point[i] % 2 != point[i-1] % 2) stop = true;
        }
        
        if( stop ) {
            System.out.println("#" + t + " -1" );
        }
        
        // 전부 다 짝수이거나 홀수 => 원점으로 모두 이동 가능
        int sum = 0;// 총 이동 거리의 합
        int cnt = 0;// 움직이는 횟수
        
        while(true) {
            
            if( sum == max || ( sum > max ) && ( sum - max ) % 2 == 0 ) break;
            sum += ++cnt;
        }
        
        System.out.println("#" + t + " " + cnt );
    }

}
}