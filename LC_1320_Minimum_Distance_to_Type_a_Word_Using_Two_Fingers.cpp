#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int dist(int a, int b) {
        if(a == 26 || b == 26) {
            return 0;
        }

        int ax = a / 6;
        int ay = a % 6;
        int bx = b / 6;
        int by = b % 6;

        return abs(ax - bx) + abs(ay - by);
    }

    int minimumDistance(string word) {
        int n = word.size();

        vector<vector<int>> dp(27, vector<int>(27, 1e9));
        dp[26][26] = 0;

        for(char ch : word) {
            int cur = ch - 'A';
            vector<vector<int>> next(27, vector<int>(27, 1e9));

            for(int a = 0; a <= 26; a++) {
                for(int b = 0; b <= 26; b++) {
                    if(dp[a][b] == 1e9) continue;

                    next[cur][b] = min(next[cur][b], dp[a][b] + dist(a, cur));

                    next[a][cur] = min(next[a][cur], dp[a][b] + dist(b, cur));
                }
            }

            dp = move(next);
        }

        int answer = 1e9;

        for(int a = 0; a <= 26; a++) {
            for(int b = 0; b <= 26; b++) {
                answer = min(answer, dp[a][b]);
            }
        }

        return answer;
    }
};