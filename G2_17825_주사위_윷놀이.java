import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, L;
    static int[][] map;
    static int[][] pos;
    static int[] arr;
    static int[] per;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        map = new int[5][22];
        arr = new int[10];
        per = new int[10];
    
        for(int i=0; i<21; i++) {
            map[0][i] = i * 2;
        }
        
        for(int i=0; i<4; i++) {
            map[1][i] = 10 + 3 * i;
        }
        for(int i=4; i<8; i++) {
            map[1][i] = 25 + 5 * (i-4);
        }
        
        for(int i=0; i<3; i++) {
            map[2][i] = 20 + 2 * i;
        }
        for(int i=3; i<7; i++) {
            map[2][i] = 25 + 5 * (i-3);
        }
        
        map[3][0] = 30;
        for(int i=1; i<4; i++) {
            map[3][i] = 29 - i;
        }
        for(int i=4; i<8; i++) {
            map[3][i] = 25 + 5 * (i-4);
        }
        
        for(int i=0; i<10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        solve(0);
        
        System.out.println(ans);
    }
    
    static void solve(int cnt) {
        if(cnt == 10) {
            pos = new int[4][2];
            move();
            return;
        }
        
        for(int i=0; i<4; i++) {
            per[cnt] = i;
            solve(cnt + 1);
        }
    }
    
    static void move() {
        int ret = 0;
        for(int i=0; i<10; i++) {
            if(end(pos[per[i]][0], pos[per[i]][1]) == false) {
                return;
            }
            
            if(check(pos[per[i]][0], pos[per[i]][1] + arr[i]) == false) {
                return;
            }
            
            pos[per[i]][1] += arr[i];
            if(pos[per[i]][0] == 0 && (pos[per[i]][1] == 5 || pos[per[i]][1] == 10 || pos[per[i]][1] == 15)) {
                pos[per[i]][0] = pos[per[i]][1] / 5;
                pos[per[i]][1] = 0;
            }
            
            if(end(pos[per[i]][0], pos[per[i]][1]) == true) {
                ret += map[pos[per[i]][0]][pos[per[i]][1]];
            }
        }

        ans = Math.max(ans, ret);
    }
    
    static boolean end(int r, int c) {

        if(r == 0 && c > 20) {
            return false;
        }
        if(r == 1 && c > 7) {
            return false;
        }
        if(r == 2 && c > 6) {
            return false;
        }
        if(r == 3 && c > 7) {
            return false;
        }
        return true;
    }
    
    static boolean check(int r, int c) {
        if(r == 0 && (c == 5 || c == 10 || c == 15)) {
            r = c / 5;
            c = 0;
        }
        
        if(r == 0 && c > 20) {
            return true;
        }
        if(r == 1 && c > 7) {
            return true;
        }
        if(r == 2 && c > 6) {
            return true;
        }
        if(r == 3 && c > 7) {
            return true;
        }
        
        for(int i=0; i<4; i++) {
            if(end(pos[i][0], pos[i][1]) == false) {
                continue;
            }
            
            if(pos[i][0] == r && pos[i][1] == c) {
                return false;
            }
            if(map[r][c] == 40 && map[pos[i][0]][pos[i][1]] == 40) {
                return false;
            }

            if(map[r][c] == 35 && map[pos[i][0]][pos[i][1]] == 35) {
                return false;
            }

            if(map[r][c] == 25 && map[pos[i][0]][pos[i][1]] == 25) {
                return false;
            }

            if(map[r][c] == 30 && map[pos[i][0]][pos[i][1]] == 30) {
                if((r == 3 && c == 0) || (pos[i][0] == 3 && pos[i][1] == 0)) {
                    continue;
                }
                return false;
            }
        }
        
        return true;
    }
}