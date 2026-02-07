import java.io.*;
import java.util.*;

public class Main {
    static int N, P, K, D;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        boolean flag = false;

        if(D != 1) {
            int sum = 0;
            for(int i=D-1; i>=0; i--) {
                arr[D - 1 - i] = i;
                sum += arr[D - 1 - i];
            }
            if(P >= sum) {
                arr[0] += P - sum;
                flag = true;
            }
        }
        else {
            for(int i=0; i<K; i++) {
                arr[i] = P / K;
            }
            int min = P / K;
            int mod = P % K;
            for(int i=K; i<N; i++) {
                if(mod - min > 0) {
                    mod -= min;
                    arr[i] = min;
                } else {
                    arr[i] = mod;
                    mod = 0;
                    break;
                }
            }
            if(mod == 0) {
                flag = true;
            }
        }
        
        if(flag) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<N; i++) {
                sb.append(arr[i]).append("\n");
            }
            System.out.println(sb.toString().trim());
        }
        else {
            System.out.println("Wrong information");
        }
    }
}