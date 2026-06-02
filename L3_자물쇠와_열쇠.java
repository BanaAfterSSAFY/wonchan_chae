import java.util.*;

class Solution {

    static int N, M;
    
    public boolean solution(int[][] key, int[][] lock) {

        M = key.length;
        N = lock.length;
    
        for(int i = 0; i < 4; i++) {
            if(check(key, lock)) {
                return true;
            }
            key = rotate(key);
        }
        return false;
    }
    
    public int[][] rotate(int[][] key) {
        
        int[][] tmp = new int[M][M]; 
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                tmp[j][i] = key[M - 1 - i][j];
            }
        }
        return tmp;
    }
    
    public boolean check(int[][] tmp, int[][] lock) {
        int cnt = N + 2 * (M - 1);
        
        for(int i = 0; i <= cnt - M; i++) {
            for(int j = 0; j <= cnt - M; j++) {
                if(solve(copy(lock, cnt), tmp, i, j)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public int[][] copy(int[][] lock, int cnt) {
        
        int[][] res = new int[cnt][cnt];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                res[M - 1 + i][M - 1 + j] = lock[i][j];
            }
        }
        
        return res;
    }

    public boolean solve(int[][] res, int[][] key, int x, int y) {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                res[x + i][y + j] += key[i][j];
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(res[M - 1 + i][M - 1 + j] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}