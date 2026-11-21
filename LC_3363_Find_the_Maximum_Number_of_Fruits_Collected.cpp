#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxCollectedFruits(vector<vector<int>>& fruits) {
        int n = fruits.size();

        if(n == 1) {
            return fruits[0][0];
        }

        int answer = 0;

        for(int i = 0; i < n; i++) {
            answer += fruits[i][i];
        }

        vector<vector<int>> dp(n, vector<int>(n, -1));

        dp[0][n - 1] = fruits[0][n - 1];

        for(int i = 1; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int d = -1; d <= 1; d++) {
                    int prev = j + d;

                    if(prev < 0 || prev >= n || dp[i - 1][prev] == -1) {
                        continue;
                    }

                    dp[i][j] = max(
                        dp[i][j],
                        dp[i - 1][prev] + fruits[i][j]
                    );
                }
            }
        }

        answer += dp[n - 2][n - 1];

        dp.assign(n, vector<int>(n, -1));
        dp[n - 1][0] = fruits[n - 1][0];

        for(int j = 1; j < n - 1; j++) {
            for(int i = j + 1; i < n; i++) {
                for(int d = -1; d <= 1; d++) {
                    int prev = i + d;

                    if(prev < 0 || prev >= n || dp[prev][j - 1] == -1) {
                        continue;
                    }

                    dp[i][j] = max(
                        dp[i][j],
                        dp[prev][j - 1] + fruits[i][j]
                    );
                }
            }
        }

        answer += dp[n - 1][n - 2];

        return answer;
    }
};