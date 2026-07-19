import java.util.*;

class Solution {

    int[][] X, Y;
    Set<Character> set = new TreeSet();
    char[][] map;

    public String solution(int m, int n, String[] board) {
        StringBuilder sb = new StringBuilder();
        map = new char[m][n];

        for(int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        X = new int[26][2];
        Y = new int[26][2];

        for(int i = 0; i < 26; i++) {
            X[i][0] = -1;
            X[i][1] = -1;
            Y[i][0] = -1;
            Y[i][1] = -1;
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char c = board[i].charAt(j);

                if('A' <= c && c <= 'Z') {
                    set.add(board[i].charAt(j));
                    if(X[c - 'A'][0] == -1) {
                        X[c - 'A'][0] = i;
                    }
                    else {
                        X[c - 'A'][1] = i;
                    }
                    if(Y[c - 'A'][0] == -1) {
                        Y[c - 'A'][0] = j;
                    }
                    else {
                        Y[c - 'A'][1] = j;
                    }
                }
            }
        }

        while(set.isEmpty() == false) {
            int size = set.size();
            for(char at : set) {
                if(check(at)) {
                    sb.append(at);
                    set.remove(at);
                    break;
                }
            }

            if(size == set.size()) {
                break;
            }
        }

        if(set.size() != 0) {
            return "IMPOSSIBLE";
        }
        return sb.toString();
    }

    public boolean check(char c) {
        int x1 = X[c - 'A'][0];
        int x2 = X[c - 'A'][1];
        int y1 = Y[c - 'A'][0];
        int y2 = Y[c - 'A'][1];

        if(x1 != x2 && y1 != y2) {
            if(solveX(y2, x1, x2, c) && solveY(x1, y1, y2, c)) {
                map[x1][y1] = '.';
                map[x2][y2] = '.';
                return true;
            }
            if(solveX(y1, x1, x2, c) && solveY(x2, y1, y2, c)) {
                map[x1][y1] = '.';
                map[x2][y2] = '.';
                return true;
            }
        }

        if(x1 == x2 && y1 != y2) {
            int x = X[c - 'A'][0];
            if(solveY(x, y1, y2, c)) {
                map[x1][y1] = '.';
                map[x2][y2] = '.';
                return true;
            }
        }

        if(x1 != x2 && y1 == y2) {
            int y = Y[c - 'A'][0];
            if(solveX(y, x1, x2, c)) {
                map[x1][y1] = '.';
                map[x2][y2] = '.';
                return true;
            }
        }

        return false;
    }

    public boolean solveY(int baseX, int y1, int y2, char c) {
        int max, min;
        if(y1 > y2) {
            max = y1;
            min = y2;
        }
        else {
            max = y2;
            min = y1;
        }
        
        for(int i = min; i <= max; i++) {
            if(map[baseX][i] != '.' && map[baseX][i] != c) {
                return false;
            }
        }
        return true;
    }

    public boolean solveX(int baseY, int x1, int x2, char c) {
        int max, min;
        if(x1 > x2) {
            max = x1;
            min = x2;
        }
        else {
            max = x2;
            min = x1;
        }
        
        for(int i = min; i <= max; i++) {
            if(map[i][baseY] != '.' && map[i][baseY] != c) {
                return false;
            }
        }
        return true;
    }
}