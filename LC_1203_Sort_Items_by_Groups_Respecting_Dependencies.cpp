#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> topo(vector<vector<int>>& graph, vector<int> indegree) {
        queue<int> q;

        for(int i = 0; i < indegree.size(); i++) {
            if(indegree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> order;

        while(!q.empty()) {
            int cur = q.front();
            q.pop();

            order.push_back(cur);

            for(int next : graph[cur]) {
                indegree[next]--;

                if(indegree[next] == 0) {
                    q.push(next);
                }
            }
        }

        if(order.size() != graph.size()) {
            return {};
        }

        return order;
    }

    vector<int> sortItems(int n, int m, vector<int>& group, vector<vector<int>>& beforeItems) {
        int groupCount = m;

        for(int i = 0; i < n; i++) {
            if(group[i] == -1) {
                group[i] = groupCount++;
            }
        }

        vector<vector<int>> itemGraph(n);
        vector<vector<int>> groupGraph(groupCount);

        vector<int> itemIndegree(n);
        vector<int> groupIndegree(groupCount);

        for(int i = 0; i < n; i++) {
            for(int prev : beforeItems[i]) {
                itemGraph[prev].push_back(i);
                itemIndegree[i]++;

                if(group[prev] != group[i]) {
                    groupGraph[group[prev]].push_back(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        vector<int> itemOrder = topo(itemGraph, itemIndegree);
        vector<int> groupOrder = topo(groupGraph, groupIndegree);

        if(itemOrder.empty() || groupOrder.empty()) {
            return {};
        }

        vector<vector<int>> groupedItems(groupCount);

        for(int item : itemOrder) {
            groupedItems[group[item]].push_back(item);
        }

        vector<int> answer;

        for(int g : groupOrder) {
            for(int item : groupedItems[g]) {
                answer.push_back(item);
            }
        }

        return answer;
    }
};