import java.util.*;

class Solution {

    static long[][] comb;
    
    public int solution(int[][] a) {
        
        int N = a.length;
        int M = a[0].length;
        
        int[] arr = new int[M];
        int sum = 0;

        for(int x = 0; x < M; x++) {
            for(int y = 0; y < N; y++) {
                if(a[y][x] == 1) {
                    arr[x]++;
                    sum++;
                }
            }
        }
        
        if(sum % 2 == 1) {
            return 0;
        }
        
        long[][] dp = new long[M][N + 1];
        comb = new long[N + 1][N + 1];
        
        dp[0][arr[0]] = solve(N, arr[0]);
        
        for(int i = 1; i < M; i++) {
            for(int j = 0; j <= N; j++) {
                if(dp[i - 1][j] == 0) {
                    continue;
                }

                int o = arr[i];
            
                for(int k = Math.max(0, o - N + j); k <= Math.min(o, j); k++) {
                    dp[i][j - k + (o - k)] +=  (dp[i-1][j] * (solve(j, k) * (solve(N - j, o - k)) % 10000019)) % 10000019;
                    dp[i][j - k + (o - k)] %= 10000019;
                }
            }
        }
        return (int)dp[M - 1][0];
    }
    
    static long solve(int a, int b) {
        if(comb[a][b] != 0) {
            return comb[a][b];
        }
        
        if(b == 0) {
            return comb[a][b] = 1;
        }

        if(a == b) {
            return comb[a][b] = 1;
        }
        return comb[a][b] = (solve(a-1,b-1) + solve(a-1, b)) % 10000019;
    }
    
}