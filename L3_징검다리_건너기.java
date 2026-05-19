import java.util.*;

class Solution {    
    public int solution(int[] stones, int k) {

        int answer = 0;
        int max = 200000000;
        int min = 0;
        
        while(min <= max) {
            int mid = (max + min) / 2;
            if(check(mid, stones, k)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            }
            else {
                max = mid - 1;
            }
        }
        return answer;
    }
    
    public boolean check(int mid, int[] stones, int k) {
        int cnt = 0;
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] < mid) {
                cnt++;
            }
            else {
                cnt = 0;
            }

            if(cnt == k) {
                return false;
            }
        }
        return true;
    }
}