import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        
        long ans = Long.MAX_VALUE;

        long[] arr = new long[N];
        long max = 0;
        
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);

        long s = 0;
        long e = max * M;
        while(s <= e) {
            long mid = (s + e) / 2;
            long sum = 0;
            for(int i = 0; i < N; i++) {
                long cnt = mid / arr[i];

                if(sum >= M) {
                    break;
                }

                sum += cnt;
            }

            if(sum >= M) {
                e = mid - 1;
                ans = Math.min(ans, mid);
            }
            else {
                s = mid + 1;
            }
        }
        System.out.println(ans);
    }
}