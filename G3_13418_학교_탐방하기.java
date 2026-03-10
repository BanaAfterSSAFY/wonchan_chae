import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int s;
        int e;
        int v;
        
        Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
    
    static int T, N, M;
    static List<Node>[] list;
    static boolean[] check;
    static int[] par;
    static int min, max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        check = new boolean[N+1];
        par = new int[N+1];
        for(int i=0; i<=N; i++) {
            par[i] = i;
        }
        
        PriorityQueue<Node> uq = new PriorityQueue<>((o1, o2) -> {
            return o2.v - o1.v;
        });
        
        PriorityQueue<Node> dq = new PriorityQueue<>((o1, o2) -> {
            return o1.v - o2.v;
        });
        
        for(int i=0; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(i == 0) {
                if(c == 0) {
                    min = max = 1;
                }
                merge(0, 1);
                continue;
            }
            
            uq.offer(new Node(a, b, c));
            dq.offer(new Node(a, b, c));
        }
        
        
        
        int cnt = 1;
        while(cnt < N) {
            Node cur = uq.poll();
            
            if(find(cur.s) == find(cur.e)) {
                continue;
            }
            
            merge(cur.s, cur.e);
            cnt++;
            if(cur.v == 0) {
                min++;
            }
        }
        
        for(int i=0; i<=N; i++) {
            par[i] = i;
        }
        merge(0, 1);
        
        cnt = 1;
        while(cnt < N) {
            Node cur = dq.poll();
            
            if(find(cur.s) == find(cur.e)) {
                continue;
            }
            
            merge(cur.s, cur.e);
            cnt++;
            if(cur.v == 0) {
                max++;
            }
        }
        
        
        System.out.println(max * max - min * min);
    }
    
    static int find(int a) {
        if(par[a] == a) {
            return a;
        }
        return par[a] = find(par[a]);
    }
    
    static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        
        par[b] = a;
    }
}