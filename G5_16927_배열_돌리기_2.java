import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, R;
    static int[][] arr;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] inp;
        inp = br.readLine().split(" ");
        
        N = Integer.parseInt(inp[0]);
        M = Integer.parseInt(inp[1]);
        R = Integer.parseInt(inp[2]);
        
        arr = new int[N][M];
        
        int K = Math.min(N, M) / 2;

        for(int i=0; i<N; i++) {
            inp = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(inp[j]);
            }
        }
            
        for(int k=0; k<K; k++) {
            int move = R % ((N - 2 * k) * 2 + (M - 2 * k) * 2 - 4);

            for(int i=0; i<move; i++) {
                int r = k, c = k, d = 0;
                int temp = arr[r][c], cur;
                
                while(true) {
                    if(r + dir[d][0] < k || r + dir[d][0] >= N - k || c + dir[d][1] < k || c + dir[d][1] >= M - k) {
                        d++;
                        if(d == 4) {
                            break;
                        }
                    }
                    
                    r += dir[d][0];
                    c += dir[d][1];
                    
                    cur = arr[r][c];
                    arr[r][c] = temp;
                    temp = cur;
                }
                
                
            }
        }
                        
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}