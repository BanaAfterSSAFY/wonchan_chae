#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int n, d;
    vector<int> arr;
    vector<int> dp;

    int dfs(int i) {
        if(dp[i] != 0) {
            return dp[i];
        }

        dp[i] = 1;

        for(int j = i + 1; j < n && j <= i + d; j++) {
            if(arr[j] >= arr[i]) {
                break;
            }

            dp[i] = max(dp[i], dfs(j) + 1);
        }

        for(int j = i - 1; j >= 0 && j >= i - d; j--) {
            if(arr[j] >= arr[i]) {
                break;
            }

            dp[i] = max(dp[i], dfs(j) + 1);
        }

        return dp[i];
    }

    int maxJumps(vector<int>& arr, int d) {
        this->arr = arr;
        this->d = d;
        n = arr.size();

        dp.assign(n, 0);

        int answer = 0;

        for(int i = 0; i < n; i++) {
            answer = max(answer, dfs(i));
        }

        return answer;
    }
};