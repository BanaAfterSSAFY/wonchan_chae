#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> minimumFlips(int n, vector<vector<int>>& edges, string start, string target) {
        vector<vector<pair<int, int>>> graph(n);

        for(int i = 0; i < edges.size(); i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            graph[u].push_back({v, i});
            graph[v].push_back({u, i});
        }

        vector<int> parent(n, -1);
        vector<int> parentEdge(n, -1);
        vector<int> order;

        stack<int> st;
        st.push(0);
        parent[0] = 0;

        while(!st.empty()) {
            int cur = st.top();
            st.pop();

            order.push_back(cur);

            for(auto [next, edge] : graph[cur]) {
                if(parent[next] != -1) {
                    continue;
                }

                parent[next] = cur;
                parentEdge[next] = edge;
                st.push(next);
            }
        }

        vector<int> need(n);

        for(int i = 0; i < n; i++) {
            need[i] = start[i] != target[i];
        }

        vector<int> answer;

        for(int i = n - 1; i > 0; i--) {
            int node = order[i];

            if(need[node]) {
                answer.push_back(parentEdge[node]);
                need[parent[node]] ^= 1;
            }
        }

        if(need[0]) {
            return {-1};
        }

        sort(answer.begin(), answer.end());

        return answer;
    }
};