package pg;
import java.util.HashMap;
import java.util.Map;

public class PG전화번호목록 {


	class Solution {
	    public boolean solution(String[] phoneBook) {
	        // 1. HashMap을 선언한다.
	        Map<String, Integer> map = new HashMap<>();

	        // 2. 모든 전화번호를 HashMap에 넣는다.
	        for (int i = 0; i < phoneBook.length; i++) 
	            map.put(phoneBook[i], i);
	        
	        // 3. 모든 전화번호의 substring이 HashMap에 존재하는지 확인한다.
	        for (int i = 0; i < phoneBook.length; i++)
	            for (int j = 0; j < phoneBook[i].length(); j++)
	                if (map.containsKey(phoneBook[i].substring(0, j)))
	                    return false;

	        return true;
	    }
	}
	
}
