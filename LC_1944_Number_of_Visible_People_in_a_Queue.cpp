#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> answer(n);
        stack<int> st;

        for(int i = n - 1; i >= 0; i--) {
            while(!st.empty() && heights[i] > heights[st.top()]) {
                answer[i]++;
                st.pop();
            }

            if(!st.empty()) {
                answer[i]++;
            }

            st.push(i);
        }

        return answer;
    }
};