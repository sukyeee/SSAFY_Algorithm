package jo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JO1828냉장고 {

	static int N;
	static Item temp[];
	public static void main(String[] args) throws Exception{

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		N = Integer.parseInt(br.readLine());
		temp = new Item[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			temp[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// 입력 완
		
		Arrays.sort(temp); // 최고온도 기준 오름차순 정렬
		
		int cnt = 1;
		int high = temp[0].high; // 처음 최고 온도
		
		for(int i=0;i<temp.length;i++) {
			if( high < temp[i].low ) { // 더 큰게 있다면 ? 갱신
				cnt++;
				high = temp[i].high;
				
			}
				
		}
		
		
		System.out.println(cnt);
		
		
	}

	static class Item implements Comparable<Item>{
		int low;
		int high;
		public Item(int low, int high) {
			super();
			this.low = low;
			this.high = high;
		}
		@Override
		public int compareTo(Item o) {
			return this.high - o.high;
		}
	
		
	}
}
