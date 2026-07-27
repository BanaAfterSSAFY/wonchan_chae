import java.util.*;

class Solution {

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        
        t1 += 10;
        t2 += 10;
        int tmp = temperature + 10;
        
        int[][] dp = new int [onboard.length][51];
        for(int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], 100001);
        }

        dp[0][tmp] = 0;
        
        for(int i = 0; i < onboard.length - 1; i++) {
            for(int j = 0; j <= 50; j++) {
                if(onboard[i] == 1 && (j < t1 || t2 < j)) {
                    continue;
                }

                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b);

                if(j >= 1) {
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a);
                }

                if(j < 50) {
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a);
                }
                
                if(tmp == j) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                }
                else if(tmp > j && j < 50) {
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                }
                else if(tmp < j && j >= 1) {
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
            }
        }

        int min = 100001;
        int cnt = onboard.length - 1;
        
        for(int i = 0; i <= 50; i++) {
            if(onboard[cnt] == 1 && (i < t1 || t2 < i)) {
                continue;
            }
            min = Math.min(min, dp[cnt][i]);
        }
        return min;
    }
}