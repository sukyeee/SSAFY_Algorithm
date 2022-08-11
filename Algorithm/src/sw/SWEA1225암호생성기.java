package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1225암호생성기 {

	static int T;
	static Queue<Integer> q = new ArrayDeque<>();
	static int front = 999;
	static boolean flag;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("SWEA1225.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			flag = false;
			T = Integer.parseInt(st.nextToken());
			q.clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				int number = Integer.parseInt(st.nextToken());
				q.add(number);
			}

			while (true) {

				for (int i = 1; i <= 5; i++) {
					int front = q.peek() - i;
					if (front <= 0) {
						q.remove();
						q.add(0);
						flag = true;
						break;
					}
					q.remove();
					q.add(front);

				}
				if (flag)
					break;
			}
			
			System.out.print("#" + t + " ");
			Iterator iter = q.iterator();

			while (iter.hasNext())
				System.out.print(iter.next() + " ");
			System.out.println();
			
		}

	} // testcase

}
