import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] arr;
    static int[] diff;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        diff = new int[N-1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            
        }
        
        for(int i=0; i<N-1; i++) {
            diff[i] = arr[i+1] - arr[i];

        }

        Arrays.sort(diff);
        
        for(int i=0; i<N-1-K+1; i++) {
            ans += diff[i];
        }

        System.out.println(ans);
    }
}