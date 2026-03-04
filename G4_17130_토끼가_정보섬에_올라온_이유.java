import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, R, C;
    static char[][] arr;
    static int[][] check;
    static int ans = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new char[N][M];
        check = new int[N][M];
        
        for(int i=0; i<N; i++) {
            String inp = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = inp.charAt(j);
                if(arr[i][j] == 'R') {
                    R = i;
                    C = j;
                }
            }
        }
        
        for(int i=0; i<=C; i++) {
            for(int j=0; j<N; j++) {
                check[j][i] = -1;
            }
        }
        
        check[R][C] = 0;
        
        for(int i=C+1; i<M; i++) {
            for(int j=0; j<N; j++) {
                
                if(N != 1) {
                    if(j == 0) {
                        check[j][i] = Math.max(check[j][i-1], check[j+1][i-1]);
                    }
                    else if(j == N-1) {
                        check[j][i] = Math.max(check[j][i-1], check[j-1][i-1]);
                    }
                    else {
                        check[j][i] = Math.max(check[j][i-1], Math.max(check[j-1][i-1], check[j+1][i-1]));
                    }
                }
                else {
                    check[j][i] = check[j][i-1];
                }
                
                if(check[j][i] > -1 && arr[j][i] == 'C') {
                    check[j][i]++;
                }
                else if(arr[j][i] == '#') {
                    check[j][i] = -1;
                }
                else if(arr[j][i] == 'O') {
                    ans = Math.max(ans, check[j][i]);
                }
            }
        }
        
        System.out.println(ans);
    }
}