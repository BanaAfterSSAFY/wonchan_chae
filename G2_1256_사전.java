import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, K;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        dp = new long[N + M + 1][N + M + 1];

        dp[0][0] = 1;
        for(int i = 1; i <= N + M; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            for(int j = 1; j < i; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];

                if(dp[i][j] > 1000000000) {
                    dp[i][j] = 1000000001;
                }
            }
        }

        if(dp[N + M][N] < K) {
            answer.append("-1");
        }
        else {
            while(N != 0 || M != 0) {
                if(dp[N + M - 1][M] >= K) {
                    answer.append("a");
                    N--;
                }
                else {
                    answer.append("z");
                    K -= dp[N + M - 1][M];
                    M--;
                }
            }
        }

        System.out.println(answer.toString());
    }
}