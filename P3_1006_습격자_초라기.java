import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, W;
    static int[][] arr;
    static int[][] dp;
    static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            arr = new int[2][N];
            dp = new int[3][N];
            ans = Integer.MAX_VALUE;
            
            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            dp[0][0] = 1;
            dp[1][0] = 1;
            if(arr[0][0] + arr[1][0] <= W) {
                dp[2][0] = 1;
            }
            else {
                dp[2][0] = 2;
            }
            
            solve();
            ans = Math.min(ans, dp[2][N-1]);
            
            if(N > 1 &&arr[0][0] + arr[0][N-1] <= W) {
                dp = new int[3][N];
                dp[0][0] = 1;
                dp[1][0] = 1234567890;
                dp[2][0] = 2;
                
                solve();
                ans = Math.min(ans, dp[1][N-1]);
            }
            

            if(N > 1 && arr[1][0] + arr[1][N-1] <= W) {
                dp = new int[3][N];
                dp[0][0] = 1234567890;
                dp[1][0] = 1;
                dp[2][0] = 2;
                
                solve();
                ans = Math.min(ans, dp[0][N-1]);
            }
            

            if(N > 1 && arr[0][0] + arr[0][N-1] <= W && arr[1][0] + arr[1][N-1] <= W) {
                dp = new int[3][N];
                dp[0][0] = 1234567890;
                dp[1][0] = 1234567890;
                dp[2][0] = 2;
                
                solve();
                ans = Math.min(ans, dp[2][N-2]);
            }
            
            System.out.println(ans);
            
        }
        
    }
    
    static void solve() {
        
        for(int i=1; i<N; i++) {
            
            dp[0][i] = dp[2][i-1] + 1;
            dp[1][i] = dp[2][i-1] + 1;
            dp[2][i] = dp[2][i-1] + 2;
            
            if(arr[0][i] + arr[0][i-1] <= W) {
                dp[0][i] = Math.min(dp[0][i], dp[1][i-1] + 1);
            }
            
            
            if(arr[1][i] + arr[1][i-1] <= W) {
                dp[1][i] = Math.min(dp[1][i], dp[0][i-1] + 1);
            }
            
            
            if(arr[0][i] + arr[1][i] <= W) {
                dp[2][i] = Math.min(dp[2][i], dp[2][i-1] + 1);
            }
            
            dp[2][i] = Math.min(dp[2][i], dp[0][i] + 1);
            dp[2][i] = Math.min(dp[2][i], dp[1][i] + 1);
            
            
            if(i > 1) {
                if(arr[0][i] + arr[0][i-1] <= W && arr[1][i] + arr[1][i-1] <= W) {
                    dp[2][i] = Math.min(dp[2][i], dp[2][i-2] + 2);
                }
            }
            else {
                if(dp[0][i-1] != 1234567890 && dp[1][i-1] != 1234567890 && arr[0][i] + arr[0][i-1] <= W && arr[1][i] + arr[1][i-1] <= W) {
                    dp[2][i] = Math.min(dp[2][i], 2);
                }
            }
        }
    }
}