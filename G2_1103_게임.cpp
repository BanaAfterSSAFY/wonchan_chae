#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<string> board;
vector<vector<int>> dp;
vector<vector<int>> visited;
bool infinite = false;

int dy[4] = {1, -1, 0, 0};
int dx[4] = {0, 0, 1, -1};

int dfs(int y, int x) {
    if(infinite) {
        return 0;
    }

    if(dp[y][x] != 0) {
        return dp[y][x];
    }

    visited[y][x] = 1;
    dp[y][x] = 1;

    int move = board[y][x] - '0';

    for(int d = 0; d < 4; d++) {
        int ny = y + dy[d] * move;
        int nx = x + dx[d] * move;

        if(ny < 0 || ny >= N || nx < 0 || nx >= M) {
            continue;
        }

        if(board[ny][nx] == 'H') {
            continue;
        }

        if(visited[ny][nx]) {
            infinite = true;
            return 0;
        }

        dp[y][x] = max(dp[y][x], dfs(ny, nx) + 1);

        if(infinite) {
            return 0;
        }
    }

    visited[y][x] = 0;

    return dp[y][x];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M;

    board.resize(N);

    for(int i = 0; i < N; i++) {
        cin >> board[i];
    }

    dp.assign(N, vector<int>(M));
    visited.assign(N, vector<int>(M));

    int answer = dfs(0, 0);

    if(infinite) {
        cout << -1 << '\n';
    }
    else {
        cout << answer << '\n';
    }

    return 0;
}