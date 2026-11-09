#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    struct State {
        long long score = 0;
        vector<int> indices;
    };

    bool better(const State& a, const State& b) {
        if(a.score != b.score) {
            return a.score > b.score;
        }

        return a.indices < b.indices;
    }

    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();

        vector<array<long long, 4>> data(n);

        for(int i = 0; i < n; i++) {
            data[i] = {
                intervals[i][0],
                intervals[i][1],
                intervals[i][2],
                i
            };
        }

        sort(data.begin(), data.end(), [](const auto& a, const auto& b) {
            if(a[1] != b[1]) {
                return a[1] < b[1];
            }

            return a[0] < b[0];
        });

        vector<long long> right(n);

        for(int i = 0; i < n; i++) {
            right[i] = data[i][1];
        }

        vector<array<State, 5>> dp(n + 1);

        for(int i = 1; i <= n; i++) {
            long long l = data[i - 1][0];
            long long w = data[i - 1][2];
            int index = data[i - 1][3];

            int prev = lower_bound(right.begin(), right.end(), l) - right.begin();

            for(int count = 1; count <= 4; count++) {
                State skip = dp[i - 1][count];
                State take = dp[prev][count - 1];

                take.score += w;
                take.indices.push_back(index);
                sort(take.indices.begin(), take.indices.end());

                dp[i][count] = better(take, skip) ? take : skip;
            }
        }

        return dp[n][4].indices;
    }
};