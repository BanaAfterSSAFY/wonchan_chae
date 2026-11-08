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

    bool possible(int n, vector<vector<int>>& edges, int k, int stability) {
        DSU dsu(n);
        int count = 0;

        for(auto& edge : edges) {
            if(edge[3] == 0) {
                continue;
            }

            if(edge[2] < stability) {
                return false;
            }

            if(!dsu.merge(edge[0], edge[1])) {
                return false;
            }

            count++;
        }

        for(auto& edge : edges) {
            if(edge[3] == 1 || edge[2] < stability) {
                continue;
            }

            if(dsu.merge(edge[0], edge[1])) {
                count++;
            }
        }

        int upgrades = 0;

        for(auto& edge : edges) {
            if(edge[3] == 1 || edge[2] >= stability || edge[2] * 2 < stability) {
                continue;
            }

            if(dsu.merge(edge[0], edge[1])) {
                upgrades++;
                count++;

                if(upgrades > k) {
                    return false;
                }
            }
        }

        return count == n - 1;
    }

    int maxStability(int n, vector<vector<int>>& edges, int k) {
        if(!possible(n, edges, k, 1)) {
            return -1;
        }

        int left = 1;
        int right = 200000;
        int answer = 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(possible(n, edges, k, mid)) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return answer;
    }
};