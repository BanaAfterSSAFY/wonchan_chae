#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        sort(satisfaction.begin(), satisfaction.end());

        int suffix = 0;
        int answer = 0;

        for(int i = satisfaction.size() - 1; i >= 0; i--) {
            if(suffix + satisfaction[i] <= 0) {
                break;
            }

            suffix += satisfaction[i];
            answer += suffix;
        }

        return answer;
    }
};