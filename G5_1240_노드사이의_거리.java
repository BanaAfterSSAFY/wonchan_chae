import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int end;
    int dist;
    
    public Node(int e, int d) {
        end = e;
        dist = d;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }
}

public class Main {
    
    static int N, M;
    static List<Node>[] arr;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] inp;
        
        inp = br.readLine().split(" ");
        
        N = Integer.parseInt(inp[0]);
        M = Integer.parseInt(inp[1]);
        
        arr = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++) {
            inp = br.readLine().split(" ");
            int a = Integer.parseInt(inp[0]);
            int b = Integer.parseInt(inp[1]);
            int d = Integer.parseInt(inp[2]);
            
            arr[a].add(new Node(b, d));
            arr[b].add(new Node(a, d));
        }
        
        for(int i=0; i<M; i++) {
            inp = br.readLine().split(" ");
            int a = Integer.parseInt(inp[0]);
            int b = Integer.parseInt(inp[1]);
            System.out.println(solve(a, b));
        }
    }
    
    public static int solve(int s, int e) {
        int[] dis = new int[N+1];
        Arrays.fill(dis, 1000000000);
        dis[s] = 0;
        
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.offer(new Node(s, 0));
        
        while(PQ.isEmpty() == false) {
            Node cur = PQ.poll();
            
            if(dis[cur.end] < cur.dist) continue;
            
            for(Node n : arr[cur.end]) {
                if(dis[n.end] > dis[cur.end] + n.dist) {
                    dis[n.end] = dis[cur.end] + n.dist;
                    PQ.add(new Node(n.end, cur.dist + n.dist));
                }
            }
        }
        return dis[e];
    }
}