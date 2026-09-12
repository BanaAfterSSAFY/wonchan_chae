#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string makeLargestSpecial(string s) {
        vector<string> parts;

        int balance = 0;
        int start = 0;

        for (int i = 0; i < s.size(); i++) {
            balance += s[i] == '1' ? 1 : -1;

            if (balance == 0) {
                string inner = s.substr(start + 1, i - start - 1);
                parts.push_back("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }

        sort(parts.rbegin(), parts.rend());

        string answer;

        for (string& part : parts) {
            answer += part;
        }

        return answer;
    }
};