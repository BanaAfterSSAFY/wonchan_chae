#include <bits/stdc++.h>
using namespace std;

int dp[61][61][61];

int damage[6][3] = {
    {9, 3, 1},
    {9, 1, 3},
    {3, 9, 1},
    {3, 1, 9},
    {1, 9, 3},
    {1, 3, 9}
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> hp(3);

    for(int i = 0; i < N; i++) {
        cin >> hp[i];
    }

    memset(dp, -1, sizeof(dp));

    queue<array<int, 3>> q;
    q.push({hp[0], hp[1], hp[2]});

    dp[hp[0]][hp[1]][hp[2]] = 0;

    while(!q.empty()) {
        auto [a, b, c] = q.front();
        q.pop();

        if(a == 0 && b == 0 && c == 0) {
            cout << dp[a][b][c] << '\n';
            return 0;
        }

        for(int i = 0; i < 6; i++) {
            int na = max(0, a - damage[i][0]);
            int nb = max(0, b - damage[i][1]);
            int nc = max(0, c - damage[i][2]);

            if(dp[na][nb][nc] != -1) {
                continue;
            }

            dp[na][nb][nc] = dp[a][b][c] + 1;
            q.push({na, nb, nc});
        }
    }

    return 0;
}