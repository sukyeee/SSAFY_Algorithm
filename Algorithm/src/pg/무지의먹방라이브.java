package pg;

import java.util.Arrays;

public class 무지의먹방라이브 {
	public static void main(String[] args) {

		int result = solution(5);

	}

	// 먹어야 하는 시간과, 음식 순서를 같이 묶어서 정렬
	static int solution(long k) {
		int answer = -1;

        int food_times[] = {3,1,2};
//		int food_times[] = { 3, 5, 1, 6, 5, 3 };
		int food_len = food_times.length;
//		k = 20;
		k = 5;
//		Food[] food = new Food[7];
		Food[] food = new Food[4];
		
		food[0] = new Food();
		for (int j = 1; j <= food_len; j++) {
			food[j] = new Food();
			food[j].time = food_times[j-1];
			food[j].idx = j;
		}
		// 정렬 - 먹어야 하는 시간과 음식 순서를 같이 묶어서 정렬.

        Arrays.sort(food, (o1, o2) -> o1.time - o2.time);

		int i = 1;
		while (i <= food_times.length) {
			
			long eat = ((long)food[i].time - (long)food[i - 1].time) * (long)food_len;
			
			if (k - (long)eat >= 0) {
				k = k - (long)eat;
			} else { // k
				// 인덱스 순서 정렬
				// 남은 i~food_times.length까지 정렬
				
				k %= (long)food_len;
				long idxArr[] = new long[food_len];
				for(int j=0;j<food_len;j++) {
					idxArr[j] = food[i+j].idx; 
				}
				Arrays.sort(idxArr);
				return (int)idxArr[(int) k];
			}

			i++;
			food_len--;

		}

		return -1;
	}

	 static class Food {
		int time;
		int idx;

		public Food() {
		}

		public Food(int time, int idx) {
			this.time = time;
			this.idx = idx;
		}

	}
}