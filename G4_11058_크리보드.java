import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        long[] dp = new long[N+1];

        for(int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        for(int i = 7; i <= N; i++) {
            for(int j = 3; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }
        
        System.out.println(dp[N]);
    }
}