import java.util.*;

class Solution {

    static int n;
    static int[] head;
    static int[] to;
    static int[] next;
    static int cnt;
    static int[] edges;
    static int[] order;
    static int[] height;
    static int[] open;

    public int solution(int[][] t) {
        n = t.length + 1;

        head = new int[n];
        Arrays.fill(head, -1);

        to = new int[t.length * 2];
        next = new int[t.length * 2];

        for(int[] edge : t) {
            solve(edge[0], edge[1]);
            solve(edge[1], edge[0]);
        }

        edges = new int[n];
        Arrays.fill(edges, -2);

        order = new int[n];

        int[] stack = new int[n];
        int top = 0;
        int size = 0;

        stack[top++] = 0;
        edges[0] = -1;

        while(top > 0) {
            int v = stack[--top];

            order[size++] = v;

            for(int e = head[v]; e != -1; e = next[e]) {
                int nv = to[e];

                if(edges[nv] != -2) {
                    continue;
                }

                edges[nv] = e ^ 1;
                stack[top++] = nv;
            }
        }

        height = new int[cnt];
        open = new int[cnt];

        for(int i = n - 1; i > 0; i--) {
            int v = order[i];

            int count = 0;

            int o1 = 0;
            int o2 = 0;
            int oi1 = -1;
            int oi2 = -1;

            int h1 = 0;
            int h2 = 0;
            int hi1 = -1;
            int hi2 = -1;

            for(int e = head[v]; e != -1; e = next[e]) {
                if(e == edges[v]) {
                    continue;
                }

                int in = e ^ 1;
                int ov = open[in];
                int hv = height[in];

                count++;

                if(ov > o1) {
                    o2 = o1;
                    oi2 = oi1;

                    o1 = ov;
                    oi1 = e;
                }
                else if(ov > o2) {
                    o2 = ov;
                    oi2 = e;
                }

                if(hv > h1) {
                    h2 = h1;
                    hi2 = hi1;

                    h1 = hv;
                    hi1 = e;
                }
                else if(hv > h2) {
                    h2 = hv;
                    hi2 = e;
                }
            }

            int out = edges[v];

            height[out] = 1 + h1;

            if(count == 0) {
                open[out] = 1;
            }
            else if(count == 1) {
                open[out] = 1 + o1;
            }
            else {
                int best;

                if(oi1 != hi1) {
                    best = o1 + h1;
                }
                else {
                    best = Math.max(o1 + h2, o2 + h1);
                }

                open[out] = 1 + best;
            }
        }

        int answer = 1;

        for(int idx = 0; idx < n; idx++) {
            int v = order[idx];

            int degree = 0;

            int o1 = 0;
            int o2 = 0;
            int o3 = 0;
            int oi1 = -1;
            int oi2 = -1;
            int oi3 = -1;

            int h1 = 0;
            int h2 = 0;
            int h3 = 0;
            int hi1 = -1;
            int hi2 = -1;
            int hi3 = -1;

            for(int e = head[v]; e != -1; e = next[e]) {
                int in = e ^ 1;

                int ov = open[in];
                int hv = height[in];

                degree++;

                if(ov > o1) {
                    o3 = o2;
                    oi3 = oi2;

                    o2 = o1;
                    oi2 = oi1;

                    o1 = ov;
                    oi1 = e;
                }
                else if(ov > o2) {
                    o3 = o2;
                    oi3 = oi2;

                    o2 = ov;
                    oi2 = e;
                }
                else if(ov > o3) {
                    o3 = ov;
                    oi3 = e;
                }

                if(hv > h1) {
                    h3 = h2;
                    hi3 = hi2;

                    h2 = h1;
                    hi2 = hi1;

                    h1 = hv;
                    hi1 = e;
                }
                else if(hv > h2) {
                    h3 = h2;
                    hi3 = hi2;

                    h2 = hv;
                    hi2 = e;
                }
                else if(hv > h3) {
                    h3 = hv;
                    hi3 = e;
                }
            }

            if(degree == 0) {
                answer = Math.max(answer, 1);
            }
            else if(degree == 1) {
                answer = Math.max(answer, 1 + o1);
            }
            else if(degree == 2) {
                answer = Math.max(answer, 1 + o1 + o2);
            }
            else {
                for(int e = head[v]; e != -1; e = next[e]) {
                    int a;
                    int b;

                    if(oi1 != e) {
                        a = o1;

                        if(oi2 != e) {
                            b = o2;
                        }
                        else {
                            b = o3;
                        }
                    }
                    else {
                        a = o2;
                        b = o3;
                    }

                    int candidate = 1 + height[e ^ 1] + a + b;

                    answer = Math.max(answer, candidate);
                }
            }

            for(int e = head[v]; e != -1; e = next[e]) {
                if(edges[v] != -1 && e == edges[v]) {
                    continue;
                }

                int allowed = degree - 1;

                int bestH;

                if(hi1 != e) {
                    bestH = h1;
                }
                else {
                    bestH = h2;
                }

                height[e] = 1 + bestH;

                if(allowed == 0) {
                    open[e] = 1;
                }
                else if(allowed == 1) {
                    int bestO = oi1 != e ? o1 : o2;
                    open[e] = 1 + bestO;
                }
                else {
                    int oa;
                    int ob;
                    int oia;
                    int oib;

                    if(oi1 != e) {
                        oa = o1;
                        oia = oi1;

                        if(oi2 != e) {
                            ob = o2;
                            oib = oi2;
                        }
                        else {
                            ob = o3;
                            oib = oi3;
                        }
                    }
                    else {
                        oa = o2;
                        oia = oi2;

                        ob = o3;
                        oib = oi3;
                    }

                    int ha;
                    int hb;
                    int hia;
                    int hib;

                    if(hi1 != e) {
                        ha = h1;
                        hia = hi1;

                        if(hi2 != e) {
                            hb = h2;
                            hib = hi2;
                        }
                        else {
                            hb = h3;
                            hib = hi3;
                        }
                    }
                    else {
                        ha = h2;
                        hia = hi2;

                        hb = h3;
                        hib = hi3;
                    }

                    int best;

                    if(oia != hia) {
                        best = oa + ha;
                    }
                    else {
                        best = Math.max(oa + hb, ob + ha);
                    }

                    open[e] = 1 + best;
                }
            }
        }

        return answer;
    }

    static void solve(int u, int v) {
        to[cnt] = v;
        next[cnt] = head[u];
        head[u] = cnt++;
    }
}