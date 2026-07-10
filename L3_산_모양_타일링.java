import java.util.*;

class Solution {

    public int solution(int n, int[] tops) {
        int N = 2 * n + 1;
        int[] dp = new int[N + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= N; i++) {

            if(i % 2 == 0 && tops[(i - 1) / 2] == 1) {
                dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % 10007;
            }
            else {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            }
        }
        return dp[N];
    }
}