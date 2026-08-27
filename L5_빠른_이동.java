import java.util.*;

class Solution {

    static int n;
    static ArrayList<Integer>[] graph;
    static int[] order;
    static int[] low;
    static int[] scc;
    static boolean[] onStack;
    static int[] stack;
    static int stackSize;
    static int orderCount;
    static int sccCount;
    static BitSet[] reach;
    static int[] pairL;
    static int[] pairR;
    static int[] dist;

    public int solution(int n, int[][] roads) {
        this.n = n;

        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] road : roads) {
            graph[road[0] - 1].add(road[1] - 1);
        }

        order = new int[n];
        Arrays.fill(order, -1);

        low = new int[n];
        scc = new int[n];
        Arrays.fill(scc, -1);

        onStack = new boolean[n];
        stack = new int[n];

        for(int i = 0; i < n; i++) {
            if(order[i] == -1) {
                tarjan(i);
            }
        }

        BitSet[] dag = new BitSet[sccCount];

        for(int i = 0; i < sccCount; i++) {
            dag[i] = new BitSet(sccCount);
        }

        for(int[] road : roads) {
            int u = scc[road[0] - 1];
            int v = scc[road[1] - 1];

            if(u != v) {
                dag[u].set(v);
            }
        }

        int[] indegree = new int[sccCount];

        for(int u = 0; u < sccCount; u++) {
            for(int v = dag[u].nextSetBit(0); v >= 0; v = dag[u].nextSetBit(v + 1)) {
                indegree[v]++;
            }
        }

        int[] topo = new int[sccCount];
        int topoSize = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for(int i = 0; i < sccCount; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();
            topo[topoSize++] = u;

            for(int v = dag[u].nextSetBit(0); v >= 0; v = dag[u].nextSetBit(v + 1)) {
                if(--indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        reach = new BitSet[sccCount];

        for(int i = 0; i < sccCount; i++) {
            reach[i] = new BitSet(sccCount);
        }

        for(int i = sccCount - 1; i >= 0; i--) {
            int u = topo[i];

            for(int v = dag[u].nextSetBit(0); v >= 0; v = dag[u].nextSetBit(v + 1)) {
                reach[u].set(v);
                reach[u].or(reach[v]);
            }
        }

        pairL = new int[sccCount];
        pairR = new int[sccCount];
        dist = new int[sccCount];

        Arrays.fill(pairL, -1);
        Arrays.fill(pairR, -1);

        int matching = 0;

        while(bfs()) {
            for(int u = 0; u < sccCount; u++) {
                if(pairL[u] == -1 && dfsMatching(u)) {
                    matching++;
                }
            }
        }

        return sccCount - matching - 1;
    }

    static void tarjan(int u) {
        order[u] = low[u] = orderCount++;

        stack[stackSize++] = u;
        onStack[u] = true;

        for(int v : graph[u]) {
            if(order[v] == -1) {
                tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            }
            else if(onStack[v]) {
                low[u] = Math.min(low[u], order[v]);
            }
        }

        if(low[u] == order[u]) {
            while(true) {
                int v = stack[--stackSize];
                onStack[v] = false;
                scc[v] = sccCount;

                if(v == u) {
                    break;
                }
            }

            sccCount++;
        }
    }

    static boolean bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean found = false;

        for(int u = 0; u < sccCount; u++) {
            if(pairL[u] == -1) {
                dist[u] = 0;
                queue.offer(u);
            }
            else {
                dist[u] = -1;
            }
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();

            for(int v = reach[u].nextSetBit(0); v >= 0; v = reach[u].nextSetBit(v + 1)) {
                int next = pairR[v];

                if(next == -1) {
                    found = true;
                }
                else if(dist[next] == -1) {
                    dist[next] = dist[u] + 1;
                    queue.offer(next);
                }
            }
        }

        return found;
    }

    static boolean dfsMatching(int u) {
        for(int v = reach[u].nextSetBit(0); v >= 0; v = reach[u].nextSetBit(v + 1)) {
            int next = pairR[v];

            if(next == -1 || (dist[next] == dist[u] + 1 && dfsMatching(next))) {
                pairL[u] = v;
                pairR[v] = u;
                return true;
            }
        }

        dist[u] = -1;
        return false;
    }
}