import java.util.*;

class Solution {
    
    static long[] answer;
        
    public long[] solution(long k, long[] room_number) {
        
        int l = room_number.length;
        answer = new long [l];
        
        HashMap<Long, Long> hm = new HashMap<>();
        
        for(int i = 0; i < l; i++) {
            long tmp = room_number[i];
            answer[i] = solve(hm, tmp);
        }
            
        return answer;
    }
    
    public long solve(HashMap<Long, Long> hm, long tmp) {
        
        if(hm.containsKey(tmp) == false) {
            hm.put(tmp, tmp + 1);
            return tmp;
        }
        
        long ret = solve(hm, hm.get(tmp));
        hm.put(tmp, ret);
        return ret;
    }
}