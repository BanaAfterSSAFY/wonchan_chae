#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int rows = grid.size();
        int cols = grid[0].size();

        vector<vector<int>> dp(cols, vector<int>(cols, -1));
        dp[0][cols - 1] = grid[0][0] + (cols == 1 ? 0 : grid[0][cols - 1]);

        for(int r = 1; r < rows; r++) {
            vector<vector<int>> next(cols, vector<int>(cols, -1));

            for(int c1 = 0; c1 < cols; c1++) {
                for(int c2 = 0; c2 < cols; c2++) {
                    if(dp[c1][c2] == -1) {
                        continue;
                    }

                    for(int d1 = -1; d1 <= 1; d1++) {
                        for(int d2 = -1; d2 <= 1; d2++) {
                            int nc1 = c1 + d1;
                            int nc2 = c2 + d2;

                            if(nc1 < 0 || nc1 >= cols || nc2 < 0 || nc2 >= cols) {
                                continue;
                            }

                            int cherries = grid[r][nc1];

                            if(nc1 != nc2) {
                                cherries += grid[r][nc2];
                            }

                            next[nc1][nc2] = max(next[nc1][nc2], dp[c1][c2] + cherries);
                        }
                    }
                }
            }

            dp = move(next);
        }

        int answer = 0;

        for(int c1 = 0; c1 < cols; c1++) {
            for(int c2 = 0; c2 < cols; c2++) {
                answer = max(answer, dp[c1][c2]);
            }
        }

        return answer;
    }
};