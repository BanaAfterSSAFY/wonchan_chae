import java.util.*;
import java.io.*;

public class Main {

    static int[] arr;
    static int[] par;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        par = new int[N+1];
        check = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= N; i++) {
            par[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a > b) {
                union(b,a);
            }
            else {
                union(a,b);
            }
        }

        long cnt = 0;
        
        for(int i = 1; i <= N; i++) {
            int idx = find(i);

            if(check[idx] == false) {
                cnt += arr[idx];
                check[idx] = true;
            }

            check[i] = true;
        }

        if(cnt > k) {
            System.out.println("Oh no");
        }
        else {
            System.out.println(cnt);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(arr[a] > arr[b]) {
            par[a] = b;
        }
        else {
            par[b] = a;
        }
    }

    static int find(int a) {
        if(a == par[a]) {
            return a;
        }
        return par[a] = find(par[a]);
    }
}