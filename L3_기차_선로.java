import java.util.*;

class Solution {

    static int N, M;
    static int[][] grid;
    static int[][] check;
    static int answer;

    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int[][] placeable = {
        {1, 3, 4, 7},
        {1, 3, 5, 6},
        {2, 3, 4, 5},
        {2, 3, 6, 7}
    };

    public int solution(int[][] grid) {
        this.grid = grid;

        N = grid.length;
        M = grid[0].length;

        check = new int[N][M];

        check[0][0] = 1;
        check[N - 1][M - 1] = 1;

        answer = 0;

        dfs(0, 1, 0);

        return answer;
    }

    static boolean direction(int rail, int d) {

        if(rail == 1) {
            return d == 0 || d == 1;
        }

        if(rail == 2) {
            return d == 3 || d == 2;
        }

        if(rail == 3) {
            return true;
        }

        if(rail == 4) {
            return d == 0 || d == 2;
        }

        if(rail == 5) {
            return d == 1 || d == 2;
        }

        if(rail == 6) {
            return d == 1 || d == 3;
        }

        if(rail == 7) {
            return d == 0 || d == 3;
        }

        return false;
    }

    static int[] next(int y, int x, int d) {

        int rail = grid[y][x];

        if(rail == 4) {
            d = (d == 2) ? 1 : 3;
        }

        else if(rail == 5) {
            d = (d == 2) ? 0 : 3;
        }

        else if(rail == 6) {
            d = (d == 3) ? 0 : 2;
        }

        else if(rail == 7) {
            d = (d == 3) ? 1 : 2;
        }

        int ny = y + dir[d][0];
        int nx = x + dir[d][1];

        return new int[]{ny, nx, d};
    }

    static boolean isCheck() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(1 <= grid[y][x] && grid[y][x] <= 7) {
                    if(grid[y][x] == 3) {
                        if(check[y][x] != 2) {
                            return false;
                        }

                    }
                    else {
                        if(check[y][x] < 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    static void dfs(int y, int x, int d) {
        if(y < 0 || y >= N || x < 0 || x >= M) {
            return;
        }

        if(grid[y][x] == -1) {
            return;
        }

        if(y == N - 1 && x == M - 1) {
            if(direction(grid[y][x], d) && isCheck()) {
                answer++;
            }
            return;
        }

        check[y][x]++;

        if(grid[y][x] == 0) {
            for(int at : placeable[d]) {
                grid[y][x] = at;
                int[] next = next(y, x, d);

                dfs(next[0], next[1], next[2]);

                grid[y][x] = 0;
            }

        }
        else {
            if(direction(grid[y][x], d)) {
                int[] next = next(y, x, d);

                dfs(next[0], next[1], next[2]);
            }
        }
        check[y][x]--;
    }
}