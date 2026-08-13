import java.util.*;

class Solution {

    static final long mask = (1L << 20) - 1;
    int[][] roads;
    HashMap<Long, Integer> map;

    int[] dx;
    int[] dy;
    int[] capacity;
    int cnt;

    LList[] arr;

    public int[] solution(int[][] city, int[][] road) {

        int N = city.length;
        int M = road.length;

        this.roads = road;

        int maxi = 3 * M + N + M * (M - 1) / 2 + 10;

        dx = new int[maxi];
        dy = new int[maxi];
        capacity = new int[maxi];

        map = new HashMap<>();
        arr = new LList[M];

        for(int i = 0; i < M; i++) {
            arr[i] = new LList();
        }

        for(int i = 0; i < M; i++) {

            int[] r = road[i];

            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];
            int limit = r[4];

            int start = get(x1, y1);
            int end = get(x2, y2);

            add(i, start);
            add(i, end);

            int nx = (x1 + x2) / 2;
            int ny = (y1 + y2) / 2;

            int camera = get(nx, ny);

            capacity[camera] = Math.min(capacity[camera], limit);

            add(i, camera);
        }

        int[] nodes = new int[N];

        for(int i = 0; i < N; i++) {
            nodes[i] = get(city[i][0], city[i][1]);
        }

        for(int c = 0; c < N; c++) {

            int x = city[c][0];
            int y = city[c][1];

            for(int i = 0; i < M; i++) {
                if(contains(road[i], x, y)) {
                    add(i, nodes[c]);
                }
            }
        }

        for(int i = 0; i < M; i++) {

            for(int j = i + 1; j < M; j++) {

                long p = cross(road[i], road[j]);

                if(p == Long.MIN_VALUE) {
                    continue;
                }

                int x = (int) (p >> 32);
                int y = (int) p;

                int node = get(x, y);

                add(i, node);
                add(j, node);
            }
        }

        LList edges = new LList();

        for(int i = 0; i < M; i++) {

            LList list = arr[i];
            list.sort();

            long prevT = Long.MIN_VALUE;
            int prevN = -1;

            for(int j = 0; j < list.size; j++) {

                long token = list.data[j];

                if(token == prevT) {
                    continue;
                }

                int node = (int) (token & mask);

                if(prevN != -1) {

                    int w = Math.min(capacity[prevN], capacity[node]);

                    edges.add(change(w, prevN, node));
                }

                prevT = token;
                prevN = node;
            }
        }

        edges.sort();

        Node dsu = new Node(cnt);

        int[] answer = new int[N - 1];
        boolean[] solved = new boolean[N];

        int res = N - 1;
        int idx = 0;

        while(idx < edges.size && res > 0) {

            long tmp = edges.data[idx];

            int d = (int) (tmp >>> 40);
            int w = 1000001 - d;

            int next = idx;

            while(next < edges.size) {

                long edge = edges.data[next];
                int cur = (int) (edge >>> 40);

                if(cur != d) {
                    break;
                }

                int u = (int) ((edge >>> 20) & mask);
                int v = (int) (edge & mask);

                dsu.union(u, v);

                next++;
            }

            int root = dsu.find(nodes[0]);

            for(int i = 1; i < N; i++) {

                if(solved[i]) {
                    continue;
                }

                if(dsu.find(nodes[i]) == root) {
                    answer[i - 1] = (w == 1000001 ? 0 : w);
                    solved[i] = true;
                    res--;
                }
            }

            idx = next;
        }

        return answer;
    }

    int get(int x, int y) {

        long k = wrap(x, y);

        Integer ter = map.get(k);

        if(ter != null) {
            return ter;
        }

        int nter = cnt++;

        map.put(k, nter);

        dx[nter] = x;
        dy[nter] = y;

        capacity[nter] = 1000001;

        return nter;
    }

    void add(int idx, int node) {

        int[] r = roads[idx];
        long coordinate;

        if(r[1] == r[3]) {
            coordinate = dx[node];
        }
        else {
            coordinate = dy[node];
        }

        long token = ((coordinate + 1000000000) << 20) | node;
        arr[idx].add(token);
    }

    boolean contains(int[] r, int x, int y) {
        return r[0] <= x && x <= r[2] && r[1] <= y && y <= r[3];
    }

    long cross(int[] a, int[] b) {
        boolean ah = a[1] == a[3];
        boolean bh = b[1] == b[3];

        if(ah == true && bh == false) {
            int x = b[0];
            int y = a[1];

            if(a[0] <= x && x <= a[2] && b[1] <= y && y <= b[3]) {
                return wrap(x, y);
            }
        }
        else if(ah == false && bh == true) {
            int x = a[0];
            int y = b[1];

            if(b[0] <= x && x <= b[2] && a[1] <= y && y <= a[3]) {
                return wrap(x, y);
            }
        }
        else if(ah == true) {

            if(a[1] != b[1]) {
                return Long.MIN_VALUE;
            }

            int l = Math.max(a[0], b[0]);
            int r = Math.min(a[2], b[2]);

            if(l == r) {
                return wrap(l, a[1]);
            }
        }
        else {

            if(a[0] != b[0]) {
                return Long.MIN_VALUE;
            }

            int v = Math.max(a[1], b[1]);
            int t = Math.min(a[3], b[3]);

            if(v == t) {
                return wrap(a[0], v);
            }
        }

        return Long.MIN_VALUE;
    }

    long wrap(int x, int y) {
        return ((long) x << 32) ^ (y & 0xffffffffL);
    }

    long change(int w, int u, int v) {
        long d = 1000001 - w;

        return (d << 40) | ((long) u << 20) | v;
    }

    static class Node {

        int[] parent;
        int[] size;

        Node(int N) {

            parent = new int[N];
            size = new int[N];

            for(int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if(parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);

            if(a == b) {
                return;
            }

            if(size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            parent[b] = a;
            size[a] += size[b];
        }
    }

    static class LList {
        long[] data = new long[4];
        int size = 0;

        void add(long value) {
            if(size == data.length) {
                data = Arrays.copyOf(data, data.length * 2);
            }

            data[size++] = value;
        }

        void sort() {
            Arrays.sort(data, 0, size);
        }
    }
}