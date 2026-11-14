#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        const long long MOD = 1000000007;

        vector<int> count(31);

        for(int x : nums) {
            for(int bit = 0; bit < 31; bit++) {
                if(x & (1 << bit)) {
                    count[bit]++;
                }
            }
        }

        long long answer = 0;

        for(int i = 0; i < k; i++) {
            long long value = 0;

            for(int bit = 0; bit < 31; bit++) {
                if(count[bit] > 0) {
                    value |= 1LL << bit;
                    count[bit]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return answer;
    }
};