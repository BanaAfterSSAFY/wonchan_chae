import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        double dist;
        int start;
        int end;
        
        Node(double dist, int start, int end) {
            this.dist = dist;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.dist - o.dist > 0)
                return 1;
            else if(this.dist - o.dist < 0)
                return -1;
            return 0;
        }
    }
    
    static int N, M;
    static int[][] arr;
    static int[] check;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1][2];
        check = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            arr[i][0] = a;
            arr[i][1] = b;
            check[i] = i;
        }
        int cnt = M;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            union(a, b);
        }
        
        List<Node> list = new ArrayList<>();
        
        for(int i=1; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                
                double d = Math.sqrt(Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2));

                list.add(new Node(d, i, j));
            }
        }
        
        Collections.sort(list);
        
        double ans = 0;
        for(Node cur : list) {

            int p = find(cur.start);
            int q = find(cur.end);
            
            if(p == q) {
                continue;
            }
            
            ans += cur.dist;
            union(cur.start, cur.end);
        }
        
        System.out.printf("%.2f\n", ans);
    }
    
    public static int find(int a) {
        if(check[a] == a) {
            return a;
        }
        return check[a] = find(check[a]);
    }
    
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a != b) {
            check[a] = b;
        }
    }
}