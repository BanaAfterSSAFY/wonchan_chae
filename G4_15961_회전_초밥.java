import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static int[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        check = new int[d + 1];
        int cnt = 0;
        
        int s = 0;
        for(int i=0; i<k; i++) {
            if(check[arr[i]] == 0) {
                cnt++;
            }
            check[arr[i]]++;
        }
        
        int max = (check[c] == 0) ? cnt + 1 : cnt;
        
        for(int i=0; i<N; i++) {
            check[arr[i]]--;
            if(check[arr[i]] == 0) {
                cnt--;
            }

            int idx = (i + k) % N;
            if(check[arr[idx]] == 0) {
                cnt++;
            }
            check[arr[idx]]++;

            int tmp = (check[c] == 0) ? cnt + 1 : cnt;
            max = Math.max(max, tmp);
            
            if(max == k + 1) break;
        }
        System.out.println(max);
    }
}