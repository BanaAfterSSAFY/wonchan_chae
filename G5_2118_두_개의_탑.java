import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N * 2];
        int sum = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            arr[i + N] = arr[i];
            sum += arr[i];
        }

        int s = 0;
        int e = 0;
        int ans = 0;
        int cur = 0;

        for(s = 0; s < N; s++) {
            while(e < s + N && cur + arr[e % N] <= sum / 2) {
                cur += arr[e % N];
                e++;
            }

            int tmp = cur;
            int rtmp = sum - tmp;

            ans = Math.max(ans, Math.min(tmp, rtmp));

            cur -= arr[s];
        }

        System.out.println(ans);
    }
}