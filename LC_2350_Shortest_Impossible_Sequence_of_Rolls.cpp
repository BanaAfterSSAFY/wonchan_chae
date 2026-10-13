#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int shortestSequence(vector<int>& rolls, int k) {
        vector<int> seen(k + 1);
        int round = 1;
        int count = 0;

        for(int x : rolls) {
            if(seen[x] == round) {
                continue;
            }

            seen[x] = round;
            count++;

            if(count == k) {
                round++;
                count = 0;
            }
        }

        return round;
    }
};