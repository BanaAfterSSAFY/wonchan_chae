#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int index = 0;
    string expression;

    set<string> mergeConcat(set<string>& a, set<string>& b) {
        set<string> result;

        for(string x : a) {
            for(string y : b) {
                result.insert(x + y);
            }
        }

        return result;
    }

    set<string> parse() {
        set<string> result;
        set<string> current = {""};

        while(index < expression.size() && expression[index] != '}') {
            if(expression[index] == ',') {
                result.insert(current.begin(), current.end());
                current = {""};
                index++;
                continue;
            }

            set<string> next;

            if(expression[index] == '{') {
                index++;
                next = parse();
                index++;
            }
            else {
                next.insert(string(1, expression[index]));
                index++;
            }

            current = mergeConcat(current, next);
        }

        result.insert(current.begin(), current.end());

        return result;
    }

    vector<string> braceExpansionII(string expression) {
        this->expression = expression;

        set<string> result = parse();

        return vector<string>(result.begin(), result.end());
    }
};