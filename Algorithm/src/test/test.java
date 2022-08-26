package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {

	static String input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		input += "??!";
		System.out.println(input);
	}

}
