package pg;
import java.io.*;
import java.util.*;

public class PG폰켓몬 {


	class Solution {
	    public int solution(int[] nums) {
	        int answer = 0;
	        int max = 0;
	        
	        // 내가 N/2 개 가져도 돼~
	        
	        max = nums.length/2; // 최대 N/2를 넘어갈 순 없음
	        
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for(int i: nums){
	            map.put(i, map.getOrDefault(i, 0) + 1);
	        }
	        
	        
	        answer = Math.min(max, map.size());
	        
	        return answer;
	    }
	}
}

