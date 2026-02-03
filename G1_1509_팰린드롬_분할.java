import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[][] pal;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();
        
        pal = new boolean[S.length() + 1][S.length() + 1];
        arr = new int[S.length() + 1];
        solve(S);
        
    }

    public static void solve (String S) {
        
        for(int i=1; i<=S.length(); i++) {
            pal[i][i] = true;
        }
        
        arr[0] = 0;
        arr[1] = 1;
        
        for(int i=2; i<=S.length(); i++) {
            int tmp = arr[i-1] + 1;
            for(int j=1; j<i; j++) {
                if(S.charAt(i-1) != S.charAt(j-1)) {
                    continue;
                }
                if(i - j == 1 || pal[j+1][i-1] == true) {
                    pal[j][i] = true;
                    
                    tmp = Math.min(tmp, arr[j - 1] + 1);
                }
            }
            arr[i] = tmp;
        }
        
        System.out.println(arr[S.length()]);
        
        return;
    }

}