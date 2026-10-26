#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxBuilding(int n, vector<vector<int>>& restrictions) {
        restrictions.push_back({1, 0});
        sort(restrictions.begin(), restrictions.end());

        int m = restrictions.size();

        for(int i = 1; i < m; i++) {
            int dist = restrictions[i][0] - restrictions[i - 1][0];
            restrictions[i][1] = min(
                restrictions[i][1],
                restrictions[i - 1][1] + dist
            );
        }

        for(int i = m - 2; i >= 0; i--) {
            int dist = restrictions[i + 1][0] - restrictions[i][0];
            restrictions[i][1] = min(
                restrictions[i][1],
                restrictions[i + 1][1] + dist
            );
        }

        int answer = 0;

        for(int i = 1; i < m; i++) {
            int x1 = restrictions[i - 1][0];
            int h1 = restrictions[i - 1][1];
            int x2 = restrictions[i][0];
            int h2 = restrictions[i][1];

            int dist = x2 - x1;
            int peak = (h1 + h2 + dist) / 2;

            answer = max(answer, peak);
        }

        int lastId = restrictions.back()[0];
        int lastHeight = restrictions.back()[1];

        answer = max(answer, lastHeight + n - lastId);

        return answer;
    }
};