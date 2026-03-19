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
    static List<Node>[] map;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
            
        T = Integer.parseInt(st.nextToken());
        
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            List<Node> list = new ArrayList<>();
            map = new ArrayList[N];
            for(int i=0; i<N; i++) {
                map[i] = new ArrayList<>();
            }
            
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                list.add(new Node(a, b, c));
                map[a].add(new Node(a, b, c));
            }
            
            int[] dist = new int[N];
            Arrays.fill(dist, 0);
            
            for(int i=1; i<N; i++) {
                for(Node at : list) {
                    if(dist[at.e] > dist[at.s] + at.d) {
                        dist[at.e]= dist[at.s]+ at.d;  
                    }
                }
            }
            
            boolean ans = false;
            for(Node at : list) {
                if(dist[at.e] > dist[at.s] + at.d) {
                    if(check(at.e) == true) {
                        ans = true;
                        break;
                    }
                }
            }
            
            if(ans == false) {
                System.out.printf("Case #%d: not possible\n", t);
            }
            else {
                System.out.printf("Case #%d: possible\n", t);
            }
        }
    }
    
    public static boolean check(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        boolean[] visit = new boolean[N];
        visit[s] = true;
        
        while(q.isEmpty() == false) {
            int cur = q.poll();
            
            if(cur == 0) {
                return true;
            }
            
            for(Node at : map[cur]) {
                if(visit[at.e] == true) {
                    continue;
                }
                
                visit[at.e] = true;
                q.offer(at.e);
            }
        }
        
        return false;
    }
    
}