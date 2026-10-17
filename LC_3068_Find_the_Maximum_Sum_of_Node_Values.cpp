#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long maximumValueSum(vector<int>& nums, int k, vector<vector<int>>& edges) {
        long long sum = 0;
        vector<long long> gain;

        for(int x : nums) {
            sum += x;
            gain.push_back((x ^ k) - x);
        }

        sort(gain.rbegin(), gain.rend());

        long long add = 0;
        long long answer = sum;

        for(int i = 0; i + 1 < gain.size(); i += 2) {
            add += gain[i] + gain[i + 1];
            answer = max(answer, sum + add);
        }

        return answer;
    }
};