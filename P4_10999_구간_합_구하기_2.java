import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M, K;
    static long[] tree;
    static long[] lazy;
    static long[] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        tree = new long[N * 4 + 4];
        lazy = new long[N * 4 + 4];
        arr = new long[N + 1];
        
        for(int i=1; i<=N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        init(1, N, 1);
        
        for(int i=0; i<M + K; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            
            if(a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                
                update(1, N, b, c, d, 1);
                
            }
            else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                System.out.println(sum(1, N, b, c, 1));
            }
        }
        
        
    }
    
    public static void init(int start, int end, int idx) {
        if(start == end) {
            tree[idx] = arr[start];
            return;
        }
        
        int mid = (start + end) / 2;
        init(start, mid, idx * 2);
        init(mid + 1, end, idx * 2 + 1);
        
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }
    
    public static long sum(int start, int end, int left, int right, int idx) {
        propagate(idx, start, end);

        if(left > end || right < start) {
            return 0;
        }
        if(left <= start && end <= right) {
            return tree[idx];
        }
        
        int mid = (start + end) / 2;
        return sum(start, mid, left, right, idx * 2) + sum(mid + 1, end, left, right, idx * 2 + 1);
    }
    
    public static void propagate(int idx, int start, int end) {
        if(lazy[idx] != 0) {
            tree[idx] += (end - start + 1) * lazy[idx];
            
            if(start != end) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    
    public static void update(int start, int end, int left, int right, long val, int idx) {
        propagate(idx, start, end);
        
        if(right < start || left > end) {
            return;
        }
        
        if(left <= start && end <= right) {
            lazy[idx] += val;
            propagate(idx, start, end);
            return;
        }
        
        int mid = (start + end) / 2;
        
        update(start, mid, left, right, val, idx * 2);
        update(mid + 1, end, left, right, val, idx * 2 + 1);
        
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }
}