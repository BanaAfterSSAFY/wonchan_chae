#include <bits/stdc++.h>
using namespace std;

class Graph {
public:
    int n;
    vector<vector<int>> dist;
    const int INF = 1000000000;

    Graph(int n, vector<vector<int>>& edges) {
        this->n = n;
        dist.assign(n, vector<int>(n, INF));

        for(int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for(auto& edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }

                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    void addEdge(vector<int> edge) {
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];

        if(w >= dist[u][v]) {
            return;
        }

        vector<int> toU(n);
        vector<int> fromV(n);

        for(int i = 0; i < n; i++) {
            toU[i] = dist[i][u];
            fromV[i] = dist[v][i];
        }

        for(int i = 0; i < n; i++) {
            if(toU[i] == INF) {
                continue;
            }

            for(int j = 0; j < n; j++) {
                if(fromV[j] == INF) {
                    continue;
                }

                dist[i][j] = min(dist[i][j], toU[i] + w + fromV[j]);
            }
        }
    }

    int shortestPath(int node1, int node2) {
        if(dist[node1][node2] == INF) {
            return -1;
        }

        return dist[node1][node2];
    }
};