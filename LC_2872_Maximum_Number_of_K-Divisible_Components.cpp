#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<int>> graph;
    vector<int> values;
    int k;
    int answer = 0;

    long long dfs(int node, int parent) {
        long long sum = values[node];

        for(int next : graph[node]) {
            if (next == parent) continue;
            sum += dfs(next, node);
        }

        if(sum % k == 0) {
            answer++;
            return 0;
        }

        return sum % k;
    }

    int maxKDivisibleComponents(int n, vector<vector<int>>& edges, vector<int>& values, int k) {
        this->values = values;
        this->k = k;

        graph.assign(n, {});

        for(auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        dfs(0, -1);

        return answer;
    }
};