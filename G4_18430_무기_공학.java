import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[][] arr;
    static int[][] check;
    static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        check = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

        solve(0);
        
        System.out.println(ans);
    }
    
    public static void solve(int cnt) {

        if(cnt == N * M) {
            int ret = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    ret += check[i][j] * arr[i][j];
                }
            }
            ans = Math.max(ans, ret);
            return;
        }
        
        int r = cnt / M, c = cnt % M;
        
        solve(cnt+1);
        
        if(check[r][c] != 0) {
            return;
        }
        
        if(c-1 > -1 && r+1 < N && check[r][c-1] == 0 && check[r+1][c] == 0) {
            check[r][c-1] = 1;
            check[r+1][c] = 1;
            check[r][c] = 2;
            solve(cnt+1);
            check[r][c-1] = 0;
            check[r+1][c] = 0;
            check[r][c] = 0;
        }
        
        if(c+1 < M && r+1 < N && check[r][c+1] == 0 && check[r+1][c] == 0) {
            check[r][c+1] = 1;
            check[r+1][c] = 1;
            check[r][c] = 2;
            solve(cnt+1);
            check[r][c+1] = 0;
            check[r+1][c] = 0;
            check[r][c] = 0;
        }
        
        if(c-1 > -1 && r-1 > -1 && check[r][c-1] == 0 && check[r-1][c] == 0) {
            check[r][c-1] = 1;
            check[r-1][c] = 1;
            check[r][c] = 2;
            solve(cnt+1);
            check[r][c-1] = 0;
            check[r-1][c] = 0;
            check[r][c] = 0;
        }
        
        if(c+1 < M && r-1 > -1 && check[r][c+1] == 0 && check[r-1][c] == 0) {
            check[r][c+1] = 1;
            check[r-1][c] = 1;
            check[r][c] = 2;
            solve(cnt+1);
            check[r][c+1] = 0;
            check[r-1][c] = 0;
            check[r][c] = 0;
        }
            
    }
}