#include <bits/stdc++.h>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    TreeNode* recoverFromPreorder(string traversal) {
        int n = traversal.size();
        vector<TreeNode*> st(n + 1);

        int i = 0;

        while (i < n) {
            int depth = 0;

            while (i < n && traversal[i] == '-') {
                depth++;
                i++;
            }

            int value = 0;

            while (i < n && isdigit(traversal[i])) {
                value = value * 10 + (traversal[i] - '0');
                i++;
            }

            TreeNode* node = new TreeNode(value);

            if (depth > 0) {
                TreeNode* parent = st[depth - 1];

                if (parent->left == nullptr) {
                    parent->left = node;
                } else {
                    parent->right = node;
                }
            }

            st[depth] = node;
        }

        return st[0];
    }
};