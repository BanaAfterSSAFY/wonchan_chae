#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        for(int i = 0; i < n; i++) {
            int c = s[i] - 'a';
            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<pair<int, int>> intervals;

        for(int c = 0; c < 26; c++) {
            if(first[c] == n) {
                continue;
            }

            int left = first[c];
            int right = last[c];
            bool valid = true;

            for(int i = left; i <= right; i++) {
                int x = s[i] - 'a';

                if(first[x] < left) {
                    valid = false;
                    break;
                }

                right = max(right, last[x]);
            }

            if(valid) {
                intervals.push_back({left, right});
            }
        }

        sort(intervals.begin(), intervals.end(), [](auto& a, auto& b) {
            if(a.second != b.second) {
                return a.second < b.second;
            }

            return a.first > b.first;
        });

        vector<string> answer;
        int end = -1;

        for(auto [left, right] : intervals) {
            if(left > end) {
                answer.push_back(s.substr(left, right - left + 1));
                end = right;
            }
        }

        return answer;
    }
};