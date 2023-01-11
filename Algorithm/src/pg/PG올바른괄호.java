package pg;
import java.util.*;

public class PG올바른괄호 {


	class Solution {
	    boolean solution(String s) {
	        boolean answer = true;

	        Stack<String> st = new Stack<>();
	        
	        // System.out.print(s.charAt(0));
	        for(int i=0;i<s.length();i++){
	            if(s.charAt(i) == '(') st.push("(");
	            else {
	                if(st.size() < 1) return false;
	                st.pop();
	            }
	        }

	        if(st.size() != 0) return false;
	        
	        
	        return answer;
	    }
	}
	
}
