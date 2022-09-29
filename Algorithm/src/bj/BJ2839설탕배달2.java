package bj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ2839설탕배달2 {

	static int N;
	static int sugar[] = new int[5001];
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	

		N = Integer.parseInt(br.readLine());
		
		Arrays.fill(sugar, 5000);
		// 설탕은 3kg, 5kg 봉지 있다 
		// 최대한 적게 사용 ! 18 kg => 5 5 5 3 

		// 봉지는 무한대, 1차원 배열으로 해결
		
		sugar[3] = 1;
		sugar[5] = 1;
		
		for (int i = 6; i <= N; i++) {
			
			sugar[i] = Math.min(sugar[i-5] + 1, sugar[i-3] + 1);
			
		}
		
		System.out.println(sugar[N] >= 5000 ? -1 : sugar[N] );
		
	
	}

}
