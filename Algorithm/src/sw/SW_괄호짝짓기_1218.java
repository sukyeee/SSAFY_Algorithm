package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW_괄호짝짓기_1218 {

	static int N;
	static char bracket;
	static Stack<Character> s = new Stack<Character>();
	static boolean flag;
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("SWEA1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			
			flag = false;
			int result = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			char inp[] = new char[N];

			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				bracket = line.charAt(i);

				if (bracket == '(' || bracket == '{' || bracket == '<' || bracket == '[') {
					s.push(bracket);
				} else {

					switch (bracket) {
					case ')':
						chk('(');
						break;

					case '}':
						chk('{');
						break;

					case '>':
						chk('<');
						break;

					case ']':
						chk('[');
						break;
					}

				}
				if (flag) {
					result = 0;
					break;
				}
			}

			System.out.println("#" + t + " " + result);
		} // testcase

	}
	
	static void chk(char bk) {
		if (s.peek() != bk)
			flag = true;
		s.pop();
	}

}
