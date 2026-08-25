import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int v;
        long dist;

        Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }

        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static int n, m;
    static int[] head, to, next;
    static long[] weight;
    static int cnt;

    static int[] U, V;
    static long[] L, W;

    public int[] solution(int n, int[][] roads) {
        this.n = n;
        m = roads.length;

        U = new int[m];
        V = new int[m];
        L = new long[m];
        W = new long[m];

        head = new int[n];
        Arrays.fill(head, -1);

        to = new int[m * 2];
        next = new int[m * 2];
        weight = new long[m * 2];

        for(int i = 0; i < m; i++) {
            int u = roads[i][0] - 1;
            int v = roads[i][1] - 1;
            long l = roads[i][2];
            long w = l + roads[i][3];

            U[i] = u;
            V[i] = v;
            L[i] = l;
            W[i] = w;

            addEdge(u, v, w);
            addEdge(v, u, w);
        }

        long[] fromStart = dijkstra(0);
        long[] fromEnd = dijkstra(n - 1);

        long shortest = fromStart[n - 1];

        long[] temp = new long[n];
        int size = 0;

        for(int i = 0; i < n; i++) {
            if(fromStart[i] + fromEnd[i] == shortest) {
                temp[size++] = fromStart[i];
            }
        }

        Arrays.sort(temp, 0, size);

        long[] coordinates = new long[size];
        int coordinateCount = 0;

        for(int i = 0; i < size; i++) {
            if(coordinateCount == 0 || coordinates[coordinateCount - 1] != temp[i]) {
                coordinates[coordinateCount++] = temp[i];
            }
        }

        coordinates = Arrays.copyOf(coordinates, coordinateCount);

        int[] left = new int[m];
        int[] right = new int[m];

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        int[] diff = new int[coordinateCount + 1];

        for(int i = 0; i < m; i++) {
            int u = U[i];
            int v = V[i];
            long w = W[i];

            if(fromStart[u] + w + fromEnd[v] == shortest) {
                int l = Arrays.binarySearch(coordinates, fromStart[u]);
                int r = Arrays.binarySearch(coordinates, fromStart[v]);

                left[i] = l;
                right[i] = r;

                diff[l]++;
                diff[r]--;
            }
            else if(fromStart[v] + w + fromEnd[u] == shortest) {
                int l = Arrays.binarySearch(coordinates, fromStart[v]);
                int r = Arrays.binarySearch(coordinates, fromStart[u]);

                left[i] = l;
                right[i] = r;

                diff[l]++;
                diff[r]--;
            }
        }

        int[] onePrefix = new int[coordinateCount];

        int active = 0;

        for(int i = 0; i < coordinateCount - 1; i++) {
            active += diff[i];

            onePrefix[i + 1] = onePrefix[i];

            if(active == 1) {
                onePrefix[i + 1]++;
            }
        }

        int[] result = new int[m];
        int resultCount = 0;

        for(int i = 0; i < m; i++) {
            boolean possible = false;

            if(left[i] != -1) {
                if(onePrefix[right[i]] - onePrefix[left[i]] > 0) {
                    possible = true;
                }
            }

            long candidate1 = fromStart[U[i]] + L[i] + fromEnd[V[i]];
            long candidate2 = fromStart[V[i]] + L[i] + fromEnd[U[i]];

            if(Math.min(candidate1, candidate2) < shortest) {
                possible = true;
            }

            if(possible) {
                result[resultCount++] = i + 1;
            }
        }

        if(resultCount == 0) {
            return new int[]{-1};
        }

        return Arrays.copyOf(result, resultCount);
    }

    static void addEdge(int u, int v, long w) {
        to[cnt] = v;
        weight[cnt] = w;
        next[cnt] = head[u];
        head[u] = cnt++;
    }

    static long[] dijkstra(int start) {
        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.dist != dist[cur.v]) {
                continue;
            }

            for(int e = head[cur.v]; e != -1; e = next[e]) {
                int nv = to[e];
                long nd = cur.dist + weight[e];

                if(nd < dist[nv]) {
                    dist[nv] = nd;
                    pq.offer(new Node(nv, nd));
                }
            }
        }

        return dist;
    }
}