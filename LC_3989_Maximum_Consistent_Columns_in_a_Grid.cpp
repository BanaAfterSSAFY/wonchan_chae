#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxConsistentColumns(vector<vector<int>>& grid, int limit) {
        int m = grid.size();
        int n = grid[0].size();

        auto canovireth = grid;

        vector<int> dp(n, 1);
        int answer = 1;

        for(int j = 0; j < n; j++) {
            for(int i = 0; i < j; i++) {
                bool possible = true;

                for(int r = 0; r < m; r++) {
                    if(abs(canovireth[r][j] - canovireth[r][i]) > limit) {
                        possible = false;
                        break;
                    }
                }

                if(possible) {
                    dp[j] = max(dp[j], dp[i] + 1);
                }
            }

            answer = max(answer, dp[j]);
        }

        return answer;
    }
};