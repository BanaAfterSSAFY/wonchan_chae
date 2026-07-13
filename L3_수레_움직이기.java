import java.util.*;

class Solution {

    static boolean[][] arr, brr;
    static int cnt = 16;
    
    public int solution(int[][] maze) {
        int answer = 0;
        
        arr = new boolean[maze.length][maze[0].length];
        brr = new boolean[maze.length][maze[0].length];
        
        int[] red = new int[2];
        int[] Red = new int[2];
        int[] blue = new int[2];
        int[] Blue = new int[2];
        
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 1) {
                    red = new int[]{i, j};
                    arr[i][j] = true;
                }
                else if(maze[i][j] == 2) {
                    blue = new int[]{i, j};
                    brr[i][j] = true;
                }
                else if(maze[i][j] == 3) {
                    Red = new int[]{i, j};
                }
                else if(maze[i][j] == 4) {
                    Blue = new int[]{i, j};
                }
            }
        }
        
        solve(red, blue, Red, Blue, maze, 0);
        answer = cnt == 16 ? 0 : cnt;
         
        return answer;
    }
    
    public void solve(int[] red, int[] blue, int[] Red, int[] Blue, int[][] maze, int moveCnt) {
        int redX = red[1];
        int redY = red[0];
        int blueX = blue[1];
        int blueY = blue[0];
        
        if(redX == Red[1] && redY == Red[0] && blueX == Blue[1] && blueY == Blue[0]) {
            cnt = Math.min(cnt, moveCnt);
            return;
        }
        
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int maxX = maze[0].length;
        int maxY = maze.length;
        
        List<int[]> listR = new ArrayList<>();
        List<int[]> listB = new ArrayList<>();

        if(redX == Red[1] && redY == Red[0]) {
            listR.add(red);
        }
        else {
            for(int i = 0; i < 4; i++) {
                if(redX + dir[i][0] < 0 || redX + dir[i][0] >= maxX || redY + dir[i][1] < 0 || redY + dir[i][1] >= maxY || arr[redY + dir[i][1]][redX + dir[i][0]] || maze[redY + dir[i][1]][redX + dir[i][0]] == 5) {
                    continue;
                }
                listR.add(new int[]{redY + dir[i][1], redX + dir[i][0]});
            }
        }

        if(blueX == Blue[1] && blueY == Blue[0]) {
            listB.add(blue);
        }
        else {
            for(int i = 0; i < 4; i++) {
                if(blueX + dir[i][0] < 0 || blueX + dir[i][0] >= maxX || blueY + dir[i][1] < 0 || blueY + dir[i][1] >= maxY || brr[blueY + dir[i][1]][blueX + dir[i][0]] || maze[blueY + dir[i][1]][blueX + dir[i][0]] == 5) {
                    continue;
                }
                listB.add(new int[]{blueY + dir[i][1], blueX + dir[i][0]});
            }
        }

        for(int[] rt : listR) {
            for(int[] bt : listB) {
                if(rt[0] == bt[0] && rt[1] == bt[1]) {
                    continue;
                }

                if(rt[0] == blueY && rt[1] == blueX && bt[0] == redY && bt[1] == redX) {
                    continue;
                }

                arr[rt[0]][rt[1]] = true;
                brr[bt[0]][bt[1]] = true;

                solve(rt, bt, Red, Blue, maze, moveCnt+1);
                
                arr[rt[0]][rt[1]] = false;
                brr[bt[0]][bt[1]] = false;
            }
        }
    }
}