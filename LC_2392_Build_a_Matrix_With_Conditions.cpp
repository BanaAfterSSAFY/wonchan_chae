#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> topo(int k, vector<vector<int>>& conditions) {
        vector<vector<int>> graph(k + 1);
        vector<int> indegree(k + 1);

        for (auto& c : conditions) {
            graph[c[0]].push_back(c[1]);
            indegree[c[1]]++;
        }

        queue<int> q;

        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> order;

        while (!q.empty()) {
            int cur = q.front();
            q.pop();

            order.push_back(cur);

            for (int next : graph[cur]) {
                if (--indegree[next] == 0) {
                    q.push(next);
                }
            }
        }

        if (order.size() != k) {
            return {};
        }

        return order;
    }

    vector<vector<int>> buildMatrix(int k, vector<vector<int>>& rowConditions, vector<vector<int>>& colConditions) {
        vector<int> rowOrder = topo(k, rowConditions);
        vector<int> colOrder = topo(k, colConditions);

        if (rowOrder.empty() || colOrder.empty()) {
            return {};
        }

        vector<int> rowPos(k + 1);
        vector<int> colPos(k + 1);

        for (int i = 0; i < k; i++) {
            rowPos[rowOrder[i]] = i;
            colPos[colOrder[i]] = i;
        }

        vector<vector<int>> answer(k, vector<int>(k));

        for (int value = 1; value <= k; value++) {
            answer[rowPos[value]][colPos[value]] = value;
        }

        return answer;
    }
};