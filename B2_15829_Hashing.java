import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M, idx;
    static Integer[] arr;
    static String S;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        S = br.readLine();
        
        long ans = 0;
        
        for(int i=0; i<N; i++) {
            
            long sum = 1;
            for(int j=0; j<i; j++) {
                sum = (sum * 31 ) % 1234567891;
            }
            
            ans += ((S.charAt(i) - 'a' + 1) * sum) % 1234567891;
            ans %= 1234567891;
        }
        
        System.out.println(ans);
        
        
    }
}