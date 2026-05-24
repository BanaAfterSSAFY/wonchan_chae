import java.util.*;

class Solution { 

    public int solution(int n, int[][] results) {

        boolean[][] arr = new boolean[n][n];
        int answer = 0;
 
        for(int i = 0; i < results.length; i++) {
            int a = results[i][0] - 1;
            int b = results[i][1] - 1;
            arr[a][b] = true;
        }
 
        for(int j = 0; j < n; j++) {
            for(int i = 0; i < n; i++) {
                for(int k = 0; k < n; k++) {
                    if(arr[i][j] && arr[j][k]) {
                        arr[i][k] = true;
                    }
                }
            }
        }
 
        for(int i = 0; i < n; i++) {
            int cnt = 0;
 
            for(int j = 0; j < n; j++) {
                if(arr[i][j] || arr[j][i]) {
                    cnt++;
                }
            }
 
            if(cnt == n - 1) {
                answer++;
            }
        }
 
        return answer;
    }
}