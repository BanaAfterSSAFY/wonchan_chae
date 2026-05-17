import java.util.*;

class Solution {

    public int[] solution(String[] gems) {

        Set<String> set = new HashSet<>(Arrays.asList(gems));
        
        Map<String, Integer> map = new HashMap<>();
        
        
        int l = set.size();
        int min = Integer.MAX_VALUE;
        int s = 0, e = 0;
        int idx = 0;
        
        while(e < gems.length) {
           
            map.put(gems[e], map.getOrDefault(gems[e], 0) + 1);
            e++;
            
            while(map.size() == l) {
                if(e - s < min) {
                    min = e - s;
                    idx = s;  
                }
                
                map.put(gems[s], map.get(gems[s]) - 1);

                if(map.get(gems[s]) == 0) {
                    map.remove(gems[s]);
                }

                s++;
            }
        }
        return new int[]{idx + 1, idx + min};
    }
}