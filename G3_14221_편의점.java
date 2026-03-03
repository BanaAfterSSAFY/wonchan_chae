import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int s;
        int e;
        int v;
        
        Node(int s, int e, int v){
            this.s = s;
            this.e = e;
            this.v = v;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
    
    static int N, M;
    static List<Node>[] list;
    static int[] house;
    static int ans, max = Integer.MAX_VALUE;
    static PriorityQueue<Node> q = new PriorityQueue<>();;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        
        //PriorityQueue<Node> q = new PriorityQueue<>();
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(a, b, c));
            list[b].add(new Node(b, a, c));
            //q.offer(new Node(a, b, c));
        }
        
        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        house = new int[P];
        int[] dist = new int[N+1];
        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<P; i++) {
            house[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            int cur = Integer.parseInt(st.nextToken());
            q.offer(new Node(0, cur, 0));
            dist[cur] = 0;
        }
                
        while(q.isEmpty() == false) {
            Node now = q.poll();
            
            if(dist[now.e] < now.v) {
                continue;
            }
            
            for(Node at : list[now.e]) {
                if(dist[at.e] > dist[now.e] + at.v) {
                    dist[at.e] = dist[now.e] + at.v;
                    q.offer(new Node(now.e, at.e, dist[at.e]));
                }
            }
            
        }
        
        for(int at : house) {
            if(dist[at] < max) {
                max = dist[at];
                ans = at;
            }
            else if(dist[at] == max) {
                if(at < ans) {
                    ans = at;
                }
            }
        }
        
        System.out.println(ans);
    }
    
}