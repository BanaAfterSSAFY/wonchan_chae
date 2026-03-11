import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M;
    static int[] arr;
    static int[][][] dp;
    static int[][] move;
    static int idx, ans = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        arr = new int[100001];
        arr[idx++] = 0;
        
        while(true) {
            int inp = Integer.parseInt(st.nextToken());
            if(inp == 0)
                break;
            
            arr[idx++] = inp;
        }
        
        dp = new int[idx][5][5];
        move = new int[5][5];
        
        for(int i=0; i<5; i++) {
            move[i][i] = 1;
        }
        move[0][1] = move[0][2] = move[0][3] = move[0][4] = 2;
        move[1][2] = move[1][4] = move[2][3] = move[2][1] = move[3][4] = move[3][2] = move[4][1] = move[4][3] = 3;
        move[1][3] = move[2][4] = move[3][1] = move[4][2] = 4;
        
        for(int i=0; i<idx; i++) {
            for(int j=0; j<5; j++) {
                Arrays.fill(dp[i][j], 123456789);
            }
        }
        dp[0][0][0] = 0;
        for(int k=1; k<idx; k++) {
            int cmd = arr[k];
            
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    dp[k][i][cmd] = Math.min(dp[k][i][cmd], dp[k-1][i][j] + move[j][cmd]);
                    dp[k][cmd][j] = Math.min(dp[k][cmd][j], dp[k-1][i][j] + move[i][cmd]);
                }
            }
        }
        
        for(int i=0; i<5; i++) {
            ans = Math.min(ans, dp[idx-1][i][arr[idx-1]]);
            ans = Math.min(ans, dp[idx-1][arr[idx-1]][i]);
        }
        
        System.out.println(ans);
    }
}