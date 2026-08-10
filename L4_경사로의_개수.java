import java.util.*;

class Solution {
    
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
    public int solution(int[][] grid, int[] d, int k) {
        
        int N =grid.length;
        int M = grid[0].length;
        int D = d.length;
        
        long[][][] dp = new long[D + 1][N * M][N * M];        
        
        for(int i = 0; i < N * M; i++) {
            dp[0][i][i] =  1;   
        }
        
        for(int l = 1; l <= D; l++) {
            for(int i = 0; i < N * M; i++) {
                int x = i % M;
                int y = i / M;
                
                for(int dd = 0; dd < 4; dd++) {
                    int nx = x + dir[dd][0];
                    int ny = y + dir[dd][1];
                    
                    if(nx < 0 || nx >= M || ny < 0 || ny >= N || grid[ny][nx] - grid[y][x] != d[l - 1] ) {
                        continue;
                    }
                    
                    for(int j = 0; j < N * M; j++) {
                        dp[l][j][ny * M + nx] += dp[l - 1][j][i] % 1000000007;
                        dp[l][j][ny * M + nx] %= 1000000007;
                    }
                }
            }
        }
        
        int count = 0;
        while(Math.pow(2, count) < k ) {
            count++;
        }
        
        long[][][] res = new long[count + 1][N * M][N * M];
        res[0] = dp[D];
        for(int c = 1; c <= count ; c++) {
            res[c] = solve(res[c - 1], res[c - 1]);
        }
        
        long[][] mat = new long[N * M][N * M];
        for(int i =0; i < N * M; i++) {
            mat[i][i] = 1;
        }
        
        int cnt = k;
        while(cnt > 0) {
            if(cnt >= Math.pow(2, count)) {
                mat = solve(mat, res[count]);
                cnt -= Math.pow(2, count);
            }
            count--;
        }    
    
        long answer = 0;
        for(int i = 0; i < N * M; i++) {
            for(int j = 0; j < N * M; j++) {
                answer += mat[i][j];
                answer %= 1000000007;
            }
        }
        
        return (int)answer;
    }
    
    static long[][] solve(long[][] a, long[][] b) {
        int N = a.length;
        long[][] result = new long[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int l = 0; l < N; l++) {
                    result[i][j] += ((a[i][l] % 1000000007) * (b[l][j] % 1000000007))% 1000000007;
                }
            }
        }
        
        return result;
    }
    
}