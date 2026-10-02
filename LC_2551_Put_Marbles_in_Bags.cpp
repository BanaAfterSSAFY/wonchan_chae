#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long putMarbles(vector<int>& weights, int k) {
        vector<long long> pairs;

        for(int i = 0; i + 1 < weights.size(); i++) {
            pairs.push_back((long long)weights[i] + weights[i + 1]);
        }

        sort(pairs.begin(), pairs.end());

        long long minimum = 0;
        long long maximum = 0;

        for(int i = 0; i < k - 1; i++) {
            minimum += pairs[i];
            maximum += pairs[pairs.size() - 1 - i];
        }

        return maximum - minimum;
    }
};