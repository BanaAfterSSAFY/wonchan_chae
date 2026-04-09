import java.io.*;
import java.util.*;

public class Main {

    static long[] arr = new long[55];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        solve();

        long result = cnt(M) - cnt(N - 1);
        System.out.print(result);
    }

    static long cnt(long N) {
        long count = N & 1;

        int size = (int) (Math.log(N) / Math.log(2));
        
        for(int i = size; i > 0; i--) {
            if((N & (1L << i)) != 0L) {
                count += arr[i - 1] +(N - (1L << i) + 1);
                N -= (1L << i);
            }
        }
        return count;
    }

    static void solve() {
        arr[0] = 1;
        
        for(int i = 1; i < 55; i++) {
            arr[i] = (arr[i - 1] << 1) + (1L << i);
        }
    }
}