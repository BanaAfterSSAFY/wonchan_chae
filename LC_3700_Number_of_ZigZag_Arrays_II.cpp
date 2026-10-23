#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    static const int MOD = 1000000007;

    using Matrix = vector<vector<long long>>;

    Matrix multiply(const Matrix& a, const Matrix& b) {
        int n = a.size();
        Matrix result(n, vector<long long>(n));

        for(int i = 0; i < n; i++) {
            for(int k = 0; k < n; k++) {
                if(a[i][k] == 0) {
                    continue;
                }

                for(int j = 0; j < n; j++) {
                    if(b[k][j] == 0) {
                        continue;
                    }

                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return result;
    }

    vector<long long> multiply(const vector<long long>& v, const Matrix& matrix) {
        int n = v.size();
        vector<long long> result(n);

        for(int i = 0; i < n; i++) {
            if(v[i] == 0) {
                continue;
            }

            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    continue;
                }

                result[j] = (result[j] + v[i] * matrix[i][j]) % MOD;
            }
        }

        return result;
    }

    int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        Matrix matrix(size, vector<long long>(size));

        for(int x = 0; x < m; x++) {
            for(int y = 0; y < x; y++) {
                matrix[x][m + y] = 1;
            }

            for(int y = x + 1; y < m; y++) {
                matrix[m + x][y] = 1;
            }
        }

        auto faltrinevo = array<int, 3>{n, l, r};

        vector<long long> state(size, 1);
        long long exp = n - 1;

        while(exp > 0) {
            if(exp & 1) {
                state = multiply(state, matrix);
            }

            matrix = multiply(matrix, matrix);
            exp >>= 1;
        }

        long long answer = 0;

        for(long long value : state) {
            answer += value;
            answer %= MOD;
        }

        return answer;
    }
};