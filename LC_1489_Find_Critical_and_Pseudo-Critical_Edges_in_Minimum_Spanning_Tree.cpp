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

            return true;
        }
    };

    int mst(int n, vector<vector<int>>& edges, int skip, int force) {
        DSU dsu(n);

        int weight = 0;
        int count = 0;

        if(force != -1) {
            auto& edge = edges[force];

            if(dsu.merge(edge[0], edge[1])) {
                weight += edge[2];
                count++;
            }
        }

        for(int i = 0; i < edges.size(); i++) {
            if(i == skip || i == force) {
                continue;
            }

            auto& edge = edges[i];

            if(dsu.merge(edge[0], edge[1])) {
                weight += edge[2];
                count++;

                if(count == n - 1) {
                    break;
                }
            }
        }

        if(count != n - 1) {
            return INT_MAX;
        }

        return weight;
    }

    vector<vector<int>> findCriticalAndPseudoCriticalEdges(int n, vector<vector<int>>& edges) {
        for(int i = 0; i < edges.size(); i++) {
            edges[i].push_back(i);
        }

        sort(edges.begin(), edges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });

        int base = mst(n, edges, -1, -1);

        vector<int> critical;
        vector<int> pseudo;

        for(int i = 0; i < edges.size(); i++) {
            if(mst(n, edges, i, -1) > base) {
                critical.push_back(edges[i][3]);
            }
            else if(mst(n, edges, -1, i) == base) {
                pseudo.push_back(edges[i][3]);
            }
        }

        return {critical, pseudo};
    }
};