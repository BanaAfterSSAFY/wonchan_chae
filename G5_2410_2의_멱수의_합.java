import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];
        dp[0] = dp[1] = 1;

        for(int i = 2; i < N + 1; i++) {
            dp[i] = (dp[i - 2] + dp[i / 2]) % 1000000000L;
        }

        System.out.println(dp[N]);
    }
}