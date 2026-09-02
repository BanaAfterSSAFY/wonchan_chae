import java.util.*;

class Solution {

    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};

    public long solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int size = n * m;

        int[] sb = new int[2];
        int snt = 0;

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(grid[y].charAt(x) == 'o') {
                    sb[snt++] = y * m + x;
                }
            }
        }

        int[][] dist = new int[2][size];

        Arrays.fill(dist[0], -1);
        Arrays.fill(dist[1], -1);

        int[] branchDist = {-1, -1};
        int snowballDistance = size + 1;

        boolean sharedBranch = false;

        int[] q = new int[size];

        for(int s = 0; s < 2; s++) {
            int head = 0;
            int tail = 0;

            int start = sb[s];

            dist[s][start] = 0;
            q[tail++] = start;

            while(head < tail) {
                int cur = q[head++];

                int y = cur / m;
                int x = cur % m;
                int d = dist[s][cur];

                int choices = 0;

                for(int dir = 0; dir < 4; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if(ny < 0 || ny >= n || nx < 0 || nx >= m) {
                        continue;
                    }

                    char nextChar = grid[ny].charAt(nx);

                    if(nextChar == '#') {
                        continue;
                    }

                    choices++;

                    int next = ny * m + nx;

                    if(dist[s][next] != -1) {
                        continue;
                    }

                    if(nextChar == 'o') {
                        snowballDistance = Math.min(
                            snowballDistance,
                            d + 1
                        );

                        continue;
                    }

                    dist[s][next] = d + 1;
                    q[tail++] = next;
                }

                if(choices >= 3) {
                    if(
                        s == 1 &&
                        dist[0][cur] != -1 &&
                        grid[y].charAt(x) == '.'
                    ) {
                        sharedBranch = true;
                    }

                    if(branchDist[s] == -1) {
                        branchDist[s] = d;
                    }
                }
            }
        }

        long shared = 0;
        long aOnly = 0;
        long bOnly = 0;

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(grid[y].charAt(x) != '.') {
                    continue;
                }

                int index = y * m + x;

                boolean a = dist[0][index] != -1;
                boolean b = dist[1][index] != -1;

                if(a && b) {
                    shared++;
                }
                else if(a) {
                    aOnly++;
                }
                else if(b) {
                    bOnly++;
                }
            }
        }

        if(branchDist[0] == -1 && branchDist[1] == -1) {
            return calculate(
                1,
                snowballDistance,
                shared,
                Math.min(aOnly, bOnly),
                Math.max(aOnly, bOnly),
                0
            );
        }

        if(
            sharedBranch ||
            (branchDist[0] != -1 && branchDist[1] != -1)
        ) {
            return calculate(
                2,
                snowballDistance,
                shared,
                aOnly,
                bOnly,
                0
            );
        }

        if(branchDist[0] == -1) {
            return calculate(
                3,
                snowballDistance,
                shared,
                aOnly,
                bOnly,
                branchDist[1]
            );
        }

        return calculate(
            3,
            snowballDistance,
            shared,
            bOnly,
            aOnly,
            branchDist[0]
        );
    }

    static long calculate(
        int type,
        long snowballDistance,
        long shared,
        long aOnly,
        long bOnly,
        long branchDistance
    ) {
        long answer = 0;
        long sum = shared + aOnly + bOnly;

        for(long total = snowballDistance - 1; total <= sum; total++) {
            if(
                type == 3 &&
                total > shared + aOnly + branchDistance + 1
            ) {
                shared++;
            }

            if(type == 2) {
                answer += total / 2 + 1;
            }
            else {
                answer += Math.min(
                    total / 2 + 1,
                    aOnly + shared + 1
                );
            }

            if(
                type == 1 &&
                total > bOnly + shared
            ) {
                answer -= total - bOnly - shared;
            }
        }

        return answer;
    }
}