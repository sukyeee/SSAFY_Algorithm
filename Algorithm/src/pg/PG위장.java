package pg;
import java.util.*;
import java.io.*;

public class PG위장 {

	class Solution {
	    public int solution(String[][] clothes) {
	        int answer = 1;
	        
	        HashMap<String, Integer> map = new HashMap<>();
	        
	        for(String[] s: clothes){
	            map.put(s[1], map.getOrDefault(s[1], 0) + 1);
	        }
	        
	        
	        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
	        
	        
	        while(iter.hasNext()){
	            Map.Entry<String, Integer> next = iter.next();
	            
	            answer = answer * (next.getValue() + 1);
	            
	        }
	        
	        answer--;
	        
	        
	        
	        return answer;
	    }
	}
}
