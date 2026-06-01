import java.util.*;

class Solution {

    public int solution(int[][] board, int[][] skill) {

        int answer = 0;
        
        int[][] arr = new int[board.length + 1][board[0].length + 1];
        
        for(int i = 0; i < board.length + 1; i++) {
            Arrays.fill(arr[i], 0);
        }
        
        for(int[] at : skill) {
            int r1 = at[1];
            int c1 = at[2];            
            int r2 = at[3];
            int c2 = at[4];

            int tmp = (at[0] == 1)? -at[5] : at[5];
            
            arr[r1][c1] += tmp;
            arr[r1][c2 + 1] += -tmp;
            arr[r2 + 1][c1] += -tmp;
            arr[r2 + 1][c2 + 1] += tmp;
        }
        
        for(int i = 0; i < board[0].length + 1; i++) {
            for(int j = 0; j < board.length; j++) {
                arr[j + 1][i] += arr[j][i];
            }
        }
        
        for(int j = 0; j < board.length + 1; j++) {
            for(int i = 0; i < board[0].length; i++) {
                arr[j][i + 1] += arr[j][i];
            }
        }
        
        for(int j = 0; j < board.length; j++) {
            for(int i = 0; i < board[0].length; i++) {
                board[j][i] += arr[j][i];
                if(board[j][i] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}