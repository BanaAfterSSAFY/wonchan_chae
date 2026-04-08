import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 2][N + 1];

            boolean flag = (N % 2 == 1);

            for(int i = 1; i <= N; i++) {
                for(int j = 1; j + i - 1 <= N; j++) {
                    int left = j;
                    int right = j + i - 1;

                    if(flag == true) {
                        dp[left][right] = Math.max(dp[left + 1][right] + arr[left], dp[left][right - 1] + arr[right]);
                    }
                    else {
                        dp[left][right] = Math.min(dp[left + 1][right], dp[left][right - 1]);
                    }
                }
                flag = !flag;
            }
            System.out.println(dp[1][N]);

        }
    }
}