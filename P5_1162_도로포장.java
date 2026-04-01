import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int e;
        long w;
        int cnt;

        public Node(int e, long w, int cnt) {
            this.e = e;
            this.w = w;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.w - o.w);
        }
    }

    static int N;
    static int M;
    static int K;
    static long[][] dist;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, w, 0));
            list[b].add(new Node(a, w, 0));
        }

        dist = new long[N + 1][K + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        dist[1][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));

        while(pq.isEmpty() == false) {
            Node now = pq.poll();
            if(now.w > dist[now.e][now.cnt]){
                continue;
            }
            for(Node at : list[now.e]) {
                if(now.cnt < K && dist[at.e][now.cnt + 1] > dist[now.e][now.cnt]) {
                    dist[at.e][now.cnt + 1] = dist[now.e][now.cnt];
                    pq.add(new Node(at.e, dist[at.e][now.cnt + 1], now.cnt + 1));
                }
                if(dist[at.e][now.cnt] > dist[now.e][now.cnt] + at.w) {
                    dist[at.e][now.cnt] = dist[now.e][now.cnt] + at.w;
                    pq.add(new Node(at.e, dist[at.e][now.cnt], now.cnt));
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for(int i = 0; i <= K; i++) {
            ans = Math.min(dist[N][i], ans);
        }
        System.out.println(ans);
    }
}