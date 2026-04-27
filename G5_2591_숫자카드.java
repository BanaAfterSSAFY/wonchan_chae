import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp = br.readLine();
        
        int N = inp.length();
        int[] dp = new int[N+1];

        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 1; i < N; i++) {
            dp[i + 1] += dp[i];
            if(solve(inp.charAt(i-1),inp.charAt(i))) {
                dp[i + 1] += dp[i - 1];
            }
            if(inp.charAt(i) == '0') {
                dp[i + 1] -= dp[i];
            }
        }
        System.out.println(dp[N]);
    }
    
    public static boolean solve(char a, char b) {
        if(a == '0') {
            return false;
        }
        int num = (a - '0') * 10 + (b - '0');
        return 1 <= num && num <= 34;
    }
}