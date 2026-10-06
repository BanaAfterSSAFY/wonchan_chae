#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumObstacles(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        deque<pair<int, int>> dq;

        int dy[4] = {1, -1, 0, 0};
        int dx[4] = {0, 0, 1, -1};

        dist[0][0] = 0;
        dq.push_front({0, 0});

        while(!dq.empty()) {
            auto [y, x] = dq.front();
            dq.pop_front();

            for(int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny < 0 || ny >= m || nx < 0 || nx >= n) {
                    continue;
                }

                int cost = grid[ny][nx];

                if(dist[ny][nx] > dist[y][x] + cost) {
                    dist[ny][nx] = dist[y][x] + cost;

                    if(cost == 0) {
                        dq.push_front({ny, nx});
                    }
                    else {
                        dq.push_back({ny, nx});
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }
};