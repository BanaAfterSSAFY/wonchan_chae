import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int s;
        int e;
        
        Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    
    static int N, M;
    static long[] arr;
    static int[] tree;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while(true) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            
            if(N == 0) {
                break;
            }
            
            arr = new long[N + 1];
            tree = new int[N * 4 + 4];
            
            for(int i=1; i<=N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            
            init(1, N, 1);
            
            System.out.println(solve(1, N));
        }
        
    }
    
    public static long solve(int start, int end) {
        long ret = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(start, end));

        while(stack.isEmpty() == false) {
            Node cur = stack.pop();
            int s = cur.s;
            int e = cur.e;

            if(s > e) continue;

            int min = query(1, N, s, e, 1);

            long tmp = (long) (e - s + 1) * arr[min];
            ret = Math.max(ret, tmp);

            if(s < min) {
                stack.push(new Node(s, min - 1));
            }

            if(e > min) {
                stack.push(new Node(min + 1, e));
            }
        }
        return ret;
        
    }
    
    public static void init(int start, int end, int idx) {
        if(start == end) {
            tree[idx] = start;
            return;
        }
        
        int mid = (start + end) / 2;
        init(start, mid, idx * 2);
        init(mid + 1, end, idx * 2 + 1);
        
        tree[idx] = arr[tree[idx * 2]] <= arr[tree[idx * 2 + 1]] ? tree[idx * 2] : tree[idx * 2 + 1];
    }
    
    public static int query(int start, int end, int left, int right, int idx) {
        if(right < start || left > end) {
            return -1;
        }
        if(left <= start && end <= right) {
            return tree[idx];
        }
        
        int mid = (start + end) / 2;
        
        int a = query(start, mid, left, right, idx * 2);
        int b = query(mid + 1, end, left, right, idx * 2 + 1);
        
        if(a == -1) return b;
        if(b == -1) return a;
        
        return arr[a] <= arr[b] ? a : b;
    }
}