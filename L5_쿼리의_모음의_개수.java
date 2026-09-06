import java.util.*;

class Solution {

    int q;
    int n;

    long[][] comb;
    long[][] pow;

    public int solution(int q, int[] a) {
        this.q = q;
        this.n = a.length;

        buildComb();
        buildPow();

        TreeSet<Integer> set = new TreeSet<>();

        for(int value : a) {
            set.add(value);
        }

        long totalQueries = 0;

        for(int l = 0; l < n; l++) {
            int min = Integer.MAX_VALUE;

            for(int r = l; r < n; r++) {
                min = Math.min(min, a[r]);
                totalQueries += min;
            }
        }

        long requiredQueries = 0;

        for(int x : set) {
            requiredQueries += countIntervals(a, x);
        }

        long filler = (totalQueries - requiredQueries) % 998244353;

        long[] dp = new long[q + 1];
        dp[0] = 1;

        long[] fillerWays = new long[q + 1];
        fillerWays[0] = 1;

        for(int i = 1; i <= q; i++) {
            fillerWays[i] = fillerWays[i - 1] * filler % 998244353;
        }

        dp = combine(dp, fillerWays);

        for(int x : set) {
            long[] ways = makeWays(a, x);
            dp = combine(dp, ways);
        }

        return (int)dp[q];
    }

    long[] makeWays(int[] a, int x) {
        long[] result = new long[q + 1];
        result[0] = 1;

        int index = 0;

        while(index < n) {
            if(a[index] < x) {
                index++;
                continue;
            }

            int left = index;

            while(index < n && a[index] >= x) {
                index++;
            }

            int right = index - 1;

            ArrayList<Integer> marks = new ArrayList<>();

            for(int i = left; i <= right; i++) {
                if(a[i] == x) {
                    marks.add(i);
                }
            }

            int length = right - left + 1;
            int total = length * (length + 1) / 2;

            long[] component = new long[q + 1];

            if(marks.isEmpty()) {
                System.arraycopy(pow[total], 0, component, 0, q + 1);
            }
            else {
                int m = marks.size();

                long[][] last = new long[m][q + 1];

                for(int j = 0; j < m; j++) {
                    int firstLength = marks.get(j) - left;
                    int first = firstLength * (firstLength + 1) / 2;

                    for(int k = 0; k <= q; k++) {
                        last[j][k] = (998244353 - pow[first][k]) % 998244353;
                    }

                    for(int i = 0; i < j; i++) {
                        int gapLength = marks.get(j) - marks.get(i) - 1;
                        int gap = gapLength * (gapLength + 1) / 2;

                        addShift(last[j], last[i], gap, -1);
                    }
                }

                System.arraycopy(pow[total], 0, component, 0, q + 1);

                for(int j = 0; j < m; j++) {
                    int tailLength = right - marks.get(j);
                    int tail = tailLength * (tailLength + 1) / 2;

                    addShift(component, last[j], tail, 1);
                }
            }

            result = combine(result, component);
        }

        return result;
    }

    void addShift(long[] target, long[] source, int add, int sign) {
        for(int k = 0; k <= q; k++) {
            long value = 0;

            for(int d = 0; d <= k; d++) {
                value += comb[k][d] * source[d] % 998244353 * pow[add][k - d] % 998244353;

                if(value >= 998244353) {
                    value -= 998244353;
                }
            }

            if(sign == 1) {
                target[k] += value;

                if(target[k] >= 998244353) {
                    target[k] -= 998244353;
                }
            }
            else {
                target[k] -= value;

                if(target[k] < 0) {
                    target[k] += 998244353;
                }
            }
        }
    }

    long[] combine(long[] a, long[] b) {
        long[] result = new long[q + 1];

        for(int total = 0; total <= q; total++) {
            long value = 0;

            for(int take = 0; take <= total; take++) {
                value += comb[total][take]
                    * a[take] % 998244353
                    * b[total - take] % 998244353;

                if(value >= 998244353) {
                    value -= 998244353;
                }
            }

            result[total] = value;
        }

        return result;
    }

    long countIntervals(int[] a, int x) {
        long result = 0;
        int index = 0;

        while(index < n) {
            if(a[index] < x) {
                index++;
                continue;
            }

            int start = index;

            while(index < n && a[index] >= x) {
                index++;
            }

            long length = index - start;

            result += length * (length + 1) / 2;
        }

        return result;
    }

    void buildComb() {
        comb = new long[q + 1][q + 1];

        for(int i = 0; i <= q; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for(int j = 1; j < i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];

                if(comb[i][j] >= 998244353) {
                    comb[i][j] -= 998244353;
                }
            }
        }
    }

    void buildPow() {
        int max = n * (n + 1) / 2;

        pow = new long[max + 1][q + 1];

        for(int value = 0; value <= max; value++) {
            pow[value][0] = 1;

            for(int k = 1; k <= q; k++) {
                pow[value][k] = pow[value][k - 1] * value % 998244353;
            }
        }
    }
}