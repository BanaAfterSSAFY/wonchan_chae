import java.util.*;

class Solution {

    static List<Integer>[] tree1;
    static List<Integer>[] tree2;
    static List<Integer>[] child1;
    static List<Integer>[] child2;

    static int[][] dp;

    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        tree1 = new ArrayList[n1];
        tree2 = new ArrayList[n2];

        child1 = new ArrayList[n1];
        child2 = new ArrayList[n2];

        for(int i = 0; i < n1; i++) {
            tree1[i] = new ArrayList<>();
            child1[i] = new ArrayList<>();
        }

        for(int i = 0; i < n2; i++) {
            tree2[i] = new ArrayList<>();
            child2[i] = new ArrayList<>();
        }

        for(int[] edge : g1) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;

            tree1[a].add(b);
            tree1[b].add(a);
        }

        for(int[] edge : g2) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;

            tree2[a].add(b);
            tree2[b].add(a);
        }

        int[] order1 = solve(tree1, child1, n1);
        int[] order2 = solve(tree2, child2, n2);

        dp = new int[n1][n2];

        for(int i = n1 - 1; i >= 0; i--) {
            int u = order1[i];

            for(int j = n2 - 1; j >= 0; j--) {
                int v = order2[j];

                dp[u][v] = 1 + matching(child1[u], child2[v]);
            }
        }

        return dp[0][0];
    }

    static int[] solve(List<Integer>[] tree, List<Integer>[] child, int n) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];
        int size = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        parent[0] = 0;
        queue.offer(0);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            order[size++] = cur;

            for(int next : tree[cur]) {
                if(parent[next] != -1) {
                    continue;
                }

                parent[next] = cur;
                child[cur].add(next);
                queue.offer(next);
            }
        }

        return order;
    }

    static int matching(List<Integer> a, List<Integer> b) {
        if(a.isEmpty() || b.isEmpty()) {
            return 0;
        }

        if(a.size() > b.size()) {
            return metric(b, a, false);
        }

        return metric(a, b, true);
    }

    static int metric(List<Integer> rows, List<Integer> cols, boolean normal) {
        int n = rows.size();
        int m = cols.size();

        int[] u = new int[n + 1];
        int[] v = new int[m + 1];
        int[] p = new int[m + 1];
        int[] way = new int[m + 1];

        for(int i = 1; i <= n; i++) {
            p[0] = i;

            int j0 = 0;

            int[] minv = new int[m + 1];
            boolean[] used = new boolean[m + 1];

            Arrays.fill(minv, Integer.MAX_VALUE);

            do {
                used[j0] = true;

                int i0 = p[j0];
                int delta = Integer.MAX_VALUE;
                int j1 = 0;

                for(int j = 1; j <= m; j++) {
                    if(used[j]) {
                        continue;
                    }

                    int x = rows.get(i0 - 1);
                    int y = cols.get(j - 1);

                    int weight = normal ? dp[x][y] : dp[y][x];

                    int cur = -weight - u[i0] - v[j];

                    if(cur < minv[j]) {
                        minv[j] = cur;
                        way[j] = j0;
                    }

                    if(minv[j] < delta) {
                        delta = minv[j];
                        j1 = j;
                    }
                }

                for(int j = 0; j <= m; j++) {
                    if(used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    }
                    else {
                        minv[j] -= delta;
                    }
                }

                j0 = j1;

            } while(p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while(j0 != 0);
        }

        int result = 0;

        for(int j = 1; j <= m; j++) {
            if(p[j] == 0) {
                continue;
            }

            int x = rows.get(p[j] - 1);
            int y = cols.get(j - 1);

            result += normal ? dp[x][y] : dp[y][x];
        }

        return result;
    }
}