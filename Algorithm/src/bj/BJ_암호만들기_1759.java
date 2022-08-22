package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_암호만들기_1759 {

	static int L, C;
	static char[] src;
	static char[] tgt;
	static boolean[] isMo;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		tgt = new char[L];
		src = new char[C];
		isMo = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i <C; i++) {
			src[i] = st.nextToken().charAt(0);
		}
		
		// 정렬 src natural ordering
		Arrays.sort(src);
		
		for (int i = 0; i < C; i++) {
			if( src[i] == 'a' || src[i] == 'e' || src[i] == 'i' || src[i] == 'o' || src[i] == 'u') {
				isMo[i] = true;
			}
		}
		
		comb(0,0,0,0);
		System.out.println(sb);
	}
	
	
	static void comb( int srcIdx, int tgtIdx, int moCnt, int jaCnt ) {
		// 기저 조건
		if (tgtIdx == L) {
			// complete code
			if( moCnt >= 1 && jaCnt >= 2) {
				
				for (int i = 0; i < L; i++) {
					sb.append(tgt[i]);
				}
				sb.append('\n');
				
			}
			return;
		}
		
		// 기저 조건
		if( srcIdx == C ) return;
		
		tgt[tgtIdx] = src[srcIdx]; // 현재 소스를 현재 타깃에 받아 들인다.
		
		// 다음 것을 따질 때
		// 현재 따지는 문자가 모음이냐 자음이냐에 따라 다르게 처리
		if( isMo[srcIdx] ) {
			comb( srcIdx + 1, tgtIdx + 1, moCnt + 1, jaCnt ); 
		} else {
			comb( srcIdx + 1, tgtIdx + 1, moCnt, jaCnt+1);
		}
		
		comb( srcIdx, tgtIdx, moCnt, jaCnt  );
	}

}
