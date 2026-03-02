import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] sum = new int[2][N+2];
        for(int i = 1; i <= N; i++) {
            sum[0][i] = sum[0][i-1] + arr[i];
            sum[1][N-i+1] = sum[1][N-i+2] + arr[N-i+1];
        }
        int max = Integer.MIN_VALUE;

        for(int i = 2; i <= N-1; i++) {
            int tmp = sum[0][N] - sum[0][1] - arr[i] + sum[0][N] - sum[0][i];
            max = Math.max(max, tmp);
        }

        for(int i = N-1; i >= 2; i--) {
            int tmp = sum[1][1] - sum[1][N] - arr[i] + sum[1][1] - sum[1][i];
            max = Math.max(max, tmp);
        }

        for(int i = 2; i <= N-1; i++) {
            int tmp = sum[0][i] - sum[0][1] + sum[1][i] - sum[1][N];
            max = Math.max(max, tmp);
        }

        System.out.println(max);
    }
}