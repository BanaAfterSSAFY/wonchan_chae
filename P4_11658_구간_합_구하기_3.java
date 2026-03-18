import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M, K;
    static long[][] arr;
    static long[][] tree;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1][N+1];
        tree = new long[N+1][N+1];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
                update(i, j, arr[i][j]);
            }
        }
        
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int cmd = Integer.parseInt(st.nextToken());
            
            if(cmd == 0) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                long d = c - arr[a][b];
                arr[a][b] = c;
                
                update(a, b, d);
                
            }
            else {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                System.out.println(sum(x2, y2) - sum(x1-1, y2) - sum(x2, y1-1) + sum(x1-1, y1-1));
            }
        }
        
    }
    
    public static void update(int r, int c, long v) {
        for(int i=r; i<=N; i+=(i & -i)) {
            for(int j=c; j<=N; j+=(j & -j)) {
                tree[i][j] += v;
            }
        }
    }
    
    public static long sum(int r, int c) {
        long res = 0;
        for(int i=r; i>0; i-=(i & -i)) {
            for(int j=c; j>0; j-=(j & -j)) {
                res += tree[i][j];
            }
        }
        return res;
    }
}