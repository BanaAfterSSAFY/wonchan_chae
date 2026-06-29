import java.util.*;

class Solution {

    public int[] solution(int e, int[] starts) {
    
        int[] ans = new int[starts.length];
        int[] arr = new int[e+1];
        int[] cnt = new int[e+1];
        int v = 0;

        arr[1] = 1;
        cnt[1] = 1;
    
        for(int i = 2; i <= e; i++) {
            int n = e / i;
            int tmp = i;
            for(int k = 1; k <= n; k++) {
                arr[tmp] += 1;
                tmp += i;
            }   
        }
        
        for(int i = e; i >= 1; i--) {
            if(arr[i] >= v) {
                v = arr[i];
                cnt[i] = i;
            }
            else {
                cnt[i] = cnt[i + 1];
            }
        }
        
        for(int i = 0; i < starts.length; i++) {
            ans[i] = cnt[starts[i]];
        }
        
        return ans;
    }
}