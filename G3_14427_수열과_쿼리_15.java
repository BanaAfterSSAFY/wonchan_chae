import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static long[] arr;

    static int[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new int[4 * N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        init(1, N, 1);
        
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            
            if (cmd == 1) {
                int t = Integer.parseInt(st.nextToken());
                long q = Long.parseLong(st.nextToken());
                arr[t] = q;
                update(1, N, 1, t);
            } else {
                sb.append(tree[1]).append("\n");
            }
        }
        System.out.print(sb);
    }
    
    public static int getMinIdx(int idx1, int idx2) {
        if (idx1 == -1) return idx2;
        if (idx2 == -1) return idx1;
        if (arr[idx1] <= arr[idx2]) return idx1;
        return idx2;
    }

    public static void init(int start, int end, int node) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
        tree[node] = getMinIdx(tree[node * 2], tree[node * 2 + 1]);
    }
    
    public static void update(int start, int end, int node, int target) {
        if (target < start || target > end) return;
        
        if (start == end) {
            tree[node] = target;
            return;
        }
        
        int mid = (start + end) / 2;
        update(start, mid, node * 2, target);
        update(mid + 1, end, node * 2 + 1, target);
        tree[node] = getMinIdx(tree[node * 2], tree[node * 2 + 1]);
    }
}