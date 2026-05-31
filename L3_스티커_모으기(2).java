import java.util.*;

class Solution {

    public int solution(int sticker[]) {

        int answer = 0;

        if(sticker.length <= 3) {
            int ret = 0;
            for(int i = 0; i < sticker.length; i++) {
                ret = Math.max(ret, sticker[i]);
            }
            return ret;
        }
        
        int[] dp = new int[sticker.length - 1];
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        
        for(int i = 2; i < sticker.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);
        }

        answer = dp[sticker.length - 2];
        
        dp = new int[sticker.length];
        dp[1] = sticker[1];
        dp[2] = sticker[1];

        for(int i = 3; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);
        }
        answer = Math.max(answer, dp[sticker.length - 1]);
        
        dp = new int[sticker.length];
        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);
        }
        answer = Math.max(answer, dp[sticker.length - 1]);
        
        return answer;
    }   
}