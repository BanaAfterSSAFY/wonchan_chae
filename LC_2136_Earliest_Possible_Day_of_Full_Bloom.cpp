#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int earliestFullBloom(vector<int>& plantTime, vector<int>& growTime) {
        int n = plantTime.size();
        vector<int> order(n);

        iota(order.begin(), order.end(), 0);

        sort(order.begin(), order.end(), [&](int a, int b) {
            return growTime[a] > growTime[b];
        });

        int day = 0;
        int answer = 0;

        for(int i : order) {
            day += plantTime[i];
            answer = max(answer, day + growTime[i]);
        }

        return answer;
    }
};