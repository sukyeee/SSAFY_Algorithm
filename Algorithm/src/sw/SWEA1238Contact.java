package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1238Contact {

	static int N; // 입력받는 데이터의 길이
	static int S; // 시작점
	static int from, to;
	static List<Integer> node[];
	static boolean visit[] = new boolean[101];
	static Deque<Integer> q = new ArrayDeque<>();
	static List<Integer> result = new ArrayList<>();
	static int max;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/sw/SWEA1238Contact.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// from, to 입력
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			node = new ArrayList[101]; // 1~100까지 들어오므로
			for (int i = 0; i < 101; i++) {
				node[i] = new ArrayList<>();
			}
			q.clear();
			result.clear();
			visit = new boolean[101];
			// 초기화 완

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				// 연결 하기
				node[from].add(to); // 유향 그래프
			}

			for (int i = 0; i < 101; i++)
				Collections.sort(node[i], (a, b) -> a - b);

			q.push(S); // S 부터 시작
			visit[S] = true;

			while (!q.isEmpty()) {

				int q_size = q.size(); // 2 -> 크기 4개

				for (int k = 0; k < q_size; k++) {
					int top = q.poll();

					for (int i = 0; i < node[top].size(); i++) {

						int v = node[top].get(i);

						if (visit[v]) {
							continue;
						}

						visit[v] = true;
						q.add(v);
						max = Math.max(max, v); // 한 depth 당 최댓값 찾기
					}

				}
				if (max == 0) break; // 마지막에 들어오는 0은 항상 넣지 않음
				result.add(max); 
				max = 0;
			}

			System.out.println("#" + t + " " + result.get(result.size() - 1));
		} // testcase

	}
}
