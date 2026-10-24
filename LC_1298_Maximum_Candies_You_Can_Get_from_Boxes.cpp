#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxCandies(
        vector<int>& status,
        vector<int>& candies,
        vector<vector<int>>& keys,
        vector<vector<int>>& containedBoxes,
        vector<int>& initialBoxes
    ) {
        int n = status.size();

        vector<int> have(n);
        vector<int> opened(n);
        vector<int> used(n);

        queue<int> q;

        for(int box : initialBoxes) {
            have[box] = 1;

            if(status[box]) {
                q.push(box);
                opened[box] = 1;
            }
        }

        int answer = 0;

        while(!q.empty()) {
            int box = q.front();
            q.pop();

            if(used[box]) {
                continue;
            }

            used[box] = 1;
            answer += candies[box];

            for(int key : keys[box]) {
                status[key] = 1;

                if(have[key] && !opened[key]) {
                    opened[key] = 1;
                    q.push(key);
                }
            }

            for(int next : containedBoxes[box]) {
                have[next] = 1;

                if(status[next] && !opened[next]) {
                    opened[next] = 1;
                    q.push(next);
                }
            }
        }

        return answer;
    }
};