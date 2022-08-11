package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ2493탑 {

	static int N;
	static int inp;
	static Deque<int[]> stack = new ArrayDeque<>(); // [0]: 인덱스 , [1]: 값

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// System.setIn(new FileInputStream("BJ2309.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			inp = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty()) {

				// 스택보다 내가 더 크다 ? 스택에 있는 놈 나와!
				if(stack.peek()[1] < inp) {
					stack.pop();
				}
				else {
					System.out.print(stack.peek()[0] +" ");
					break;
				}
			}
			// 스택이 비어있으면 inp 가장 크다는 의미 
			if(stack.isEmpty()) {
				System.out.print("0 ");
			}
			
			stack.push(new int[] { i, inp }); // 일단 스택에 쌓아놓고 작은것들 while문에서 처리 

		}

	}

}

/*
5
6 9 5 7 4
 */