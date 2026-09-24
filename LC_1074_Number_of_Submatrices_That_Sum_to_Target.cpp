#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numSubmatrixSumTarget(vector<vector<int>>& matrix, int target) {
        int n = matrix.size();
        int m = matrix[0].size();

        for(int r = 0; r < n; r++) {
            for(int c = 1; c < m; c++) {
                matrix[r][c] += matrix[r][c - 1];
            }
        }

        int answer = 0;

        for(int left = 0; left < m; left++) {
            for(int right = left; right < m; right++) {
                unordered_map<int, int> count;
                count[0] = 1;

                int sum = 0;

                for(int r = 0; r < n; r++) {
                    int rowSum = matrix[r][right];

                    if(left > 0) {
                        rowSum -= matrix[r][left - 1];
                    }

                    sum += rowSum;

                    if(count.count(sum - target)) {
                        answer += count[sum - target];
                    }

                    count[sum]++;
                }
            }
        }

        return answer;
    }
};