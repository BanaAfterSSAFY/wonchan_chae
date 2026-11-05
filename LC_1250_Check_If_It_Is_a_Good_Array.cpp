#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isGoodArray(vector<int>& nums) {
        int g = 0;

        for(int x : nums) {
            g = gcd(g, x);

            if(g == 1) {
                return true;
            }
        }

        return false;
    }
};