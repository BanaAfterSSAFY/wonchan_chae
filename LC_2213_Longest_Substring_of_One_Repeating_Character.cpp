#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    struct Node {
        int len = 0;
        int prefix = 0;
        int suffix = 0;
        int best = 0;
        char leftChar = 0;
        char rightChar = 0;
    };

    string s;
    vector<Node> tree;

    Node merge(Node a, Node b) {
        if(a.len == 0) {
            return b;
        }

        if(b.len == 0) {
            return a;
        }

        Node result;

        result.len = a.len + b.len;
        result.leftChar = a.leftChar;
        result.rightChar = b.rightChar;

        result.prefix = a.prefix;
        result.suffix = b.suffix;
        result.best = max(a.best, b.best);

        if(a.rightChar == b.leftChar) {
            result.best = max(result.best, a.suffix + b.prefix);

            if(a.prefix == a.len) {
                result.prefix = a.len + b.prefix;
            }

            if(b.suffix == b.len) {
                result.suffix = b.len + a.suffix;
            }
        }

        return result;
    }

    void build(int node, int left, int right) {
        if(left == right) {
            tree[node].len = 1;
            tree[node].prefix = 1;
            tree[node].suffix = 1;
            tree[node].best = 1;
            tree[node].leftChar = s[left];
            tree[node].rightChar = s[left];

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, char c) {
        if(left == right) {
            tree[node].leftChar = c;
            tree[node].rightChar = c;
            return;
        }

        int mid = (left + right) / 2;

        if(index <= mid) {
            update(node * 2, left, mid, index, c);
        }
        else {
            update(node * 2 + 1, mid + 1, right, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        this->s = s;

        int n = s.size();
        int q = queryIndices.size();

        tree.resize(n * 4);

        build(1, 0, n - 1);

        vector<int> answer;

        for(int i = 0; i < q; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters[i]);
            answer.push_back(tree[1].best);
        }

        return answer;
    }
};