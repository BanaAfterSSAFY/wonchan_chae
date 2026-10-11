#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool parseBoolExpr(string expression) {
        stack<char> st;

        for(char c : expression) {
            if(c == ',') {
                continue;
            }

            if(c != ')') {
                st.push(c);
                continue;
            }

            int trueCount = 0;
            int falseCount = 0;

            while(st.top() != '(') {
                if(st.top() == 't') {
                    trueCount++;
                }
                else if(st.top() == 'f') {
                    falseCount++;
                }

                st.pop();
            }

            st.pop();

            char op = st.top();
            st.pop();

            bool result;

            if(op == '!') {
                result = falseCount > 0;
            }
            else if(op == '&') {
                result = falseCount == 0;
            }
            else {
                result = trueCount > 0;
            }

            st.push(result ? 't' : 'f');
        }

        return st.top() == 't';
    }
};