import java.util.*;

class Solution {

    static int sx, sy, ex, ey, l;
    static int tn, tm;
    static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

    static String[] arr = {"d", "l", "r", "u"};

    static String ans = null;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        sx = x;
        sy = y;
        ex = r;
        ey = c;
        
        l = k;
        tn = n;
        tm = m;

        if((Math.abs(sx - ex) + Math.abs(sy - ey)) % 2 != k % 2) {
            return "impossible";
        }

        if((Math.abs(sx - ex) + Math.abs(sy - ey)) > k) {
            return "impossible";
        }

        dfs(sx, sy, new StringBuffer(""));

        return ans;
    }

    public static void dfs(int cx, int cy, StringBuffer s) {

        if(ans != null) {
            return;
        }

        if(s.length() == l && cx == ex && cy == ey) {
            ans = s.toString();
            return;
        }
        else if(s.length() == l) {
            return;
        }

        int d = Math.abs(ex-cx) + Math.abs(ey-cy);
        
        if(l - s.length() < d) {
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = cx + dir[i][0];
            int ny = cy + dir[i][1];
            
            if(nx < 1 || nx > tn || ny < 1 || ny > tm) {
                continue;
            }

            s.append(arr[i]);
            dfs(nx, ny, s);
            s.delete(s.length() - 1, s.length());
        }
    }
}