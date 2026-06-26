import java.util.*;

class Solution {
    
    private int num[];
    private String ore[]; 
    private int dp[][][];
    
    public int solution(String arr[]) {
        
        int n = arr.length / 2;
        dp = new int[2][200][200];
        
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }
        
        num = new int[n + 1];
        ore = new String[n];
        for(int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) {
                num[i / 2] = Integer.parseInt(arr[i]);
                continue;
            }
            
            ore[i / 2] = arr[i];
        }
        
        return solve(0, 0, n);
    }
    
    public int solve(int flag, int start, int end) {
        if(start == end) {
            dp[flag][start][end] = num[start];
            return dp[flag][start][end];
        }
        
        if(check(flag, start, end)) {
            return dp[flag][start][end];
        }
        
        int result = flag == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE; 

        if(flag == 0) {
            for(int mid = start; mid < end; mid++) {
                if(ore[mid].equals("-")) {
                    result = Math.max(result, solve(0, start, mid) - solve(1, mid + 1, end));
                    continue;
                }
                
                result = Math.max(result, solve(0, start, mid) + solve(0, mid + 1, end));
            }
        }
        
        if(flag == 1) {
            for(int mid = start; mid < end; mid++) {
                if(ore[mid].equals("-")) {
                    result = Math.min(result, solve(1, start, mid) - solve(0, mid + 1, end));
                    continue;
                }
                
                result = Math.min(result, solve(0, start, mid) + solve(0, mid + 1, end));
            }
        }
        
        dp[flag][start][end] = result;
        return result;
    }
    
    public boolean check(int flat, int start, int end) {
        return dp[flat][start][end] != Integer.MAX_VALUE && dp[flat][start][end] != Integer.MIN_VALUE;
    }
}