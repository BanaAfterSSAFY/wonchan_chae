import java.util.*;

class Solution {

    static class Node {
        int[] pos;
        int[] lazy;

        Node(int[] pos, int initialToken) {
            this.pos = pos;

            if(pos.length > 0) {
                lazy = new int[pos.length * 4 + 5];
                lazy[1] = initialToken;
            }
            else {
                lazy = new int[1];
            }
        }

        int get(int index) {
            return get(1, 0, pos.length - 1, index);
        }

        int get(int node, int left, int right, int index) {
            if(lazy[node] != 0) {
                return lazy[node];
            }

            int mid = (left + right) >>> 1;

            if(index <= mid) {
                return get(node << 1, left, mid, index);
            }

            return get(node << 1 | 1, mid + 1, right, index);
        }

        void assign(int ql, int qr, int token) {
            if(ql > qr || pos.length == 0) {
                return;
            }

            assign(1, 0, pos.length - 1, ql, qr, token);
        }

        void assign(int node, int left, int right, int ql, int qr, int token) {
            if(qr < left || right < ql) {
                return;
            }

            if(ql <= left && right <= qr) {
                lazy[node] = token;
                return;
            }

            if(lazy[node] != 0) {
                int value = lazy[node];

                lazy[node << 1] = value;
                lazy[node << 1 | 1] = value;
                lazy[node] = 0;
            }

            int mid = (left + right) >>> 1;

            assign(node << 1, left, mid, ql, qr, token);
            assign(node << 1 | 1, mid + 1, right, ql, qr, token);
        }

        int lowerBound(int target) {
            int left = 0;
            int right = pos.length;

            while(left < right) {
                int mid = (left + right) >>> 1;

                if(pos[mid] < target) {
                    left = mid + 1;
                }
                else {
                    right = mid;
                }
            }

            return left;
        }

        int upperBound(int target) {
            int left = 0;
            int right = pos.length;

            while(left < right) {
                int mid = (left + right) >>> 1;

                if(pos[mid] <= target) {
                    left = mid + 1;
                }
                else {
                    right = mid;
                }
            }

            return left;
        }
    }

    int[] code;
    int[] ranks;

    Node[] trees;

    int[][] tg;

    int[] parent;
    int[] labels;
    byte[] rank;

    int tokenCount;

