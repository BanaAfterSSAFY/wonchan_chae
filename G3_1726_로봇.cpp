#include <bits/stdc++.h>
using namespace std;

int M, N;
int grid[101][101];
int dist[101][101][5];

int dy[5] = {0, 0, 0, 1, -1};
int dx[5] = {0, 1, -1, 0, 0};

int turnLeft[5] = {0, 4, 3, 1, 2};
int turnRight[5] = {0, 3, 4, 2, 1};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> M >> N;

    for(int y = 1; y <= M; y++) {
        for(int x = 1; x <= N; x++) {
            cin >> grid[y][x];
        }
    }

    int sy, sx, sd;
    int ey, ex, ed;

    cin >> sy >> sx >> sd;
    cin >> ey >> ex >> ed;

    memset(dist, -1, sizeof(dist));

    queue<tuple<int, int, int>> q;

    q.push({sy, sx, sd});
    dist[sy][sx][sd] = 0;

    while(!q.empty()) {
        auto [y, x, d] = q.front();
        q.pop();

        if(y == ey && x == ex && d == ed) {
            cout << dist[y][x][d] << '\n';
            return 0;
        }

        int ld = turnLeft[d];
        int rd = turnRight[d];

        if(dist[y][x][ld] == -1) {
            dist[y][x][ld] = dist[y][x][d] + 1;
            q.push({y, x, ld});
        }

        if(dist[y][x][rd] == -1) {
            dist[y][x][rd] = dist[y][x][d] + 1;
            q.push({y, x, rd});
        }

        for(int k = 1; k <= 3; k++) {
            int ny = y + dy[d] * k;
            int nx = x + dx[d] * k;

            if(ny < 1 || ny > M || nx < 1 || nx > N) {
                break;
            }

            if(grid[ny][nx] == 1) {
                break;
            }

            if(dist[ny][nx][d] != -1) {
                continue;
            }

            dist[ny][nx][d] = dist[y][x][d] + 1;
            q.push({ny, nx, d});
        }
    }

    return 0;
}