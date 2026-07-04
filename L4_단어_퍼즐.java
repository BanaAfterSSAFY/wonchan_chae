import java.util.*;

class Solution {

    public int solution(String[] strs, String t) {
    
        int answer = 0;
        int[] dp = new int[t.length()+1];
        dp[0] = 1;

        for(int i = 0; i < t.length(); i++) {
            
            if(dp[i] == 0) {
                continue;
            }
            
            for(int j = 0; j < strs.length; j++) {

                if(i + strs[j].length() > t.length()) {
                    continue;
                }
                
                int k = 0;
                for(; k < strs[j].length(); k++) {
                    if(t.charAt(i + k) != strs[j].charAt(k)) {
                        break;
                    }
                }
                
                if(k != strs[j].length()) {
                    continue;
                }
                
                dp[i + k] = dp[i + k] == 0 ? dp[i] + 1 : Math.min(dp[i + k], dp[i] + 1);
            }
        }
        
        return dp[t.length()] == 0 ? -1 : dp[t.length()] - 1;
    }
}