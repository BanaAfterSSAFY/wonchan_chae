#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string s;
    int k;
    vector<int> limit;

    bool valid(const string& t) {
        int index = 0;
        int count = 0;

        for(char c : s) {
            if(c == t[index]) {
                index++;

                if(index == t.size()) {
                    index = 0;
                    count++;

                    if(count == k) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    string longestSubsequenceRepeatedK(string s, int k) {
        this->s = s;
        this->k = k;

        vector<int> freq(26);

        for(char c : s) {
            freq[c - 'a']++;
        }

        limit.resize(26);

        for(int i = 0; i < 26; i++) {
            limit[i] = freq[i] / k;
        }

        queue<pair<string, vector<int>>> q;
        q.push({"", vector<int>(26)});

        string answer = "";

        while(!q.empty()) {
            auto [cur, used] = q.front();
            q.pop();

            for(int c = 0; c < 26; c++) {
                if(used[c] >= limit[c]) {
                    continue;
                }

                string next = cur + char('a' + c);

                if(!valid(next)) {
                    continue;
                }

                vector<int> nextUsed = used;
                nextUsed[c]++;

                q.push({next, nextUsed});

                if(next.size() > answer.size() || (next.size() == answer.size() && next > answer)) {
                    answer = next;
                }
            }
        }

        return answer;
    }
};