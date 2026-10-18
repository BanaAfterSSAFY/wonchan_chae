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

    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        int total = row * col;
        int top = total;
        int bottom = total + 1;

        DSU dsu(total + 2);
        vector<vector<int>> land(row, vector<int>(col));

        int dy[4] = {1, -1, 0, 0};
        int dx[4] = {0, 0, 1, -1};

        for(int day = total - 1; day >= 0; day--) {
            int y = cells[day][0] - 1;
            int x = cells[day][1] - 1;

            land[y][x] = 1;

            int index = y * col + x;

            if(y == 0) {
                dsu.merge(index, top);
            }

            if(y == row - 1) {
                dsu.merge(index, bottom);
            }

            for(int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny < 0 || ny >= row || nx < 0 || nx >= col) {
                    continue;
                }

                if(!land[ny][nx]) {
                    continue;
                }

                dsu.merge(index, ny * col + nx);
            }

            if(dsu.find(top) == dsu.find(bottom)) {
                return day;
            }
        }

        return 0;
    }
};