import java.util.*;

class Solution {

    static final int ps = 1;
    static final int pe = 2;
    static final int bs = 4;
    static final int be = 8;
    
    static int[][] map;
    static int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        
        map = new int[N+2][N+2];
        
        for(int[] at : build_frame) {
            int x = at[0];
            int y = at[1];
            int a = at[2];
            int b = at[3];
        
            if(b == 1) {
                if(a == 0 && solveP(x, y)) {
                    map[x][y] |= ps;
                    map[x][y+1] |= pe;
                }
                else if(a == 1 && solveB(x, y)) {
                    map[x][y] |= bs;
                    map[x+1][y] |= be;
                }
                continue;
            }
            
            boolean flag = false;
            
            if(a == 0) {                
                map[x][y+1] -= pe; 
                
                if((map[x][y+1] & be) != 0 && solveB(x-1, y+1) == false) {
                    flag = true;
                }
                else if((map[x][y+1] & bs) != 0 && solveB(x, y+1) == false) {
                    flag = true;
                }
                else if((map[x][y+1] & ps) != 0 && solveP(x, y+1) == false) {
                    flag = true;
                }
                
                if(flag == true) {
                    map[x][y+1] += pe;
                }
                else {
                    map[x][y] -= ps;
                }
                continue;
            }
            
            map[x][y] -= bs;
            map[x+1][y] -= be;
            
            if((map[x][y] & be) != 0 && solveB(x-1, y) == false) {
                flag = true;
            }
            else if((map[x][y] & ps) != 0 && solveP(x, y) == false) {
                flag = true;
            }
            else if((map[x+1][y] & bs) != 0 && solveB(x+1, y) == false) {
                flag = true;
            }
            else if((map[x+1][y] & ps) != 0 && solveP(x+1, y) == false) {
                flag = true;
            }
            
            if(flag == true) {
                map[x][y] += bs;
                map[x+1][y] += be;
            }
        }
        
        ArrayList<int[]> answer = new ArrayList<>();
        
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if((map[i][j] & ps) > 0) {
                    answer.add(new int[] {i, j, 0});
                }
                if((map[i][j] & bs) != 0) {
                    answer.add(new int[] {i, j, 1});
                }
            }
        }

        return answer.toArray(new int[answer.size()][]);
    }
    
    private boolean solveP(int x, int y) {
        if(y == 0) {
            return true;
        }        
        else if((map[x][y] & (bs + be + pe )) != 0) {
            return true;
        }
        
        return false;
    }
    
    private boolean solveB(int x, int y) {
        if((map[x][y] & pe) != 0 || (map[x+1][y] & pe) != 0) {
            return true;
        }
        else if((map[x][y] & be) != 0  && (map[x+1][y] & bs) != 0) {
            return true;
        }
        
        return false;
    }
}