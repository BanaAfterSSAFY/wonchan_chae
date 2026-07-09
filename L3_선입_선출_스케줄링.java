import java.util.*;

class Solution {

    public int solution(int n, int[] cores) {
        int answer = 0;        
        int len = cores.length;
        
        if(n <= len) {
            return n;
        }
    
        int s = 1;
        int e = 10000 * n;
        int time = 0;
        int cnt = 0;
        
        while(s <= e) {
            int mid = (s + e) / 2;
            
            int count = solve(mid, cores);
            
            if(count >= n) {
                e = mid - 1;
                time = mid;
                cnt = count;
            }
            else {
                s = mid + 1;
            }
        }
        
        cnt -= n;
        for(int i = cores.length - 1; i >= 0; i--) {
            if(time % cores[i] == 0) {
                if(cnt == 0) {
                    answer = i + 1;
                    break;
                }
                cnt--;
            }
        }
        
        return answer;
    }
    
    public int solve(int mid, int[] cores) {

        int count = cores.length;
        
        for(int i = 0; i < cores.length; i++) {
            count += mid / cores[i];
        }
        
        return count;
    }
}