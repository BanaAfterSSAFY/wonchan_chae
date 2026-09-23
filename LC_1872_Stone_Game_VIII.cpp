#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();

        for(int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        int answer = stones[n - 1];

        for(int i = n - 2; i >= 1; i--) {
            answer = max(answer, stones[i] - answer);
        }

        return answer;
    }
};