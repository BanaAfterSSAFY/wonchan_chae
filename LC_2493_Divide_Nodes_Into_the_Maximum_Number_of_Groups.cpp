#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int magnificentSets(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);

        for(auto& edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            graph[u].push_back(v);
            graph[v].push_back(u);
        }

        vector<int> color(n, -1);
        vector<vector<int>> components;

        for(int i = 0; i < n; i++) {
            if(color[i] != -1) {
                continue;
            }

            queue<int> q;
            vector<int> component;

            color[i] = 0;
            q.push(i);

            while(!q.empty()) {
                int cur = q.front();
                q.pop();

                component.push_back(cur);

                for(int next : graph[cur]) {
                    if(color[next] == -1) {
                        color[next] = color[cur] ^ 1;
                        q.push(next);
                    }
                    else if(color[next] == color[cur]) {
                        return -1;
                    }
                }
            }

            components.push_back(component);
        }

        int answer = 0;

        for(auto& component : components) {
            int maximum = 0;

            for(int start : component) {
                vector<int> dist(n, -1);
                queue<int> q;

                dist[start] = 1;
                q.push(start);

                int groups = 1;

                while(!q.empty()) {
                    int cur = q.front();
                    q.pop();

                    groups = max(groups, dist[cur]);

                    for(int next : graph[cur]) {
                        if(dist[next] != -1) {
                            continue;
                        }

                        dist[next] = dist[cur] + 1;
                        q.push(next);
                    }
                }

                maximum = max(maximum, groups);
            }

            answer += maximum;
        }

        return answer;
    }
};