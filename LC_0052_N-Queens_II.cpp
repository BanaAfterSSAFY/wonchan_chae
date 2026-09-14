class Solution {
public:
    int answer = 0;

    void dfs(int row, int n, int cols, int diag1, int diag2) {
        if (row == n) {
            answer++;
            return;
        }

        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);

        while (available) {
            int bit = available & -available;
            available -= bit;

            dfs(
                row + 1,
                n,
                cols | bit,
                (diag1 | bit) << 1,
                (diag2 | bit) >> 1
            );
        }
    }

    int totalNQueens(int n) {
        dfs(0, n, 0, 0, 0);
        return answer;
    }
};