#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    struct Node {
        map<string, Node*> child;
        string serial;
        bool deleted = false;
    };

    unordered_map<string, int> freq;

    string encode(Node* node) {
        string s;

        for(auto& [name, next] : node->child) {
            s += "(" + name + encode(next) + ")";
        }

        node->serial = s;

        if(!s.empty()) {
            freq[s]++;
        }

        return s;
    }

    void mark(Node* node) {
        if(!node->serial.empty() && freq[node->serial] > 1) {
            node->deleted = true;
            return;
        }

        for(auto& [name, next] : node->child) {
            mark(next);
        }
    }

    void collect(Node* node, vector<string>& path, vector<vector<string>>& answer) {
        for(auto& [name, next] : node->child) {
            if(next->deleted) {
                continue;
            }

            path.push_back(name);
            answer.push_back(path);

            collect(next, path, answer);

            path.pop_back();
        }
    }

    vector<vector<string>> deleteDuplicateFolder(vector<vector<string>>& paths) {
        Node* root = new Node();

        for(auto& path : paths) {
            Node* cur = root;

            for(string& name : path) {
                if(!cur->child.count(name)) {
                    cur->child[name] = new Node();
                }
                cur = cur->child[name];
            }
        }

        encode(root);

        for(auto& [name, node] : root->child) {
            mark(node);
        }

        vector<vector<string>> answer;
        vector<string> path;

        collect(root, path, answer);

        return answer;
    }
};