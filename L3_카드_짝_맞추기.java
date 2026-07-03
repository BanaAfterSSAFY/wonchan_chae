import java.util.*;

class Solution {

    static int answer = Integer.MAX_VALUE;
    static boolean[] arr = new boolean [7];
    static boolean[] check = new boolean [7];
    static int[][] rcd = new int [7][4];
    static int[] seq;
    static int[][] map;
    static int size;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] board, int r, int c) {
        map = board; 
        size = board.length;
        init();
        solve(0, r, c, 0);
        return answer;
    }

    public static int game(int sx, int sy, int ex, int ey, int cx, int cy) {
        int num = map[sx][sy];
        int sum = 0;
        int[][] wal = new int [size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                wal[i][j] = 16;
            }
        }

        wal[cx][cy] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int [] {cx, cy});
        
        while(q.isEmpty() == false) {
            int [] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int x = cur[0] + dir[i][0];
                int y = cur[1] + dir[i][1];
                
                if(validation(x, y) == false) {
                    continue;
                }

                if(wal[x][y] > wal[cur[0]][cur[1]] + 1) {
                    wal[x][y] = wal[cur[0]][cur[1]] + 1;
                    q.add(new int [] {x, y});
                }
                
                if(map[x][y] != 0) {
                    continue; 
                }
                
                boolean flag = false;

                while(true) {
                    x += dir[i][0];
                    y += dir[i][1];
                    
                    if(validation(x, y) == false) {
                        break;
                    }

                    if(map[x][y] != 0) {
                        flag = true;
                        break;
                    }
                }

                if(flag == false) {
                    x -= dir[i][0];
                    y -= dir[i][1];
                }

                if(wal[x][y] > wal[cur[0]][cur[1]] + 1) {
                    wal[x][y] = wal[cur[0]][cur[1]] + 1;
                    q.add(new int []{x, y});
                }
            }
        }
        sum += wal[sx][sy];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                wal[i][j] = 16;
            }
        }

        wal[sx][sy] = 0;
        q.add(new int [] {sx, sy});
        
        while(q.isEmpty() == false) {
            int [] cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int x = cur[0] + dir[i][0];
                int y = cur[1] + dir[i][1];
                
                if(validation(x, y) == false) {
                    continue;
                }

                if(wal[x][y] > wal[cur[0]][cur[1]] + 1) {
                    wal[x][y] = wal[cur[0]][cur[1]] + 1;
                    q.add(new int [] {x, y});
                }
                
                if(map[x][y] != 0) {
                    continue; 
                }
                
                boolean flag = false;

                while(true) {
                    x += dir[i][0];
                    y += dir[i][1];
                    
                    if(validation(x, y) == false) {
                        break;
                    }

                    if(map[x][y] != 0) {
                        flag = true;
                        break;
                    }
                }

                if(flag == false) {
                    x -= dir[i][0];
                    y -= dir[i][1];
                }

                if(wal[x][y] > wal[cur[0]][cur[1]] + 1) {
                    wal[x][y] = wal[cur[0]][cur[1]] + 1;
                    q.add(new int []{x, y});
                }
            }
        }

        map[sx][sy] = 0;
        map[ex][ey] = 0; 
        sum += wal[ex][ey];
        sum += 2;
        return sum;
    }

    public static boolean validation(int nx, int ny) {
        if(0 <= nx && 0 <= ny && nx < size && ny < size) {
            return true;
        }
        return false;
    }    

    public static void solve(int depth, int x, int y, int cnt) {
        if(depth == seq.length) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for(int i = 1; i <= 6; i++) {
            if(arr[i] == false || check[i]) {
                continue;
            }

            check[i] = true;
            seq[depth] = i;
            
            int tmp1 = game(rcd[i][0], rcd[i][1], rcd[i][2], rcd[i][3], x, y);
            
            solve(depth + 1, rcd[i][2], rcd[i][3], cnt + tmp1);

            map[rcd[i][0]][rcd[i][1]] = i;
            map[rcd[i][2]][rcd[i][3]] = i;
            
            int tmp2 = game(rcd[i][2], rcd[i][3], rcd[i][0], rcd[i][1], x, y);
            
            solve(depth + 1, rcd[i][0], rcd[i][1], cnt + tmp2);

            map[rcd[i][0]][rcd[i][1]] = i;
            map[rcd[i][2]][rcd[i][3]] = i;
            
            check[i] = false;
        }
    }

    public static void init() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int num = map[i][j];

                if(num == 0) {
                    continue;
                }

                if(arr[num] == false) {
                    arr[num] = true;
                    rcd[num][0] = i;
                    rcd[num][1] = j;
                }
                else {
                    rcd[num][2] = i;
                    rcd[num][3] = j;
                }
            }
        }

        int cnt = 0;
        for(int i = 1; i <= 6; i++) {
            if(arr[i]) cnt++;
        }
        seq = new int [cnt];
    }
}