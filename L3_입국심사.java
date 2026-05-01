import java.util.*;

class Solution {

    public long solution(int n, int[] times) {
        long answer = 0;
        
        long s = 0;
        long e = times[times.length - 1] * (long)n;
        
        while(s <= e) {
            long mid = (s + e) / 2;
            long sum = 0;

            for(int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            if(sum >= n) {
                e = mid - 1;
                answer = mid;
            }
            else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
}