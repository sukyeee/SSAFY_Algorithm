import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algo1_부울경_3반_이수경 {

	static int T, X;
	static int ans;
	static int stick[] = new int[7];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 0  1  2  3  4  5  6
		// 64 32 16 8  4  2  1
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			X = Integer.parseInt(br.readLine());
			stick = new int[7];
			
			while(X > 0) {
				if( X >= 64 ) {
					stick[0]++;
					X -= 64;
				}
				else if( X >= 32 ) {
					stick[1]++;
					X -= 32;
				}
				else if( X >= 16 ) {
					stick[2]++;
					X -= 16;
				}
				else if( X >= 8 ) {
					stick[3]++;
					X -= 8;
				}
				else if( X >= 4 ) {
					stick[4]++;
					X -= 4;
				}
				else if( X >= 2 ) {
					stick[5]++;
					X -= 2;
				}
				else if( X >= 1 ) {
					stick[6]++;
					X -= 1;
				}
			}
			
			
			
			for (int i = 0; i < stick.length; i++) {
				ans += stick[i];
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
	}

}
