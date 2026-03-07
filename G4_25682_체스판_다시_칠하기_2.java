import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static char[][] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];

        char[] tmp;
        for(int i = 0; i < N; i++) {
            tmp = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                arr[i][j] = tmp[j];
            }
        }

        int[][] black = prefix('B');
        int[][] white = prefix('W');

        System.out.println(Math.min(solve(black), solve(white)));
    }

    private static int solve(int[][] board) {
        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= N-K+1; i++) {
            for(int j = 1; j <= M-K+1; j++) {
                int num = board[i+K-1][j+K-1] - board[i-1][j+K-1] - board[i+K-1][j-1] + board[i-1][j-1];

                result = Math.min(num, result);
            }
        }
        return result;
    }

    private static int[][] prefix(char color) {
        int[][] tmp = new int[N+1][M+1];

        for(int i = 0; i < N; i++) {
            for(int j = 0;  j < M; j++) {
                int cnt = 0;

                if((i+j) % 2 == 0) {
                    cnt = arr[i][j] == color ? 0 : 1;
                }else{
                    cnt =  arr[i][j] != color ? 0 : 1;
                }

                tmp[i+1][j+1] = tmp[i+1][j] + tmp[i][j+1] - tmp[i][j] + cnt;
            }
        }
        return tmp;
    }
}