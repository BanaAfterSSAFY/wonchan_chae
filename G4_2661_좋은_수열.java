import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static boolean flag;
    static boolean[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        int[] ans = new int[N+1];
                
        solve(ans, 1, 1);
    }
    
    public static void solve(int[] arr, int idx, int next) {
        if(flag == true) {
            return;
        }
        
        arr[idx] = next;
        boolean tmp;
        for(int i=0; i<idx / 2; i++) {
            int s = idx - 1 - 2 * i, e = idx - i;
            tmp = true;
            for(int j=0; j<=i; j++) {
                if(arr[s+j] != arr[e+j]) {
                    tmp = false;
                }
            }
            
            if(tmp == true) {
                return;
            }
        }
        
        if(idx == N) {
            for(int i=1; i<=N; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
            flag = true;
            return;
        }
                
        solve(arr, idx+1, 1);
        solve(arr, idx+1, 2);
        solve(arr, idx+1, 3);
        
        return;
    }
    
}