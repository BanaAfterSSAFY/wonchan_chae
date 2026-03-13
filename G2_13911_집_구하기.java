import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int s; 
        int e;
        int d;
        
        Node(int s, int e, int d){
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }
    
    static int T, N, M;
    static List<Node>[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = new ArrayList<>();
        }
                
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            arr[a].add(new Node(a, b, c));
            arr[b].add(new Node(b, a, c));
        }
        
        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
        int[] burger = new int[N+1];
        for(int j=1; j<=N; j++) {
            burger[j] = 1234567890;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.d - o2.d;
        });
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<D; i++) {
            int inp = Integer.parseInt(st.nextToken());
            burger[inp] = 0;
            pq.offer(new Node(0, inp, 0));
        }
                
        while(pq.isEmpty() == false) {
            Node cur = pq.poll();
            
            if(burger[cur.e] < cur.d) {
                continue;
            }
            
            for(Node at : arr[cur.e]) {
                if(burger[at.e] > burger[cur.e] + at.d) {
                    burger[at.e] = burger[cur.e] + at.d;
                    pq.offer(new Node(cur.e, at.e, burger[at.e]));
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
        int[] coffee = new int[N+1];
        for(int j=1; j<=N; j++) {
            coffee[j] = 1234567890;
        }
        pq.clear();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<S; i++) {
            int inp = Integer.parseInt(st.nextToken());
            coffee[inp] = 0;
            pq.offer(new Node(0, inp, 0));
        }
        
        while(pq.isEmpty() == false) {
            Node cur = pq.poll();
            
            if(coffee[cur.e] < cur.d) {
                continue;
            }
            
            for(Node at : arr[cur.e]) {
                if(coffee[at.e] > coffee[cur.e] + at.d) {
                    coffee[at.e] = coffee[cur.e] + at.d;
                    pq.offer(new Node(cur.e, at.e, coffee[at.e]));
                }
            }
        }
        
        int Ans = Integer.MAX_VALUE;
        
        for(int i=1; i<=N; i++) {
            if(burger[i] == 0 || burger[i] == 1234567890 || coffee[i] == 0 || coffee[i] == 1234567890) {
                continue;
            }
            
            if(burger[i] > X || coffee[i] > Y) {
                continue;
            }
            
            Ans = Math.min(burger[i] + coffee[i], Ans);
        }
        
        
        if(Ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(Ans);
        }
    }
}