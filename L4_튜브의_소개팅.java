import java.util.*;

class Solution {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int[][] map = new int[m][n];
        Queue<int[]> q = new ArrayDeque();
        q.add(new int[] {0, 0});
      
        long[][] dist = new long[m][n];
        for(int i = 0 ; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;
        
        while(q.isEmpty() == false) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            if(x == m - 1 && y == n - 1) {
                if(dist[x][y] <= s) {
                    break;
                }
            }
            for(int d = 0; d < 4; d++) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];
                
                if(0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if(time_map[nx][ny] == -1) {
                        continue;
                    }

                    if(dist[nx][ny] > dist[x][y] + time_map[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + time_map[nx][ny];
                        q.add(new int[] {nx, ny});
                        map[nx][ny] = map[x][y] + 1;
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = map[m - 1][n - 1];
        answer[1] = (int)dist[m - 1][n - 1];
      
        return answer;
    }
    
}