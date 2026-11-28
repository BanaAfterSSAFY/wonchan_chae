#include <bits/stdc++.h>
using namespace std;

int result[6][3];
vector<pair<int, int>> matches;
bool possible;

void dfs(int idx) {
    if(possible) {
        return;
    }

    if(idx == 15) {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                if(result[i][j] != 0) {
                    return;
                }
            }
        }

        possible = true;
        return;
    }

    auto [a, b] = matches[idx];

    if(result[a][0] > 0 && result[b][2] > 0) {
        result[a][0]--;
        result[b][2]--;

        dfs(idx + 1);

        result[a][0]++;
        result[b][2]++;
    }

    if(result[a][1] > 0 && result[b][1] > 0) {
        result[a][1]--;
        result[b][1]--;

        dfs(idx + 1);

        result[a][1]++;
        result[b][1]++;
    }

    if(result[a][2] > 0 && result[b][0] > 0) {
        result[a][2]--;
        result[b][0]--;

        dfs(idx + 1);

        result[a][2]++;
        result[b][0]++;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    for(int i = 0; i < 6; i++) {
        for(int j = i + 1; j < 6; j++) {
            matches.push_back({i, j});
        }
    }

    for(int t = 0; t < 4; t++) {
        possible = false;
        bool valid = true;

        int win = 0;
        int draw = 0;
        int lose = 0;

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                cin >> result[i][j];
            }

            if(result[i][0] + result[i][1] + result[i][2] != 5) {
                valid = false;
            }

            win += result[i][0];
            draw += result[i][1];
            lose += result[i][2];
        }

        if(win != lose || draw % 2 != 0) {
            valid = false;
        }

        if(valid) {
            dfs(0);
        }

        cout << (possible ? 1 : 0);

        if(t < 3) {
            cout << ' ';
        }
    }

    cout << '\n';

    return 0;
}