#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int answer = 0;

    void dfs(int idx, vector<string>& words, vector<int>& score, vector<int>& count, int cur) {
        if(idx == words.size()) {
            answer = max(answer, cur);
            return;
        }

        dfs(idx + 1, words, score, count, cur);

        vector<int> used(26);
        int add = 0;
        bool possible = true;

        for(char c : words[idx]) {
            int x = c - 'a';
            used[x]++;
            add += score[x];

            if(used[x] > count[x]) {
                possible = false;
            }
        }

        if(!possible) {
            return;
        }

        for(int i = 0; i < 26; i++) {
            count[i] -= used[i];
        }

        dfs(idx + 1, words, score, count, cur + add);

        for(int i = 0; i < 26; i++) {
            count[i] += used[i];
        }
    }

    int maxScoreWords(vector<string>& words, vector<char>& letters, vector<int>& score) {
        vector<int> count(26);

        for(char c : letters) {
            count[c - 'a']++;
        }

        dfs(0, words, score, count, 0);

        return answer;
    }
};