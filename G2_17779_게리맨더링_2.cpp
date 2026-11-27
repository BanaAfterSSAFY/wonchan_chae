#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    int a[21][21];
    int total = 0;

    for(int r = 1; r <= n; r++) {
        for(int c = 1; c <= n; c++) {
            cin >> a[r][c];
            total += a[r][c];
        }
    }

    int answer = INT_MAX;

    for(int x = 1; x <= n - 2; x++) {
        for(int y = 2; y < n; y++) {
            for(int d1 = 1; y - d1 >= 1; d1++) {
                for(int d2 = 1; y + d2 <= n; d2++) {
                    if(x + d1 + d2 > n) {
                        break;
                    }

                    bool boundary[21][21] = {};
                    int population[5] = {};

                    for(int i = 0; i <= d1; i++) {
                        boundary[x + i][y - i] = true;
                        boundary[x + d2 + i][y + d2 - i] = true;
                    }

                    for(int i = 0; i <= d2; i++) {
                        boundary[x + i][y + i] = true;
                        boundary[x + d1 + i][y - d1 + i] = true;
                    }

                    for(int r = 1; r < x + d1; r++) {
                        for(int c = 1; c <= y; c++) {
                            if(boundary[r][c]) {
                                break;
                            }
                            population[0] += a[r][c];
                        }
                    }

                    for(int r = 1; r <= x + d2; r++) {
                        for(int c = n; c > y; c--) {
                            if(boundary[r][c]) {
                                break;
                            }
                            population[1] += a[r][c];
                        }
                    }

                    for(int r = x + d1; r <= n; r++) {
                        for(int c = 1; c < y - d1 + d2; c++) {
                            if(boundary[r][c]) {
                                break;
                            }
                            population[2] += a[r][c];
                        }
                    }

                    for(int r = x + d2 + 1; r <= n; r++) {
                        for(int c = n; c >= y - d1 + d2; c--) {
                            if(boundary[r][c]) {
                                break;
                            }
                            population[3] += a[r][c];
                        }
                    }

                    population[4] = total;
                    for(int i = 0; i < 4; i++) {
                        population[4] -= population[i];
                    }

                    int maximum = *max_element(population, population + 5);
                    int minimum = *min_element(population, population + 5);
                    answer = min(answer, maximum - minimum);
                }
            }
        }
    }

    cout << answer << '\n';

    return 0;
}