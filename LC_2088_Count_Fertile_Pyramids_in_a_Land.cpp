#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countPyramids(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int answer = 0;

        vector<vector<int>> dp(m, vector<int>(n));

        for(int i = m - 1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    continue;
                }

                dp[i][j] = 1;

                if(i + 1 < m && j > 0 && j + 1 < n) {
                    dp[i][j] += min({
                        dp[i + 1][j - 1],
                        dp[i + 1][j],
                        dp[i + 1][j + 1]
                    });
                }

                answer += dp[i][j] - 1;
            }
        }

        dp.assign(m, vector<int>(n));

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    continue;
                }

                dp[i][j] = 1;

                if(i > 0 && j > 0 && j + 1 < n) {
                    dp[i][j] += min({
                        dp[i - 1][j - 1],
                        dp[i - 1][j],
                        dp[i - 1][j + 1]
                    });
                }

                answer += dp[i][j] - 1;
            }
        }

        return answer;
    }
};