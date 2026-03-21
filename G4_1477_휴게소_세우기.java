import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        arr = new int[N + 2];

        arr[0] = 0;
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;
        Arrays.sort(arr);

        int s = 1;
        int e = L-1;

        while(s <= e) {
            int mid = (s + e) / 2;
            int sum = 0;

            for(int i = 1; i < arr.length; i++) {
                sum += (arr[i] - arr[i-1] - 1) / mid;
            }

            if(sum > M) {
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
        }
        System.out.println(s);
    }
}