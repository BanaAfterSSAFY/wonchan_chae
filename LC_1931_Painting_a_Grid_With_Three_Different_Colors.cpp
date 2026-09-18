#include <bits/stdc++.h>
using namespace std;

class Solution {
public:

    int colorTheGrid(int m, int n) {
        vector<int> states;
        int total = 1;

        for(int i = 0; i < m; i++) {
            total *= 3;
        }

        for(int q = 0; q < total; q++) {
            int x = q;
            int prev = -1;
            bool ok = true;

            for(int i = 0; i < m; i++) {
                int color = x % 3;
                x /= 3;

                if(color == prev) {
                    ok = false;
                    break;
                }

                prev = color;
            }

            if(ok) {
                states.push_back(q);
            }
        }

        int s = states.size();
        vector<vector<int>> next(s);

        for(int i = 0; i < s; i++) {
            for(int j = 0; j < s; j++) {
                int a = states[i];
                int b = states[j];
                bool ok = true;

                for(int r = 0; r < m; r++) {
                    if(a % 3 == b % 3) {
                        ok = false;
                        break;
                    }

                    a /= 3;
                    b /= 3;
                }

                if(ok) {
                    next[i].push_back(j);
                }
            }
        }

        vector<long long> dp(s, 1);

        for(int col = 1; col < n; col++) {
            vector<long long> ndp(s);

            for(int i = 0; i < s; i++) {
                for(int j : next[i]) {
                    ndp[j] += dp[i];

                    if(ndp[j] >= 1000000007) {
                        ndp[j] -= 1000000007;
                    }
                }
            }

            dp = ndp;
        }

        long long answer = 0;

        for(long long value : dp) {
            answer += value;
            answer %= 1000000007;
        }

        return answer;
    }
};