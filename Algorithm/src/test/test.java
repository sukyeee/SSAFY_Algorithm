package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {

	static String input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
//		System.out.println(Math.atan2(3, 2));
//		System.out.println(Math.atan(0.44) * 180 / Math.PI); // 
		
		float white_ball_y = 127;
		float white_ball_x = 63.5f;
		
		float ball_y = 240;
		float ball_x = 110;
		
		float w = Math.abs(white_ball_x - ball_x);
		float h =  Math.abs(white_ball_y - ball_y);
		float distance = (float)Math.sqrt( Math.pow( h , 2)  + Math.pow( w , 2) );	
		System.out.println(w/h);
		
		System.out.println( Math.atan( (h/w) ) * 180 / Math.PI ); //라디안 각도 -> 일반 각도로 변환 
		
//		System.out.println(Math.atan( (8.0/6.0) ) * 180 / Math.PI);
		System.out.println(Math.atan2(h,w)  * 180 / Math.PI );
		
		System.out.println(Math.sqrt((90*90 + 50*50)) + 5.73);
		System.out.println(5.73/2 + 10);
		
		System.out.println(Math.sqrt((105.73*105.73) - (105.205*105.205)));
		
		
		int hole_x = 127;
		int hole_y = 0;
		int goal_x = 10;
		int goal_y = 100;
		int white_x = 100;
		int white_y = 50;
		double r = 5.73; // 당구공 지름 
		// 홀에서 목표구까지의 거리 
		double diff_x = Math.abs( goal_x - hole_x ); 
		double diff_y = Math.abs( goal_y - hole_y );
		
		double d = Math.sqrt(  (diff_x * diff_x)   +  (diff_y * diff_y) );
		double D = d + r;
		double se = (Math.atan2( goal_y , goal_x ) * 180 / Math.PI); // 세타 
		double H = Math.cos(se) * D;
		double W = Math.sin(se) * D; // 또는 피타고라서 정리로도 가능 
		
		// 가상 목표 지점 W, H 구하기 완료
		
		// 흰 공과 가상 목표 지점과의 각도 구하기
		diff_x = Math.abs( W - white_x ); 
		diff_y = Math.abs( H - white_y );
		d = Math.sqrt(  (diff_x * diff_x)   +  (diff_y * diff_y) );
		
		Math.atan2(diff_x, diff_y);
	
		
		System.out.println(  Math.atan2(35, 65 ) * 180  / Math.PI  );
		
		
		
	}

}
