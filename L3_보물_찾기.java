import java.util.function.Function;

class Solution {

    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {

        int n = depth.length;

        long[][] dp = new long[n][n];
        int[][] arr = new int[n][n];

        for(int len = 1; len <= n; len++) {

            for(int l = 0; l + len - 1 < n; l++) {

                int r = l + len - 1;

                dp[l][r] = Long.MAX_VALUE;

                for(int k = l; k <= r; k++) {

                    long left = 0;
                    long right = 0;

                    if(l <= k - 1) {
                        left = dp[l][k - 1];
                    }

                    if(k + 1 <= r) {
                        right = dp[k + 1][r];
                    }

                    long cost = depth[k] + Math.max(left, right);

                    if(cost < dp[l][r]) {
                        dp[l][r] = cost;
                        arr[l][r] = k;
                    }
                }
            }
        }

        int l = 0;
        int r = n - 1;

        while(l <= r) {

            int k = arr[l][r];

            int result = excavate.apply(k + 1);

            if(result == 0) {
                return k + 1;
            }

            if(result == -1) {
                r = k - 1;
            }
            else {
                l = k + 1;
            }
        }

        return 0;
    }
}