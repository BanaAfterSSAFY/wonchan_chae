import java.util.*;

class Solution {

    public int solution(int n, int[][] triangle, int[][] v) {
        if(n == 0) {
            return 1;
        }

        if(n == 1) {
            return 3;
        }

        long[][] t = new long[3][2];

        for(int i = 0; i < 3; i++) {
            t[i][0] = triangle[i][0];
            t[i][1] = triangle[i][1];
        }

        if(solve(t[1][0] - t[0][0], t[1][1] - t[0][1], t[2][0] - t[0][0], t[2][1] - t[0][1]) < 0) {
            long[] temp = t[1];
            t[1] = t[2];
            t[2] = temp;
        }

        int[][] order = new int[3][n];
        int[][] rank = new int[3][n];

        for(int vertex = 0; vertex < 3; vertex++) {
            Integer[] indices = new Integer[n];

            for(int i = 0; i < n; i++) {
                indices[i] = i;
            }

            final long ox = t[vertex][0];
            final long oy = t[vertex][1];

            Arrays.sort(indices, (a, b) -> {
                long ax = (long)v[a][0] - ox;
                long ay = (long)v[a][1] - oy;
                long bx = (long)v[b][0] - ox;
                long by = (long)v[b][1] - oy;

                long c = solve(ax, ay, bx, by);

                return c > 0 ? -1 : 1;
            });

            for(int i = 0; i < n; i++) {
                order[vertex][i] = indices[i];
                rank[vertex][indices[i]] = i;
            }
        }

        int answer = 0;

        for(int i = 0; i < 3; i++) {
            int next = (i + 1) % 3;

            answer++;
            answer += rank[next][order[i][n - 1]];
        }

        for(int first = 1; first < 3; first++) {
            int second = (first + 1) % 3;

            boolean[] inFirst = new boolean[n];

            for(int j = 0; j < n - 1; j++) {
                int point = order[first][j];

                inFirst[point] = true;

                int unionCount = j + 1;
                int limit = rank[second][point];

                for(int k = 0; k < limit; k++) {
                    int nextPoint = order[second][k];

                    if(!inFirst[nextPoint]) {
                        unionCount++;
                    }

                    if(unionCount == n) {
                        answer++;
                    }
                }
            }
        }

        boolean[] inFirst = new boolean[n];

        for(int j = 0; j < n - 1; j++) {
            int firstPoint = order[0][j];

            inFirst[firstPoint] = true;

            int[] notFirstPrefix = new int[n + 1];

            for(int i = 0; i < n; i++) {
                int point = order[2][i];

                notFirstPrefix[i + 1] = notFirstPrefix[i] + (inFirst[point] ? 0 : 1);
            }

            boolean[] covered = inFirst.clone();

            int maxMissing = n - 1;

            while(maxMissing >= 0 && covered[order[2][maxMissing]]) {
                maxMissing--;
            }

            int unionCount = j + 1;
            int breakPosition = n;

            int secondLimit = rank[1][firstPoint];

            for(int k = 0; k < secondLimit; k++) {
                int secondPoint = order[1][k];

                if(inFirst[secondPoint]) {
                    breakPosition = Math.min(breakPosition, rank[2][secondPoint]);
                }
                else if(!covered[secondPoint]) {
                    covered[secondPoint] = true;
                    unionCount++;

                    while(maxMissing >= 0 && covered[order[2][maxMissing]]) {
                        maxMissing--;
                    }
                }

                if(unionCount == n) {
                    answer++;
                }

                int thirdLimit = rank[2][secondPoint];

                int start = Math.max(0, maxMissing);
                int end = Math.min(thirdLimit, breakPosition);

                if(start < end) {
                    answer += notFirstPrefix[end] - notFirstPrefix[start];
                }
            }
        }

        return answer;
    }

    static long solve(long ax, long ay, long bx, long by) {
        return ax * by - ay * bx;
    }
}