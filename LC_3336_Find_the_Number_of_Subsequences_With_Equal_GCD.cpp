#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1000000007;

        int maximum = *max_element(nums.begin(), nums.end());

        vector<vector<int>> dp(maximum + 1, vector<int>(maximum + 1));
        dp[0][0] = 1;

        for(int x : nums) {
            vector<vector<int>> next = dp;

            for(int g1 = 0; g1 <= maximum; g1++) {
                for(int g2 = 0; g2 <= maximum; g2++) {
                    if(dp[g1][g2] == 0) {
                        continue;
                    }

                    int ng1 = gcd(g1, x);
                    int ng2 = gcd(g2, x);

                    next[ng1][g2] += dp[g1][g2];

                    if(next[ng1][g2] >= MOD) {
                        next[ng1][g2] -= MOD;
                    }

                    next[g1][ng2] += dp[g1][g2];

                    if(next[g1][ng2] >= MOD) {
                        next[g1][ng2] -= MOD;
                    }
                }
            }

            dp = move(next);
        }

        int answer = 0;

        for(int g = 1; g <= maximum; g++) {
            answer += dp[g][g];

            if(answer >= MOD) {
                answer -= MOD;
            }
        }

        return answer;
    }
};