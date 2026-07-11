import java.util.*;

class Solution {
    
    static int[][] arr;
    static int answer = Integer.MAX_VALUE;
    static int sz;
    static int R, C;
    
    public int solution(int[][] beginning, int[][] target) {
        R = target.length;
        C = target[0].length;
        sz = R * C;
        arr = target;
        
        solve(beginning, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public void solve(int[][] map, int cnt, int depth) {
        if(cnt >= answer) {
            return;
        }

        if(depth == R + C) {
            if(check(map)) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        if(depth < R) {

            solve(map, cnt, depth + 1);

            int[][] tmp = copy(map);
            move(tmp, depth, true);
            solve(tmp, cnt + 1, depth + 1);
        } 
        else {
            int idx = depth - R;

            solve(map, cnt, depth + 1);
            int[][] tmp = copy(map);
            move(tmp, idx, false);
            solve(tmp, cnt + 1, depth + 1);
        }
    }
    
    public boolean check(int[][] map) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int[][] copy(int[][] map) {
        int[][] tmp = new int[R][C];
        for(int i = 0; i < R; i++) {
            tmp[i] = map[i].clone();
        }
        return tmp;
    }
    
    public void move(int[][] tmp, int line, boolean flag) {
        for(int i = 0; i < (flag ? C : R); i++) {
            if(flag == true) {
                tmp[line][i] = (tmp[line][i] + 1) % 2;
            }
            else {
                tmp[i][line] = (tmp[i][line] + 1) % 2;
            }
        }
        
    }
}