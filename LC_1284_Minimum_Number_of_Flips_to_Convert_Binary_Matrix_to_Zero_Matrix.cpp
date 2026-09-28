#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minFlips(vector<vector<int>>& mat) {
        int m = mat.size();
        int n = mat[0].size();
        int total = m * n;

        int start = 0;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(mat[r][c]) {
                    start |= 1 << (r * n + c);
                }
            }
        }

        vector<int> flipMask(total);
        int dr[5] = {0, 1, -1, 0, 0};
        int dc[5] = {0, 0, 0, 1, -1};

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                int mask = 0;

                for(int d = 0; d < 5; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    mask ^= 1 << (nr * n + nc);
                }

                flipMask[r * n + c] = mask;
            }
        }

        queue<int> q;
        vector<int> dist(1 << total, -1);

        q.push(start);
        dist[start] = 0;

        while(!q.empty()) {
            int cur = q.front();
            q.pop();

            if(cur == 0) {
                return dist[cur];
            }

            for(int i = 0; i < total; i++) {
                int next = cur ^ flipMask[i];

                if(dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[cur] + 1;
                q.push(next);
            }
        }

        return -1;
    }
};