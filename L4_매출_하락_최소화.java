import java.util.*;

class Solution {

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    int[][] dp;
    int[] arr;

    public int solution(int[] sales, int[][] links) {

        this.arr = sales;
        this.dp = new int[sales.length][2];
        list.clear();

        for(int i = 0, l = sales.length; i < l; i++) {
            list.add(new ArrayList<>());
        }

        for(int[] at : links) {
            list.get(at[0] - 1).add(at[1] - 1);
        }

        solve(0);

        return Math.min(dp[0][0], dp[0][1]);
    }

    public void solve(int cnt) {

        if(list.get(cnt).isEmpty()) {
            dp[cnt][0] = 0;
            dp[cnt][1] = arr[cnt];
            return;
        }

        int sum = 0;
        int minDiff = Integer.MAX_VALUE;

        for(int at : list.get(cnt)) {
            solve(at);
            
            sum += Math.min(dp[at][0], dp[at][1]);
            
            minDiff = Math.min(minDiff, dp[at][1] - dp[at][0]);
        }

        dp[cnt][1] = arr[cnt] + sum;
        dp[cnt][0] = sum + Math.max(0, minDiff);
    }
}