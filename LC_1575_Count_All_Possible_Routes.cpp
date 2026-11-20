#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    static const int MOD = 1000000007;

    vector<vector<int>> dp;
    vector<int> locations;
    int finish;

    int dfs(int city, int fuel) {
        if(dp[city][fuel] != -1) {
            return dp[city][fuel];
        }

        long long result = city == finish ? 1 : 0;

        for(int next = 0; next < locations.size(); next++) {
            if(next == city) {
                continue;
            }

            int cost = abs(locations[city] - locations[next]);

            if(cost > fuel) {
                continue;
            }

            result += dfs(next, fuel - cost);
            result %= MOD;
        }

        return dp[city][fuel] = result;
    }

    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        this->locations = locations;
        this->finish = finish;

        dp.assign(locations.size(), vector<int>(fuel + 1, -1));

        return dfs(start, fuel);
    }
};