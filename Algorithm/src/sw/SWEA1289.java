package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class SWEA1289 {
	void bit() {
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//메모리값 되돌리기
		// 원래 메모리에 뭐가있는지 알지만,, 3번째 거 바꾸면 뒤에거 까지 바뀜.
		// 원래 상태로 돌아가는데 최소 몇번 고쳐야 하는지?
		
		// 0000 -> 0011 1번
		// 000 -> 111 -> 100 2번
		
		System.setIn(new FileInputStream("SWEA1289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		
		int result = 0;
		String line;
		for(int tc=1;tc<=TC;tc++) {
			line = br.readLine();
			int arr[] = new int[line.length()];
			for(int i=0;i<line.length();i++) {
				arr[i] = line.charAt(i) - '0';
			}
			// 입력 완
			
			int ans[] = new int[line.length()];
			boolean flag = true;
			int cnt = 0;
			while(true) {
				flag = true;
				for(int i=0;i<line.length();i++) {
					if(arr[i] != ans[i]) {
						flag = false;
						break;
					}
				} //하나라도 다를 시 break, 같지 않음
				if(flag) break;
				
				for(int i=0;i<line.length();i++) {
					if(arr[i] != ans[i]) {
						for(int j=i;j<line.length();j++) {
							ans[j] = arr[i];	
						}
						break;
					} // 다르면 뒤에꺼 다 바꿈.
				}
				
				
				
				cnt++;
			}
			
		
			System.out.println("#"+tc+ " " +cnt);
		}
		
		
		
	}

}
