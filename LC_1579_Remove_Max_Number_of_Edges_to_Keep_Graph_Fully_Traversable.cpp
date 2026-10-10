#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    class DSU {
    public:
        vector<int> parent;
        vector<int> rank;
        int count;

        DSU(int n) {
            parent.resize(n + 1);
            rank.resize(n + 1);
            count = n;

            for(int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if(parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        bool merge(int a, int b) {
            a = find(a);
            b = find(b);

            if(a == b) {
                return false;
            }

            if(rank[a] < rank[b]) {
                swap(a, b);
            }

            parent[b] = a;

            if(rank[a] == rank[b]) {
                rank[a]++;
            }

            count--;

            return true;
        }
    };

    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        DSU alice(n);
        DSU bob(n);

        int used = 0;

        for(auto& edge : edges) {
            if(edge[0] != 3) {
                continue;
            }

            if(alice.merge(edge[1], edge[2])) {
                bob.merge(edge[1], edge[2]);
                used++;
            }
        }

        for(auto& edge : edges) {
            if(edge[0] == 1) {
                if(alice.merge(edge[1], edge[2])) {
                    used++;
                }
            }
            else if(edge[0] == 2) {
                if(bob.merge(edge[1], edge[2])) {
                    used++;
                }
            }
        }

        if(alice.count != 1 || bob.count != 1) {
            return -1;
        }

        return edges.size() - used;
    }
};