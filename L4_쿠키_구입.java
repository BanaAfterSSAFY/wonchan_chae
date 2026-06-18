import java.util.*;

class Solution {

    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        
        for(int i = 0; i < n - 1; i++) {
            int l = cookie[i];
            int r = cookie[i + 1];
            
            int left = i;
            int right = i + 1;
            
            while(left >= 0 && right < n) {
                if(l == r) {
                    answer = Math.max(answer, l);
                }
                
                if(l <= r && left > 0) {
                    left--;
                    l += cookie[left];
                }
                else if(r < l && right < n - 1) {
                    right++;
                    r += cookie[right];
                }
                else {
                    break;
                }
            }
        }
        return answer;
    }
}