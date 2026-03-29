import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int[] prime = new int[N+1];
        int[] pos = new int[max+1];
        
        for(int i = 0; i < N; i++) {
            pos[arr[i]] = i + 1;
        }

        for(int mod : arr) {
            for(int i = mod*2; i <= max; i += mod) {
                if (pos[i] != 0) {
                    prime[pos[i]]--;
                    prime[pos[mod]]++;
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(prime[i] + " ");
        }
        System.out.println();
    }
}