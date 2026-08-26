import java.util.*;

class Solution {

    static int[] parent;
    static int[] size;
    static int[] ids;
    static int[] enter;
    static int[] roots;
    static int[] left;
    static int[] right;
    static int[] priority;
    static int[] key;
    static int[] batch;
    static int[] stack;

    static long seed = 0x1234ABCD5678EF90L;

    public String[] solution(int n, int[][] queries) {
        int qCount = queries.length;

        parent = new int[n];
        size = new int[n];
        ids = new int[n];
        enter = new int[n];

        roots = new int[n + qCount + 1];

        left = new int[n + 1];
        right = new int[n + 1];
        priority = new int[n + 1];
        key = new int[n + 1];
        batch = new int[n + 1];

        stack = new int[n + 1];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            ids[i] = i;
            enter[i] = 0;

            int node = i + 1;

            key[node] = 0;
            batch[node] = i;
            priority[node] = nextPriority();

            roots[i] = node;
        }

        int answerCount = 0;

        for(int[] query : queries) {
            if(query[0] == 3) {
                answerCount++;
            }
        }

        String[] answer = new String[answerCount];
        int answerIndex = 0;
        int nextSetId = n;

        for(int qi = 0; qi < qCount; qi++) {
            int type = queries[qi][0];
            int x = queries[qi][1];
            int y = queries[qi][2];
            int time = qi + 1;

            int rx = find(x);
            int ry = find(y);

            int sx = ids[rx];
            int sy = ids[ry];

            if(type == 1) {
                if(sx == sy) {
                    continue;
                }

                int source = roots[sy];
                int rep = collapse(source);

                left[source] = 0;
                right[source] = 0;
                key[source] = time;
                priority[source] = nextPriority();
                batch[source] = rep;

                ids[rep] = sx;
                enter[rep] = time;

                roots[sy] = 0;
                roots[sx] = merge(roots[sx], source);
            }

            else if(type == 2) {
                int tx = enter[rx];
                int ty = enter[ry];

                if(tx > ty) {
                    continue;
                }

                int root = roots[sx];

                long first = split(root, tx);

                int a = (int)(first >>> 32);
                int bc = (int)first;

                long second = split(bc, ty + 1);

                int b = (int)(second >>> 32);
                int c = (int)second;

                roots[sx] = merge(a, c);

                if(b == 0) {
                    continue;
                }

                int rep = collapse(b);
                int newSet = nextSetId++;

                left[b] = 0;
                right[b] = 0;
                key[b] = time;
                priority[b] = nextPriority();
                batch[b] = rep;

                ids[rep] = newSet;
                enter[rep] = time;

                roots[newSet] = b;
            }

            else {
                answer[answerIndex++] = sx == sy ? "Yes" : "No";
            }
        }

        return answer;
    }

    static int find(int x) {
        int root = x;

        while(parent[root] != root) {
            root = parent[root];
        }

        while(parent[x] != x) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }

        return root;
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) {
            return a;
        }

        if(size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        parent[b] = a;
        size[a] += size[b];

        return a;
    }

    static int collapse(int root) {
        int top = 0;
        int rep = -1;

        stack[top++] = root;

        while(top > 0) {
            int node = stack[--top];

            if(left[node] != 0) {
                stack[top++] = left[node];
            }

            if(right[node] != 0) {
                stack[top++] = right[node];
            }

            int current = find(batch[node]);

            if(rep == -1) {
                rep = current;
            }
            else {
                rep = union(rep, current);
            }
        }

        return find(rep);
    }

    static int merge(int a, int b) {
        if(a == 0) {
            return b;
        }

        if(b == 0) {
            return a;
        }

        if(Integer.compareUnsigned(priority[a], priority[b]) < 0) {
            right[a] = merge(right[a], b);
            return a;
        }

        left[b] = merge(a, left[b]);
        return b;
    }

    static long split(int root, int value) {
        if(root == 0) {
            return 0L;
        }

        if(key[root] < value) {
            long result = split(right[root], value);

            int a = (int)(result >>> 32);
            int b = (int)result;

            right[root] = a;

            return pack(root, b);
        }

        long result = split(left[root], value);

        int a = (int)(result >>> 32);
        int b = (int)result;

        left[root] = b;

        return pack(a, root);
    }

    static long pack(int a, int b) {
        return ((long)a << 32) | (b & 0xffffffffL);
    }

    static int nextPriority() {
        seed += 0x9E3779B97F4A7C15L;

        long z = seed;

        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        z ^= z >>> 31;

        return (int)z;
    }
}