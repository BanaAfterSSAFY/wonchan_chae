#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    pair<int, int> memo[29][29][29];
    bool seen[29][29][29];

    pair<int, int> dfs(int n, int a, int b) {
        if(a > b) {
            swap(a, b);
        }

        if(a + b == n + 1) {
            return {1, 1};
        }

        if(seen[n][a][b]) {
            return memo[n][a][b];
        }

        seen[n][a][b] = true;

        int nextN = (n + 1) / 2;

        bool dp[15][15] = {};
        dp[0][0] = true;

        for(int i = 1; i <= n / 2; i++) {
            int j = n + 1 - i;

            vector<int> winners;

            if(i == a || j == a) {
                winners.push_back(a);
            }
            else if(i == b || j == b) {
                winners.push_back(b);
            }
            else {
                winners.push_back(i);
                winners.push_back(j);
            }

            bool next[15][15] = {};

            for(int x = 0; x <= nextN; x++) {
                for(int y = 0; y <= nextN; y++) {
                    if(!dp[x][y]) {
                        continue;
                    }

                    for(int w : winners) {
                        int nx = x + (w < a);
                        int ny = y + (w < b);

                        next[nx][ny] = true;
                    }
                }
            }

            memcpy(dp, next, sizeof(dp));
        }

        if(n % 2 == 1) {
            int mid = n / 2 + 1;

            bool next[15][15] = {};

            for(int x = 0; x <= nextN; x++) {
                for(int y = 0; y <= nextN; y++) {
                    if(!dp[x][y]) {
                        continue;
                    }

                    int nx = x + (mid < a);
                    int ny = y + (mid < b);

                    next[nx][ny] = true;
                }
            }

            memcpy(dp, next, sizeof(dp));
        }

        int earliest = INT_MAX;
        int latest = 0;

        for(int x = 0; x <= nextN; x++) {
            for(int y = 0; y <= nextN; y++) {
                if(!dp[x][y]) {
                    continue;
                }

                int na = x + 1;
                int nb = y + 1;

                auto [e, l] = dfs(nextN, na, nb);

                earliest = min(earliest, e + 1);
                latest = max(latest, l + 1);
            }
        }

        return memo[n][a][b] = {earliest, latest};
    }

    vector<int> earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        auto [earliest, latest] = dfs(n, firstPlayer, secondPlayer);
        return {earliest, latest};
    }
};