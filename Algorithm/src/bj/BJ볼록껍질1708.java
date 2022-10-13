package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ볼록껍질1708 {

	static int N;
	static long sx, sy;
	static long[][] point; // point[3][0] 4번째 x 좌표의 수 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		point = new long[N][2];
		
		// point 배열에 입력 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작점 sx, sy 지정 ( 맨 앞의 점을 시작점으로 )
		sx = point[0][0];
		sy = point[0][1];
		// 시작점을 y 가 가장 작은 점, y가 같은 점이 있다면 x가 가장 작은 점 계산 => sy, sx 로 
		for (int i = 1; i < N; i++) {
			if( sy > point[i][1] ) {
				sx = point[i][0];
				sy = point[i][1];
			} else if ( sy == point[i][1] && sx > point[i][0] ) {
				sx = point[i][0];
				sy = point[i][1];
			}
		}
		// sx, sy는 가장 낮은 ( 가장 왼쪽 위치 )
		
			
		// point 배열을 반시계방향으로 정렬 
		Arrays.sort(point, (p1, p2) -> {
			int ret = ccw( sx, sy, p1[0], p1[1], p2[0], p2[1]);
			if( ret > 0 ) { // 반시계 방향 
				return -1;
			}else if ( ret < 0) { // 시계 방향 
				return 1;
			}else {
				long diff = distance( sx, sy, p1[0], p1[1] ) - distance( sx, sy, p2[0], p2[1] );
				return diff > 0 ? 1 : -1 ;
			}
			
		});
		
		// Stack 객체 생성 
		Stack<long[]> st = new Stack<>();
		
		// 시작점을 Stack 에 넣는다.
		st.push(new long[] { sx, sy });
		
		int length = point.length;
		// 시작점을 제외한 모든 점을 순회 
		for (int i = 1; i < length; i++) {
			// stack 에 들어가 있는 이전 2개의 점과, i점과의 ccw 확인 
			long[] next = point[i]; // 따지려는 점 
			while( st.size() > 1 ) {
				// next 가 이전 2개의 점과 반시계 방향인지 따져봐서, 아니면 계속 반시계 방향이 만들어지도록 기존 점을 삭제
				long[] first = st.get(st.size()-2);
				long[] second = st.get(st.size()-1);
				int ret = ccw( first[0], first[1], second[0], second[1], next[0], next[1] );
				if( ret <= 0 ) st.pop();
				else break;
			}
			st.push(point[i]); // 새로 추가 
			
			// for문을 이용해서 각 i 점에 대해서 
			// stack 에 들어가 있는 이전 2개의 점과 i점과의 ccw를 확인해서
			// 반시계 방향이 아니면 계속 꺼낸다. (반복적으로) => stack 에 i 점을 넣는다.
			
		}
		
		
		
		// stack 에 들어가 있는 점들이 바로 볼록껍질을 구성하는 점들이므로 Stack의 크기를 출력.
		System.out.println(st.size());
	}
	
	static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
	
		long ccw = (( x1*y2 + x2*y3 + x3*y1  )  - ( y1*x2 + y2*x3 + y3*x1 ));
		
		if(ccw > 0) {
			return 1;
		} else if ( ccw < 0 ) {
			return -1;
		}else {
			return 0;
		}
		
		
	}
	
	static long distance( long x1, long y1, long x2, long y2 ) {
		return (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
	}
	
	

}
/*
8
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
*/