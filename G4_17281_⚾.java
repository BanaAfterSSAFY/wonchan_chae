import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] arr;
    static boolean[] check;
    static int[] perm;
    static int Ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N+1][10];
        check = new boolean[10];
        perm = new int[10];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        check[1] = true;
        perm[4] = 1;
        
        solve(1);
        
        System.out.println(Ans);
    }
    
    public static void solve(int cnt) {
        if(cnt == 10) {
            //System.out.println(Arrays.toString(perm));
            print();
            return;
        }

        if(cnt == 4){
            solve(cnt + 1);
            return;
        }
        
        for(int i=1; i<10; i++) {
            if(check[i] == false) {
                check[i] = true;
                perm[cnt] = i;
                solve(cnt+1);
                check[i] = false;
            }
        }
    }
    
    public static void print() {
        int out = 0;
        int score = 0;
        int run = 0;
        int cnt = 1;
        int idx = 1;
        
        while(N >= cnt) {
            out = 0;
            run = 0;
            while(out < 3){
                
                if(arr[cnt][perm[idx]] == 0) {
                    out++;
                }
                else {
                    run = (run << arr[cnt][perm[idx]]) | (1 << (arr[cnt][perm[idx]] - 1));
                    score += Integer.bitCount(run >> 3);
                    run &= 7;
                }
                idx++;
                if(idx == 10) {
                    idx = 1;
                }
            }
            cnt++;
        }
        
        Ans = Math.max(Ans, score);
    }
}