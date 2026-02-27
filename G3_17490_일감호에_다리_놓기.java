import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static long K;
    static int[] arr;
    static boolean[] check;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        
        arr = new int[N+1];
        check = new boolean[N+1];
                
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            check[i]= true;
        }
        
        int start = N;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            start = Math.min(start, b);
            check[b] = false;
        }
        
        if(M <= 1) {
            System.out.println("YES\n");
            return;
        }
        
        int temp = start;
        while(true) {
            int tmp = arr[start];
            while(check[(start % N) + 1]) {
                start = (start % N) + 1;
                tmp = Math.min(tmp, arr[start]);
            }
            start = (start % N) + 1;
            K -= tmp;
            if(start == temp) {
                break;
            }
        }
        
        if(K < 0) {
            System.out.println("NO\n");
        }
        else {
            System.out.println("YES\n");
        }
    }
}