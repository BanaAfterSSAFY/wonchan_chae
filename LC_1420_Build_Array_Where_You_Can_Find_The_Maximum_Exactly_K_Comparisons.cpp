#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numOfArrays(int n, int m, int k) {
        const int MOD = 1000000007;

        vector<vector<long long>> dp(k + 1, vector<long long>(m + 1));

        for(int maximum = 1; maximum <= m; maximum++) {
            dp[1][maximum] = 1;
        }

        for(int length = 2; length <= n; length++) {
            vector<vector<long long>> next(k + 1, vector<long long>(m + 1));

            for(int cost = 1; cost <= k; cost++) {
                long long prefix = 0;

                for(int maximum = 1; maximum <= m; maximum++) {
                    if(cost > 1) {
                        prefix += dp[cost - 1][maximum - 1];
                        prefix %= MOD;
                    }

                    next[cost][maximum] = (
                        dp[cost][maximum] * maximum + prefix
                    ) % MOD;
                }
            }

            dp = move(next);
        }

        long long answer = 0;

        for(int maximum = 1; maximum <= m; maximum++) {
            answer += dp[k][maximum];
            answer %= MOD;
        }

        return answer;
    }
};