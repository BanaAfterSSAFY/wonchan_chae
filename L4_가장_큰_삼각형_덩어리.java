import java.util.*;

class Solution {
    
    static class Triangle {
        
        public int x;
        public int y;
        public int state;
        
        public Triangle(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
    
    static int N, M;
    static int[][][] group;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] vec = {{1, 2}, {0, 2}, {0, 3}, {1, 3}};
    static int[][] pos = {{1, 0}, {0, 1}, {0, 1}, {1, 0}};
    
    public int solution(int[][] grid) {

        N = grid.length;
        M = grid[0].length;
        group = new int[N][M][2];

        int answer = 0;
        int num = 1;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 2; k++) {
                    if(group[i][j][k] == 0) {
                        answer = Math.max(answer, bfs(i, j, k, num, grid));
                        num++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    static int bfs(int x, int y, int state, int num, int[][] grid) {
        Queue<Triangle> q = new LinkedList<>();
        q.offer(new Triangle(x, y, state));
        group[x][y][state] = num;
        
        int size = 0;
        while(q.isEmpty() == false) {
            Triangle cur = q.peek();
            q.poll();
            size++;
            
            int shape;
            if(grid[cur.x][cur.y] == -1) {
                shape = cur.state == 0 ? 0 : 2;  
            }
            else {
                shape = cur.state == 0 ? 1 : 3;  
            }
            
            for(int i = 0; i < 2; i++) {
                int nd = vec[shape][i];
                int nx = cur.x + dir[nd][0];
                int ny = cur.y + dir[nd][1];

                if(isValid(nx, ny) == false) {
                    continue;
                }

                if(group[nx][ny][0] == num || group[nx][ny][1] == num) {
                    continue;
                }
                
                int flag;
                if(nd == 0 || nd == 1) {
                    flag = pos[shape][grid[nx][ny] == -1 ? 0 : 1]; 
                }
                else {
                    flag = nd == 2 ? 1 : 0;
                }

                group[nx][ny][flag] = num;
                q.offer(new Triangle(nx, ny, flag));
            }
        }
        
        return size;
    }
    
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }   
}