#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    class DSU {
    public:
        vector<int> parent;
        vector<int> rank;

        DSU(int n) {
            parent.resize(n);
            rank.resize(n);

            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if(parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if(a == b) {
                return;
            }

            if(rank[a] < rank[b]) {
                swap(a, b);
            }

            parent[b] = a;

            if(rank[a] == rank[b]) {
                rank[a]++;
            }
        }
    };

    vector<int> minimumCost(int n, vector<vector<int>>& edges, vector<vector<int>>& query) {
        DSU dsu(n);

        for(auto& edge : edges) {
            dsu.merge(edge[0], edge[1]);
        }

        vector<int> cost(n, -1);

        for(auto& edge : edges) {
            int root = dsu.find(edge[0]);
            cost[root] &= edge[2];
        }

        vector<int> answer;

        for(auto& q : query) {
            int a = dsu.find(q[0]);
            int b = dsu.find(q[1]);

            if(a != b) {
                answer.push_back(-1);
            }
            else {
                answer.push_back(cost[a]);
            }
        }

        return answer;
    }
};