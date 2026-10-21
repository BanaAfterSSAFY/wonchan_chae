#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<int> dp(n + 1, INT_MIN);

        dp[n] = 0;

        for(int i = n - 1; i >= 0; i--) {
            int sum = 0;

            for(int take = 1; take <= 3 && i + take <= n; take++) {
                sum += stoneValue[i + take - 1];
                dp[i] = max(dp[i], sum - dp[i + take]);
            }
        }

        if(dp[0] > 0) {
            return "Alice";
        }
        else if(dp[0] < 0) {
            return "Bob";
        }
        else {
            return "Tie";
        }
    }
};