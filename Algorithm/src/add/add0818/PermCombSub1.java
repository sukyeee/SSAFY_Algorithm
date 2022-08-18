package add.add0818;

import java.io.*;
import java.util.*;


// nPr, nCr, 2^n
// 과목평가는 가장 쉬운 im 문제 하나 나옴. 첫번째 문제는 다 맞출 수 있도록 나옴.
// + im 문제 중에 조금 어려운 문제 하나 더.
// + 3번째 문제는 100점 안주려고 숨어있는 테케  더 있음. 그 테케까지 통과 되어야 100점임
// 4번째. 순열 조합 부분집합

public class PermCombSub1 {

	static int N = 4, R = 3, C = 0;
	static int[] a = { 1, 2, 3, 4 }, b = new int[R];
	static boolean[] v = new boolean[N];

	public static void main(String[] args) {
//		perm(0, 0); // 순열 4P3  = 4*3*2= 24 중복순열 4ㅠ3 = 4^3 = 64
		ppch(0, 0); // 조합  4C3 = 4, 중복조합 4H3 = 4+3 - 1C3 = 6C3 = 20
//		subset(0); // 부분집합 2^4 = 16
		System.out.println(C);
	}
	
	static void ppch(int depth, int start) {
		
		if(depth == R) {
			System.out.println(Arrays.toString(b));
			C++;
			return;
		}
		
		for (int i = start; i < N; i++) {
			if(v[i]) continue;  // 방문 체크하지않으면 중복순열임
			v[i] = true;
			b[depth] = a[i];
//			ppch(depth + 1, 0); // 순열	
			ppch(depth + 1, i);
			v[i] = false;
		}
	
		// 중복 순열
//		for (int i = 0; i < N; i++) {
//			b[depth] = a[i];
//			perm(depth + 1);
//		}
		
	}
	
	
	
	static void subset(int depth) {
		if(depth == N) {
			
			for(int i=0;i<N;i++) System.out.print(v[i] ? a[i]:"X");
			System.out.println();
			C++;
			return;
		}
		
		
		v[depth] = true;
		subset(depth + 1);
		v[depth] = false;
		subset(depth + 1);
		
		
	}
	
}
