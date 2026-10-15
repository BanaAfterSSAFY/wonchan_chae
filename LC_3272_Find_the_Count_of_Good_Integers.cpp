#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long countGoodIntegers(int n, int k) {
        vector<long long> factorial(n + 1, 1);

        for(int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        int half = (n + 1) / 2;
        int start = 1;

        for(int i = 1; i < half; i++) {
            start *= 10;
        }

        int end = start * 10;

        set<string> seen;
        long long answer = 0;

        for(int prefix = start; prefix < end; prefix++) {
            string left = to_string(prefix);
            string palindrome = left;

            int index = n % 2 == 0 ? half - 1 : half - 2;

            for(int i = index; i >= 0; i--) {
                palindrome += left[i];
            }

            long long value = stoll(palindrome);

            if(value % k != 0) {
                continue;
            }

            vector<int> count(10);

            for(char c : palindrome) {
                count[c - '0']++;
            }

            string key;

            for(int x : count) {
                key += to_string(x) + "#";
            }

            if(seen.count(key)) {
                continue;
            }

            seen.insert(key);

            long long ways = factorial[n];

            for(int x : count) {
                ways /= factorial[x];
            }

            if(count[0] > 0) {
                long long invalid = factorial[n - 1];

                invalid /= factorial[count[0] - 1];

                for(int d = 1; d < 10; d++) {
                    invalid /= factorial[count[d]];
                }

                ways -= invalid;
            }

            answer += ways;
        }

        return answer;
    }
};