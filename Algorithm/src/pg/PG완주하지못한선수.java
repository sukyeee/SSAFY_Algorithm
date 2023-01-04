package pg;
import java.util.*;
import java.io.*;

public class PG완주하지못한선수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	class Solution {
	    public String solution(String[] participant, String[] completion) {
	        String answer = "";
	      
	        HashMap<String, Integer> map = new HashMap<>();
	        
	        for(String part: participant){
	            map.put(part, map.getOrDefault(part, 0) + 1);
	        }
	        for(String comp: completion){
	            map.put(comp, map.get(comp) - 1);
	        }
	        
	        Iterator<Map.Entry<String,Integer>> iter = map.entrySet().iterator();
	        
	        while(iter.hasNext()){
	            Map.Entry<String,Integer> next = iter.next();
	            if(next.getValue() != 0){
	                answer = next.getKey();
	                break;
	            }
	        }
	        
	        
	        return answer;
	    }
	}

}
