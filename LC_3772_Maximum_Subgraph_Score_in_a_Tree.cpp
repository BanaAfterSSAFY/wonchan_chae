#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> maxSubgraphScore(int n, vector<vector<int>>& edges, vector<int>& good) {
        vector<vector<int>> graph(n);

        for(auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        vector<int> parent(n, -1);
        vector<int> order;

        queue<int> q;
        q.push(0);
        parent[0] = 0;

        while(!q.empty()) {
            int cur = q.front();
            q.pop();

            order.push_back(cur);

            for(int next : graph[cur]) {
                if(parent[next] != -1) {
                    continue;
                }

                parent[next] = cur;
                q.push(next);
            }
        }

        vector<int> down(n);

        for(int i = n - 1; i >= 0; i--) {
            int cur = order[i];

            down[cur] = good[cur] ? 1 : -1;

            for(int next : graph[cur]) {
                if(parent[next] != cur) {
                    continue;
                }

                down[cur] += max(0, down[next]);
            }
        }

        vector<int> answer = down;

        for(int cur : order) {
            for(int next : graph[cur]) {
                if(parent[next] != cur) {
                    continue;
                }

                int parentScore = answer[cur] - max(0, down[next]);
                answer[next] = down[next] + max(0, parentScore);
            }
        }

        return answer;
    }
};