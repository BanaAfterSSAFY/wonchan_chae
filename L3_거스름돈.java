import java.util.*;

class Solution {

    private int[][] arr;

    public int solution(int n, int[] money) {

        arr = new int[money.length + 1][n + 1];

        for(int i = 1; i <= money.length; i++) {
            for(int j = 0; j <= n; j++) {
                if(j == 0) {
                    arr[i][j] = 1;
                }
                else if (j - money[i - 1] >= 0) {
                    arr[i][j] = (arr[i - 1][j] + arr[i][j - money[i - 1]]) % 1000000007;
                }
                else {
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }

        return arr[money.length][n];
    }
}