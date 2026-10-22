#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> countSubgraphsForEachDiameter(int n, vector<vector<int>>& edges) {
        vector<vector<int>> dist(n, vector<int>(n, n));

        for(int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for(auto& edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        vector<int> answer(n - 1);

        for(int mask = 1; mask < (1 << n); mask++) {
            int nodes = __builtin_popcount(mask);

            if(nodes < 2) {
                continue;
            }

            int edgeCount = 0;

            for(auto& edge : edges) {
                int u = edge[0] - 1;
                int v = edge[1] - 1;

                if((mask & (1 << u)) && (mask & (1 << v))) {
                    edgeCount++;
                }
            }

            if(edgeCount != nodes - 1) {
                continue;
            }

            int diameter = 0;

            for(int i = 0; i < n; i++) {
                if(!(mask & (1 << i))) {
                    continue;
                }

                for(int j = i + 1; j < n; j++) {
                    if(!(mask & (1 << j))) {
                        continue;
                    }

                    diameter = max(diameter, dist[i][j]);
                }
            }

            answer[diameter - 1]++;
        }

        return answer;
    }
};