#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumEffort(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] - a[0] > b[1] - b[0];
        });

        int answer = 0;
        int spent = 0;

        for(auto& task : tasks) {
            answer = max(answer, spent + task[1]);
            spent += task[0];
        }

        return answer;
    }
};