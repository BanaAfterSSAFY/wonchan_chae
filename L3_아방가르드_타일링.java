import java.util.*;

class Solution {

    public int solution(int n) {

        long[] dp = new long[n + 1];
        long[] dp4 = new long[n + 1];
        long[] dp5 = new long[n + 1];
        long[] dp6 = new long[n + 1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            if(i - 1 >= 0) {
                dp[i] = (dp[i] + dp[i - 1]) % 1000000007;
            }

            if(i - 2 >= 0) {
                dp[i] = (dp[i] + dp[i - 2] * 2) % 1000000007;
            }

            if(i - 3 >= 0) {
                dp[i] = (dp[i] + dp[i - 3] * 5) % 1000000007;
            }

            if(i - 4 >= 0) {
                long tmp = (dp[i - 4] * 2) % 1000000007;
                dp4[i] = (dp4[i-3] + tmp) % 1000000007;
                dp[i] = (dp[i] + dp4[i]) % 1000000007;
            }

            if(i - 5 >= 0) {
                long tmp = (dp[i - 5] * 2) % 1000000007;
                dp5[i] = (dp5[i-3] + tmp) % 1000000007;
                dp[i] = (dp[i] + dp5[i]) % 1000000007;
            }

            if(i - 6 >= 0) {
                long tmp = (dp[i - 6] * 4) % 1000000007;
                dp6[i] = (dp6[i-3] + tmp) % 1000000007;
                dp[i] = (dp[i] + dp6[i]) % 1000000007;
            }
        }

        return Math.toIntExact(dp[n]);
    }
}