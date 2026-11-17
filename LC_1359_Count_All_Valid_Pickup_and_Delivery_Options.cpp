#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countOrders(int n) {
        const long long MOD = 1000000007;

        long long answer = 1;

        for(int i = 1; i <= n; i++) {
            answer = answer * i % MOD;
            answer = answer * (2 * i - 1) % MOD;
        }

        return answer;
    }
};