import java.io.*;
import java.util.*;


public class Main {
    
    static int N;
    static int[][] arr;
    static boolean[] check;
    static double Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            Ans = Double.MAX_VALUE;

            arr = new int[N][2];
            check = new boolean[N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            
            solve(0, 0);
            System.out.printf("%.8f\n", Ans);
        }
    }
    
    
    public static void solve(int cnt, int idx) {
        
        if(cnt == N / 2) {
            sum();
            return;
        }
        
        for(int i=idx; i<N; i++) {
            if(check[i] == false) {
                check[i] = true;
                solve(cnt+1, i+1);
                check[i] = false;
            }
        }
        return;
    }
    
    public static void sum() {
        int Y = 0, X = 0;
        for(int i=0; i<N; i++) {
            if(check[i] == true) {
                Y += arr[i][0];
                X += arr[i][1];
            }
            else {
                Y -= arr[i][0];
                X -= arr[i][1];
            }
        }
        
        Ans = Math.min(Ans, Math.sqrt(Math.pow(Y, 2) + Math.pow(X, 2)));
        
        return;
    }
}