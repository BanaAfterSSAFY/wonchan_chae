#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int answer = 0;

    void dfs(int index, int count, vector<vector<int>>& requests, vector<int>& balance) {
        if(index == requests.size()) {
            for(int x : balance) {
                if(x != 0) {
                    return;
                }
            }

            answer = max(answer, count);
            return;
        }

        if(count + requests.size() - index <= answer) {
            return;
        }

        dfs(index + 1, count, requests, balance);

        int from = requests[index][0];
        int to = requests[index][1];

        balance[from]--;
        balance[to]++;

        dfs(index + 1, count + 1, requests, balance);

        balance[from]++;
        balance[to]--;
    }

    int maximumRequests(int n, vector<vector<int>>& requests) {
        vector<int> balance(n);

        dfs(0, 0, requests, balance);

        return answer;
    }
};