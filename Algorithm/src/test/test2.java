package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노란색은 인접한 두 층 연속 사용 가능
		// 파란색은 인접한 두 층에 연속 사용 불가 
		// f(1) = 2, 
		// f(2) = 3,
		// f(3) = 5   3층 -> 
		// f(4) = 8
		// f(5) = 13
		// f(n) = f(n-1) + f(n-2)
		
		
		// 1cm 파란 막대 노란 막대, 2cm 빨간 막대 
		// 이 막대들을 연결해서 길이가 n  cm 인 막대를 만드는 방법의 수 ?
		// f(1) = 2
		// 2cm를 만드는 방법 f(2)  = 5;
		// 파 파 / 파 노 / 노 파 / 노 노/ 빨간막대 
		// 3cm는 파 파 노 / 파 파 파 / 파 노 파 / 파 노 노 / 노 파 노 / 노 파 파 / 노 노 노 / 노 노 파 / 빨 노 / 빨 파 / 노 빨 / 파 빨 
		// f(3) = 12 
	    // f(4) = 29 인가?! 해보자 
		
		// f(n) = f(n-1)*2 + f(n-2)
		
		// f(n) = f(n-1)+f(n-2) + f(n-1)
		// f(6) = 169
	}

}
