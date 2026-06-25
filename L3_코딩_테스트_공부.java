import java.util.*;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        
        int ma = alp;
        int mc = cop;
        for(int[] at : problems) {
            ma = Math.max(ma, at[0]);
            mc = Math.max(mc, at[1]);
        }

        if(alp == ma && cop == mc) {
            return 0;
        }

        int[][] dp = new int[ma + 2][mc + 2];
        for(int[] at : dp) {
            Arrays.fill(at, Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;

        for(int i = alp; i < dp.length - 1; i++) {
            for(int j = cop; j < dp[0].length - 1; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for(int[] at : problems) {
                    if(i >= at[0] && j >= at[1]) {
                        int na = Math.min(i + at[2], ma);
                        int nc = Math.min(j + at[3], mc);

                        dp[na][nc] = Math.min(dp[na][nc], dp[i][j] + at[4]);
                    }
                }
            }
        }
        return dp[ma][mc];
    }
}