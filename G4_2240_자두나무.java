import java.io.*;
import java.util.*;

public class Main {
    
    static int [][] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        arr = new int[T+1][W+1];
        
        for(int i=1; i<=T; i++) {
            int inp = Integer.parseInt(br.readLine());
            
            for(int j=0; j<=W; j++) {
                if(j == 0) {
                    if(inp == 1) {
                        arr[i][j] = arr[i-1][j] + 1;
                    }
                    else {
                        arr[i][j] = arr[i-1][j];
                    }
                    continue;
                }
                
                
                if(j % 2 == 0) {
                    if(inp == 1) {
                        arr[i][j] = Math.max(arr[i-1][j-1], arr[i-1][j] + 1);
                    }
                    else {
                        arr[i][j] = Math.max(arr[i-1][j-1] + 1, arr[i-1][j]);
                    }
                }
                else {
                    if(inp == 1) {
                        arr[i][j] = Math.max(arr[i-1][j-1] + 1, arr[i-1][j]);
                    }
                    else {
                        arr[i][j] = Math.max(arr[i-1][j-1], arr[i-1][j] + 1);
                    }
                }
                
                
            }
        }
        
        int Ans = 0;
        
        for(int i=0; i<=W; i++) {
            Ans = Math.max(Ans, arr[T][i]);
        }
        
        System.out.println(Ans);
    }
}