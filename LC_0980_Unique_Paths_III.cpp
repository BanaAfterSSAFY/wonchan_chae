#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int n, m;
    int answer = 0;
    int remain = 0;
    vector<vector<int>> grid;
    int dy[4] = {1, -1, 0, 0};
    int dx[4] = {0, 0, 1, -1};

    void dfs(int y, int x, int count) {
        if (grid[y][x] == 2) {
            if (count == remain) {
                answer++;
            }
            return;
        }

        int temp = grid[y][x];
        grid[y][x] = -1;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                continue;
            }

            if (grid[ny][nx] == -1) {
                continue;
            }

            dfs(ny, nx, count + 1);
        }

        grid[y][x] = temp;
    }

    int uniquePathsIII(vector<vector<int>>& grid) {
        this->grid = grid;

        n = grid.size();
        m = grid[0].size();

        int sy = 0;
        int sx = 0;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (grid[y][x] != -1) {
                    remain++;
                }

                if (grid[y][x] == 1) {
                    sy = y;
                    sx = x;
                }
            }
        }

        dfs(sy, sx, 1);

        return answer;
    }
};