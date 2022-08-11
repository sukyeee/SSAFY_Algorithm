package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BufferedReaderScannerTest {

	public static void main(String[] args) throws IOException {

		
		
		{

//			System.setIn(new FileInputStream("inputTC.txt"));
//			Scanner sc = new Scanner(System.in);
//			
//			long start = System.nanoTime();
//			int TC = sc.nextInt();
//			
//			for(int tc = 1; tc <= TC; tc++) {
//				int N = sc.nextInt();
//				
//				int sum = 0;
//				for(int i=0; i<N; i++) {
//					for(int j=0;j<N;j++) {
//						sum += sc.nextInt();
//					}
//				}
//				System.out.println("#" + tc + " " +sum);
//				
//			}
//			long end = System.nanoTime();
//			System.out.println((end-start/1_000_000_000.0 + "s"));
			
		}
		
		
		
		System.setIn(new FileInputStream("inputTC.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		long start = System.nanoTime();
		int TC = Integer.parseInt(in.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(in.readLine());
			
			int sum = 0;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j=0;j<N;j++) {
					sum += Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + tc + " " +sum);
			
		}
		long end = System.nanoTime();
		System.out.println((end-start/1_000_000_000.0 + "s"));
		
		
	}

}
