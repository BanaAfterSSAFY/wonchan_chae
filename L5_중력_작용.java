import java.util.*;

class Solution {

    static int n;

    static int[] head;
    static int[] to;
    static int[] next;
    static int edges;
    static int[] parent;
    static int[] subtreeSize;
    static int[] heavy;
    static int[] chainHead;
    static int[] position;
    static int[] left;
    static int[] right;
    static int[] size;
    static int[] priority;
    static long[] value;
    static long[] sum;
    static int root;
    static int splitLeft;
    static int splitRight;

    static long seed = 0x9E3779B97F4A7C15L;

    public long[] solution(int[] values, int[][] edges, int[][] queries) {
        n = values.length;

        head = new int[n];
        Arrays.fill(head, -1);

        to = new int[Math.max(0, 2 * (n - 1))];
        next = new int[Math.max(0, 2 * (n - 1))];

        for(int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            addEdge(u, v);
            addEdge(v, u);
        }

        parent = new int[n];
        Arrays.fill(parent, -2);

        subtreeSize = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);

        chainHead = new int[n];
        position = new int[n];

        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int orderSize = 0;

        parent[0] = -1;
        stack[top++] = 0;

        while(top > 0) {
            int cur = stack[--top];

            order[orderSize++] = cur;

            for(int e = head[cur]; e != -1; e = next[e]) {
                int nv = to[e];

                if(parent[nv] != -2) {
                    continue;
                }

                parent[nv] = cur;
                stack[top++] = nv;
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            int cur = order[i];

            subtreeSize[cur] = 1;

            int maxSize = 0;

            for(int e = head[cur]; e != -1; e = next[e]) {
                int nv = to[e];

                if(parent[nv] != cur) {
                    continue;
                }

                subtreeSize[cur] += subtreeSize[nv];

                if(subtreeSize[nv] > maxSize) {
                    maxSize = subtreeSize[nv];
                    heavy[cur] = nv;
                }
            }
        }

        top = 0;
        int index = 0;

        chainHead[0] = 0;
        stack[top++] = 0;

        while(top > 0) {
            int cur = stack[--top];

            position[cur] = index++;

            int h = heavy[cur];

            for(int e = head[cur]; e != -1; e = next[e]) {
                int nv = to[e];

                if(parent[nv] != cur || nv == h) {
                    continue;
                }

                chainHead[nv] = nv;
                stack[top++] = nv;
            }

            if(h != -1) {
                chainHead[h] = chainHead[cur];
                stack[top++] = h;
            }
        }

        long[] base = new long[n];

        for(int i = 0; i < n; i++) {
            base[position[i]] = values[i];
        }

        left = new int[n + 1];
        right = new int[n + 1];
        size = new int[n + 1];
        priority = new int[n + 1];

        value = new long[n + 1];
        sum = new long[n + 1];

        root = 0;

        for(int i = 0; i < n; i++) {
            int node = i + 1;

            size[node] = 1;
            priority[node] = nextPriority();
            value[node] = base[i];
            sum[node] = base[i];

            root = merge(root, node);
        }

        int answerCount = 0;

        for(int[] query : queries) {
            if(query[1] == -1) {
                answerCount++;
            }
        }

        long[] answer = new long[answerCount];
        int answerIndex = 0;

        for(int[] query : queries) {
            int u = query[0] - 1;
            int w = query[1];

            if(w == -1) {
                int l = position[u];
                int r = l + subtreeSize[u] - 1;

                answer[answerIndex++] = rangeSum(l, r);
            }
            else {
                updatePath(u, w);
            }
        }

        return answer;
    }

    static void updatePath(int u, long w) {
        while(chainHead[u] != chainHead[0]) {
            int h = chainHead[u];
            int p = parent[h];

            long incoming = getValue(position[p]);

            shift(position[h], position[u], incoming);

            u = p;
        }

        shift(position[0], position[u], w);
    }

    static void shift(int l, int r, long incoming) {
        split(root, r + 1);

        int a = splitLeft;
        int d = splitRight;

        split(a, l);

        int before = splitLeft;
        int middle = splitRight;

        int len = r - l + 1;

        split(middle, len - 1);

        int remain = splitLeft;
        int last = splitRight;

        value[last] = incoming;
        pull(last);

        middle = merge(last, remain);

        root = merge(merge(before, middle), d);
    }

    static long getValue(int index) {
        int cur = root;
        int target = index;

        while(cur != 0) {
            int leftSize = size[left[cur]];

            if(target < leftSize) {
                cur = left[cur];
            }
            else if(target == leftSize) {
                return value[cur];
            }
            else {
                target -= leftSize + 1;
                cur = right[cur];
            }
        }

        return 0;
    }

    static long rangeSum(int l, int r) {
        return prefixSum(r + 1) - prefixSum(l);
    }

    static long prefixSum(int count) {
        long result = 0;

        int cur = root;
        int remain = count;

        while(cur != 0 && remain > 0) {
            int leftSize = size[left[cur]];

            if(remain <= leftSize) {
                cur = left[cur];
            }
            else {
                result += sum[left[cur]];
                result += value[cur];

                remain -= leftSize + 1;

                if(remain == 0) {
                    break;
                }

                cur = right[cur];
            }
        }

        return result;
    }

    static void split(int node, int count) {
        if(node == 0) {
            splitLeft = 0;
            splitRight = 0;
            return;
        }

        int leftSize = size[left[node]];

        if(count <= leftSize) {
            split(left[node], count);

            int a = splitLeft;
            int b = splitRight;

            left[node] = b;
            pull(node);

            splitLeft = a;
            splitRight = node;
        }
        else {
            split(right[node], count - leftSize - 1);

            int a = splitLeft;
            int b = splitRight;

            right[node] = a;
            pull(node);

            splitLeft = node;
            splitRight = b;
        }
    }

    static int merge(int a, int b) {
        if(a == 0) {
            return b;
        }

        if(b == 0) {
            return a;
        }

        if(Integer.compareUnsigned(priority[a], priority[b]) > 0) {
            right[a] = merge(right[a], b);
            pull(a);

            return a;
        }

        left[b] = merge(a, left[b]);
        pull(b);

        return b;
    }

    static void pull(int node) {
        if(node == 0) {
            return;
        }

        size[node] = size[left[node]] + size[right[node]] + 1;
        sum[node] = sum[left[node]] + sum[right[node]] + value[node];
    }

    static int nextPriority() {
        seed += 0x9E3779B97F4A7C15L;

        long z = seed;

        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        z ^= z >>> 31;

        return (int)z;
    }

    static void addEdge(int u, int v) {
        to[edges] = v;
        next[edges] = head[u];
        head[u] = edges++;
    }
}