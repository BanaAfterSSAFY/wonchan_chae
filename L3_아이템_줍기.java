import java.util.*;

class Solution {
  
    static char[][] map;

    public int solution(int[][] rectangle, int X, int Y, int itemX, int itemY) {
        map = new char[101][101];

        for(int i = 0; i < rectangle.length; i++) {
            int y1 = rectangle[i][1];
            int x1 = rectangle[i][0];
            int y2 = rectangle[i][3];
            int x2 = rectangle[i][2];  
            solve(y1 * 2, x1 * 2, y2 * 2, x2 * 2);
        }
        
        return bfs(Y * 2, X * 2, itemY * 2, itemX * 2);
    }

    public static int bfs(int Y,int X,int findY,int findX) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{Y, X, 0});

        boolean[][] check = new boolean[101][101];
        
        while(q.isEmpty() == false) {
            Integer temp[] = q.poll();
            int ty = temp[0];
            int tx = temp[1];
            int cnt = temp[2];
            
            if(ty == findY && tx == findX) {
                return cnt / 2;
            }

            for(int i = 0; i < 4; i++) {
                int ny = ty + dir[i][0];
                int nx = tx + dir[i][1];

                if(ny < 0 || nx < 0 || ny >= map.length || nx >= map[0].length) {
                    continue;
                }
                if(check[ny][nx] == true || map[ny][nx] != '2') {
                    continue;
                }

                check[ny][nx] = true;
                q.add(new Integer[]{ny, nx, cnt + 1});
            }
        }
        return 0;
    }

    public static void solve(int y1, int x1, int y2, int x2) {
        
        for(int i = y1; i <= y2; i++) {
            for(int j = x1; j <= x2; j++) {
            	if(map[i][j] == '1') {
                    continue;
                }

                map[i][j] = '1';
                
                if(i == y1 || i == y2 || j == x1 || j == x2) {
                    map[i][j] = '2';
                }
            }
        }
    }
}