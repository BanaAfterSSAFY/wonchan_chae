#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int timer = 0;
    vector<vector<int>> graph;
    vector<int> tin, tout, sub;

    void dfs(int u, int parent, vector<int>& nums) {
        tin[u] = timer++;
        sub[u] = nums[u];

        for(int v : graph[u]) {
            if(v == parent) {
                continue;
            }

            dfs(v, u, nums);
            sub[u] ^= sub[v];
        }

        tout[u] = timer - 1;
    }

    bool isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();

        graph.assign(n, {});
        tin.resize(n);
        tout.resize(n);
        sub.resize(n);

        vector<int> child;

        for(auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].push_back(v);
            graph[v].push_back(u);
        }

        dfs(0, -1, nums);

        for(int i = 1; i < n; i++) {
            child.push_back(i);
        }

        int total = sub[0];
        int answer = INT_MAX;

        for(int i = 0; i < child.size(); i++) {
            for(int j = i + 1; j < child.size(); j++) {
                int a = child[i];
                int b = child[j];

                int x, y, z;

                if(isAncestor(a, b)) {
                    x = sub[b];
                    y = sub[a] ^ sub[b];
                    z = total ^ sub[a];
                }
                else if(isAncestor(b, a)) {
                    x = sub[a];
                    y = sub[b] ^ sub[a];
                    z = total ^ sub[b];
                }
                else{
                    x = sub[a];
                    y = sub[b];
                    z = total ^ sub[a] ^ sub[b];
                }

                int mx = max({x, y, z});
                int mn = min({x, y, z});

                answer = min(answer, mx - mn);
            }
        }

        return answer;
    }
};