#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    static const int MOD = 1000000007;

    vector<int> assignEdgeWeights(vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int n = edges.size() + 1;
        int LOG = 1;

        while((1 << LOG) <= n) {
            LOG++;
        }

        vector<vector<int>> graph(n + 1);

        for(auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].push_back(v);
            graph[v].push_back(u);
        }

        vector<int> depth(n + 1);
        vector<vector<int>> parent(LOG, vector<int>(n + 1));

        queue<int> q;
        q.push(1);

        parent[0][1] = 0;

        while(!q.empty()) {
            int cur = q.front();
            q.pop();

            for(int next : graph[cur]) {
                if(next == parent[0][cur]) {
                    continue;
                }

                parent[0][next] = cur;
                depth[next] = depth[cur] + 1;
                q.push(next);
            }
        }

        for(int j = 1; j < LOG; j++) {
            for(int i = 1; i <= n; i++) {
                parent[j][i] = parent[j - 1][parent[j - 1][i]];
            }
        }

        vector<int> power(n + 1);
        power[0] = 1;

        for(int i = 1; i <= n; i++) {
            power[i] = (long long)power[i - 1] * 2 % MOD;
        }

        auto lca = [&](int u, int v) {
            if(depth[u] < depth[v]) {
                swap(u, v);
            }

            int diff = depth[u] - depth[v];

            for(int j = 0; j < LOG; j++) {
                if(diff & (1 << j)) {
                    u = parent[j][u];
                }
            }

            if(u == v) {
                return u;
            }

            for(int j = LOG - 1; j >= 0; j--) {
                if(parent[j][u] != parent[j][v]) {
                    u = parent[j][u];
                    v = parent[j][v];
                }
            }

            return parent[0][u];
        };

        vector<int> answer;

        for(auto& query : queries) {
            int u = query[0];
            int v = query[1];

            if(u == v) {
                answer.push_back(0);
                continue;
            }

            int p = lca(u, v);
            int distance = depth[u] + depth[v] - 2 * depth[p];

            answer.push_back(power[distance - 1]);
        }

        return answer;
    }
};