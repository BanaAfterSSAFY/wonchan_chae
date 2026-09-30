#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    void dfs(int node, int parent, int color, vector<vector<int>>& graph, vector<int>& colors, vector<int>& count) {
        colors[node] = color;
        count[color]++;

        for(int next : graph[node]) {
            if(next == parent) {
                continue;
            }

            dfs(next, node, color ^ 1, graph, colors, count);
        }
    }

    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        int n = edges1.size() + 1;
        int m = edges2.size() + 1;

        vector<vector<int>> graph1(n);
        vector<vector<int>> graph2(m);

        for(auto& edge : edges1) {
            graph1[edge[0]].push_back(edge[1]);
            graph1[edge[1]].push_back(edge[0]);
        }

        for(auto& edge : edges2) {
            graph2[edge[0]].push_back(edge[1]);
            graph2[edge[1]].push_back(edge[0]);
        }

        vector<int> color1(n);
        vector<int> color2(m);

        vector<int> count1(2);
        vector<int> count2(2);

        dfs(0, -1, 0, graph1, color1, count1);
        dfs(0, -1, 0, graph2, color2, count2);

        int best = max(count2[0], count2[1]);

        vector<int> answer(n);

        for(int i = 0; i < n; i++) {
            answer[i] = count1[color1[i]] + best;
        }

        return answer;
    }
};