    public String[] solution(String s, String[] query) {
        int n = s.length();
        int qn = query.length;

        int maxTokens = 32;

        for(String q : query) {
            if(q.charAt(0) == '3') {
                int p = q.lastIndexOf(' ') + 1;
                int mask = 0;

                for(int i = p; i < q.length(); i++) {
                    mask |= 1 << (q.charAt(i) - 'a');
                }

                maxTokens += Integer.bitCount(mask);
            }
        }

        parent = new int[maxTokens + 5];
        labels = new int[maxTokens + 5];
        rank = new byte[maxTokens + 5];

        tg = new int[26][qn + 1];

        code = new int[n];
        ranks = new int[n];

        int[] count = new int[26];

        for(int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            code[i] = ch;
            count[ch]++;
        }

        int[][] positions = new int[26][];

        for(int ch = 0; ch < 26; ch++) {
            positions[ch] = new int[count[ch]];
        }

        Arrays.fill(count, 0);

        for(int i = 0; i < n; i++) {
            int ch = code[i];
            int r = count[ch]++;

            positions[ch][r] = i + 1;
            ranks[i] = r;
        }

        trees = new Node[26];

        for(int ch = 0; ch < 26; ch++) {
            int token = 0;

            if(positions[ch].length > 0) {
                token = createToken(0);
                tg[ch][0] = token;
            }

            trees[ch] = new Node(positions[ch], token);
        }

        ArrayList<String> answer = new ArrayList<>();

        int groupId = 0;

        for(String q : query) {
            char type = q.charAt(0);

            if(type == '1' || type == '4') {
                int p = 2;
                int x = 0;

                while(p < q.length() && q.charAt(p) != ' ') {
                    x = x * 10 + q.charAt(p++) - '0';
                }

                p++;

                int y = 0;

                while(p < q.length()) {
                    y = y * 10 + q.charAt(p++) - '0';
                }

                int gx = getGroup(x);
                int gy = getGroup(y);

                if(type == '1') {
                    answer.add(gx == gy ? "YES" : "NO");
                }
                else if(gx != gy) {
                    int early = Math.min(gx, gy);
                    int late = Math.max(gx, gy);

                    for(int ch = 0; ch < 26; ch++) {
                        unionLocal(ch, early, late);
                    }
                }
            }

            else if(type == '2') {
                int p = 2;
                int x = 0;

                while(q.charAt(p) != ' ') {
                    x = x * 10 + q.charAt(p++) - '0';
                }

                p++;

                int mask = 0;

                while(p < q.length()) {
                    mask |= 1 << (q.charAt(p++) - 'a');
                }

                int oldGroup = getGroup(x);
                int newGroup = ++groupId;

                while(mask != 0) {
                    int ch = Integer.numberOfTrailingZeros(mask);

                    mask &= mask - 1;

                    moveWholeLetter(ch, oldGroup, newGroup);
                }
            }

            else if(type == '3') {
                int p = 2;
                int x = 0;

                while(q.charAt(p) != ' ') {
                    x = x * 10 + q.charAt(p++) - '0';
                }

                p++;

                int y = 0;

                while(q.charAt(p) != ' ') {
                    y = y * 10 + q.charAt(p++) - '0';
                }

                p++;

                int mask = 0;

                while(p < q.length()) {
                    mask |= 1 << (q.charAt(p++) - 'a');
                }

                int newGroup = ++groupId;

                while(mask != 0) {
                    int ch = Integer.numberOfTrailingZeros(mask);

                    mask &= mask - 1;

                    Node tree = trees[ch];

                    int left = tree.lowerBound(x);
                    int right = tree.upperBound(y) - 1;

                    if(left <= right) {
                        int token = createToken(newGroup);

                        tg[ch][newGroup] = token;

                        tree.assign(left, right, token);
                    }
                }
            }

            else {
                int[] composition = new int[(groupId + 1) * 26];

                for(int i = 0; i < n; i++) {
                    int group = getGroup(i + 1);

                    composition[group * 26 + code[i]]++;
                }

                for(int group = 0; group <= groupId; group++) {
                    int base = group * 26;

                    StringBuilder sb = new StringBuilder();

                    for(int ch = 0; ch < 26; ch++) {
                        int value = composition[base + ch];

                        if(value == 0) {
                            continue;
                        }

                        if(sb.length() > 0) {
                            sb.append(' ');
                        }

                        sb.append((char)('a' + ch))
                          .append(' ')
                          .append(value);
                    }

                    if(sb.length() > 0) {
                        answer.add(sb.toString());
                    }
                }
            }
        }

        return answer.toArray(new String[0]);
    }

    int createToken(int group) {
        int token = ++tokenCount;

        parent[token] = token;
        labels[token] = group;

        return token;
    }

    int find(int x) {
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

    int getGroup(int position) {
        int index = position - 1;
        int ch = code[index];

        int token = trees[ch].get(ranks[index]);

        return labels[find(token)];
    }

    int getToken(int ch, int group) {
        int token = tg[ch][group];

        if(token == 0) {
            return 0;
        }

        int root = find(token);

        if(labels[root] != group) {
            return 0;
        }

        tg[ch][group] = root;

        return root;
    }

    void moveWholeLetter(int ch, int oldGroup, int newGroup) {
        int root = getToken(ch, oldGroup);

        if(root == 0) {
            return;
        }

        labels[root] = newGroup;

        tg[ch][oldGroup] = 0;
        tg[ch][newGroup] = root;
    }

    void unionLocal(int ch, int early, int late) {
        int lateRoot = getToken(ch, late);

        if(lateRoot == 0) {
            return;
        }

        int earlyRoot = getToken(ch, early);

        if(earlyRoot == 0) {
            labels[lateRoot] = early;

            tg[ch][early] = lateRoot;
            tg[ch][late] = 0;

            return;
        }

        if(earlyRoot != lateRoot) {
            if(rank[earlyRoot] < rank[lateRoot]) {
                int temp = earlyRoot;

                earlyRoot = lateRoot;
                lateRoot = temp;
            }

            parent[lateRoot] = earlyRoot;

            if(rank[earlyRoot] == rank[lateRoot]) {
                rank[earlyRoot]++;
            }
        }

        labels[earlyRoot] = early;

        tg[ch][early] = earlyRoot;
        tg[ch][late] = 0;
    }
}