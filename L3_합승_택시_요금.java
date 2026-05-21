import java.util.*;

class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        int[][] arr = new int[n+1][n+1];

        for(int i = 1; i < n + 1; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
            arr[i][i] = 0;
        }

        for(int i = 1; i < fares.length + 1; i++) {
            arr[fares[i - 1][0]][fares[i - 1][1]] = fares[i - 1][2];
            arr[fares[i - 1][1]][fares[i - 1][0]] = fares[i - 1][2];
        }

        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(arr[j][i] == Integer.MAX_VALUE) {
                    continue;
                }

                for(int k = 1; k < n + 1; k++) {
                    if(arr[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }

                    arr[j][k] = Math.min(arr[j][i] + arr[i][k], arr[j][k]);
                }
            }
        }

        for(int i = 1; i < n + 1; i++) {
            answer = Math.min(arr[s][i] + arr[i][a] + arr[i][b], answer);            
        }

        return answer;
    }
}