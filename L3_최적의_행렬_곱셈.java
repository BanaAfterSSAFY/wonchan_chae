import java.util.*;

class Solution {
    
    public int solution(int[][] matrix_sizes) {
        int N = matrix_sizes.length;
        int[][] dp = new int[N][N];
        
        for(int[] at : dp) {
            Arrays.fill(at, Integer.MAX_VALUE);
        }

        for(int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }

        for(int i = 1; i < N; i++) {
            for(int s = 0; s < N; s++) {
                int e = s + i;
                
                if(e >= N) {
                    break;
                }

                for(int j = s; j < e; j++) {
                    dp[s][e] = Math.min(dp[s][e], dp[s][j] + dp[j + 1][e] + (matrix_sizes[s][0] * matrix_sizes[j + 1][0] * matrix_sizes[e][1]));
                }
            }
        }
        return dp[0][N - 1];
    }
}