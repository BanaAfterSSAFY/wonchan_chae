#include <bits/stdc++.h>
using namespace std;

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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<string> grid(N);

    for(int i = 0; i < N; i++) {
        cin >> grid[i];
    }

    DSU dsu(N * M);

    for(int y = 0; y < N; y++) {
        for(int x = 0; x < M; x++) {
            int ny = y;
            int nx = x;

            if(grid[y][x] == 'U') {
                ny--;
            }
            else if(grid[y][x] == 'D') {
                ny++;
            }
            else if(grid[y][x] == 'L') {
                nx--;
            }
            else {
                nx++;
            }

            dsu.merge(y * M + x, ny * M + nx);
        }
    }

    int answer = 0;

    for(int i = 0; i < N * M; i++) {
        if(dsu.find(i) == i) {
            answer++;
        }
    }

    cout << answer << '\n';

    return 0;
}