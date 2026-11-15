#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    class Fenwick {
    public:
        int n;
        vector<int> tree;

        Fenwick(int n) {
            this->n = n;
            tree.assign(n + 1, 0);
        }

        void update(int index, int value) {
            index++;

            while(index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        int query(int index) {
            int result = 0;
            index++;

            while(index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }

            return result;
        }

        void rangeUpdate(int left, int right, int value) {
            update(left, value);

            if(right + 1 < n) {
                update(right + 1, value);
            }
        }
    };

    vector<bool> palindromePath(int n, vector<vector<int>>& edges, string s, vector<string>& queries) {
        vector<vector<int>> graph(n);

        for(auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        vector<int> parent(n);
        vector<int> depth(n);
        vector<int> tin(n);
        vector<int> tout(n);
        vector<int> order;

        parent[0] = 0;

        stack<pair<int, int>> st;
        st.push({0, 0});

        int timer = 0;

        while(!st.empty()) {
            auto [node, state] = st.top();
            st.pop();

            if(state == 0) {
                tin[node] = timer++;
                order.push_back(node);

                st.push({node, 1});

                for(int i = graph[node].size() - 1; i >= 0; i--) {
                    int next = graph[node][i];

                    if(next == parent[node]) {
                        continue;
                    }

                    parent[next] = node;
                    depth[next] = depth[node] + 1;
                    st.push({next, 0});
                }
            }
            else {
                tout[node] = timer - 1;
            }
        }

        int LOG = 1;

        while((1 << LOG) <= n) {
            LOG++;
        }

        vector<vector<int>> up(LOG, vector<int>(n));
        up[0] = parent;

        for(int j = 1; j < LOG; j++) {
            for(int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        auto lca = [&](int u, int v) {
            if(depth[u] < depth[v]) {
                swap(u, v);
            }

            int diff = depth[u] - depth[v];

            for(int j = 0; j < LOG; j++) {
                if(diff & (1 << j)) {
                    u = up[j][u];
                }
            }

            if(u == v) {
                return u;
            }

            for(int j = LOG - 1; j >= 0; j--) {
                if(up[j][u] != up[j][v]) {
                    u = up[j][u];
                    v = up[j][v];
                }
            }

            return parent[u];
        };

        vector<int> mask(n);

        for(int i = 0; i < n; i++) {
            mask[i] = 1 << (s[i] - 'a');
        }

        Fenwick fenwick(n);

        for(int i = 0; i < n; i++) {
            fenwick.rangeUpdate(tin[i], tout[i], mask[i]);
        }

        vector<bool> answer;

        for(string& query : queries) {
            stringstream ss(query);
            string type;
            int u;

            ss >> type >> u;

            if(type == "update") {
                char c;
                ss >> c;

                int nextMask = 1 << (c - 'a');
                int diff = mask[u] ^ nextMask;

                if(diff != 0) {
                    fenwick.rangeUpdate(tin[u], tout[u], diff);
                    mask[u] = nextMask;
                }
            }
            else {
                int v;
                ss >> v;

                int p = lca(u, v);

                int pathMask = fenwick.query(tin[u])
                             ^ fenwick.query(tin[v])
                             ^ mask[p];

                answer.push_back((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }
};