import java.util.*;

class Solution {

    static List<int[]> list = new ArrayList();
    static int[] arr = {0, 1, 2, 3};
    static boolean[] check;
    static int[][] dir = {{0, 1},{0, -1},{1, 0}, {-1, 0}};
    
    public int solution(int[][] clockHands) {
        int answer = Integer.MAX_VALUE;
        int[] res = new int[clockHands[0].length];
        check = new boolean[clockHands[0].length];
        
        solve(0, clockHands[0].length, res);
        
        for(int[] at: list) {
            int result = 0;
            int[][] copy = new int[clockHands.length][];
            
            for(int i = 0; i < clockHands.length; i++) {
                copy[i] = Arrays.copyOf(clockHands[i], clockHands[i].length);
            }

            for(int i = 0; i < at.length; i++) {
                result += at[i];
                turn(0, i, at[i], copy);
            }

            for(int i = 1; i < copy.length; i++) {
                for(int col = 0; col < copy[i].length; col++) {
                    if(copy[i - 1][col] != 0) {
                        int tmp = 4 - copy[i - 1][col];
                        result += tmp;
                        turn(i, col, tmp, copy);
                    }
                }
            }

            boolean flag = true;
            for(int aut : copy[copy.length - 1]) {
                if(aut != 0) {
                    flag = false;
                }
            }
            if(flag == true) {
                answer =Math.min(result, answer);
            }
        }
        return answer;
    }
    
    public void solve(int cur, int targetSize, int[] res) {
        if(cur == targetSize) {
            int[] tempResults = Arrays.copyOf(res, targetSize);
            list.add(tempResults);
            return;
        }

        for(int i = cur; i < targetSize; i++) {
            for(int at : arr) {
                if(check[i] == false) {
                    check[i] = true;
                    res[i] = at;
                    solve(cur + 1, targetSize, res);
                    check[i] = false;
                }
            }
        }
        return;
    }
    
    public void turn(int y, int x, int count, int[][] board) {
        board[y][x] = (board[y][x] + count) % 4;
        for(int[] at: dir) {
            int ny = at[0] + y;
            int nx = at[1] + x;
            if(ny < 0 || ny >= board.length || nx <0 || nx >= board[0].length) {
                continue;
            }
            board[ny][nx] = (board[ny][nx] + count) % 4;
        }
    }
}