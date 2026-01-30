import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int end;
    int d;
    public Node(int end, int d) {
        this.end = end;
        this.d = d;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.d - o.d;
    }
}

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Node>> arr = new ArrayList<>();
    static int[] brr = new int[50001];
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++) {
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr.get(a).add(new Node(b, c));
            arr.get(b).add(new Node(a, c));
        }

        solve(1);

        System.out.println(brr[N]);
    }

    public static void solve(int s) {
        Arrays.fill(brr, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        brr[s] = 0;

        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int end = temp.end;
            int dis = temp.d; 

            if(brr[end] < dis) {
                continue;
            }
            for(int i=0; i<arr.get(end).size(); i++) { 
                int cost = brr[end] + arr.get(end).get(i).d;

                if(cost < brr[arr.get(end).get(i).end]) {
                    brr[arr.get(end).get(i).end] = cost;
                    pq.offer(new Node(arr.get(end).get(i).end, cost));
                }
            }
        }
    }

}