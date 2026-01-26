import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int cost;
    int cur;
    
    public Node(int cost, int cur) {
        this.cost = cost;
        this.cur = cur;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.cost != o.cost)
            return this.cost - o.cost;
        else
            return this.cur - o.cur;
    }
}

public class Main {
    
    static int N, M, L, S, G, H;
    static List<Node>[] arr;
    static int[] X;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        String[] inp;
        
        for(int t=0; t<T; t++) {
            inp = br.readLine().split(" ");
            
            N = Integer.parseInt(inp[0]);
            M = Integer.parseInt(inp[1]);
            L = Integer.parseInt(inp[2]);
            
            X = new int[L];
            arr = new ArrayList[N + 1];
            for(int i=1; i<=N; i++){
                arr[i] = new ArrayList<>();
            }
            
            inp = br.readLine().split(" ");
            
            S = Integer.parseInt(inp[0]);
            G = Integer.parseInt(inp[1]);
            H = Integer.parseInt(inp[2]);

            int W = 0;

            for(int i=0; i<M; i++) {
                inp = br.readLine().split(" ");
                
                int a = Integer.parseInt(inp[0]);
                int b = Integer.parseInt(inp[1]);
                int d = Integer.parseInt(inp[2]);
                
                arr[a].add(new Node(d, b));
                arr[b].add(new Node(d, a));

                if((a == G && b == H) || (a == H && b == G)){
                    W = d;
                }
            }
            
            for(int i=0; i<L; i++){
                X[i] = Integer.parseInt(br.readLine());
            }
           
            int[] distS = solve(S);
            int[] distG = solve(G);
            int[] distH = solve(H);

            List<Integer> Ans = new ArrayList<>();

            for(int x : X){
                int pathA = distS[G] + W + distH[x];
                int pathB = distS[H] + W + distG[x];

                if(pathA == distS[x] || pathB == distS[x]) {
                    Ans.add(x);
                }
            }

            Collections.sort(Ans);

            for(int ans : Ans){
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }
    
    public static int[] solve(int s) {
        int[] dist = new int[N+1];
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.offer(new Node(0, s));
        
        Arrays.fill(dist, 1000000000);
        dist[s] = 0;

        while(PQ.isEmpty() == false) {
            Node tmp = PQ.poll();
            
            if(dist[tmp.cur] < tmp.cost) continue;

            for(Node n : arr[tmp.cur]) {
                if(dist[n.cur] > dist[tmp.cur] + n.cost){
                    dist[n.cur] = dist[tmp.cur] + n.cost;
                    PQ.offer(new Node(dist[n.cur], n.cur));
                }
            }
        }
        return dist;
    }
}