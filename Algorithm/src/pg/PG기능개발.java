package pg;
import java.util.*;

public class PG기능개발 {

	class Solution {
	    public int[] solution(int[] progresses, int[] speeds) {

	        ArrayList<Integer> answer = new ArrayList<>();
	        int[] dayOfend = new int[100];
	        int day = -1;
	        for(int i=0; i<progresses.length; i++) {
	            while(progresses[i] + (day*speeds[i]) < 100) {
	                day++;
	            }
	            
	            dayOfend[day]++;
	        }
	        
	        for(int i=0;i<100;i++){
	            if(dayOfend[i] != 0) answer.add(dayOfend[i]);
	        }
	        int[] result = new int[answer.size()];
	        for(int i=0;i<answer.size();i++){
	            result[i] = answer.get(i);
	        }
	        
	        
	        return result;
	    }
	}
	
}
