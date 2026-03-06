import java.io.*;
import java.util.*;

public class Main {
    
    static long N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        
        long A = 0, B = 0;
        
        for(long i=1; i*i<=M/N; i++) {
            if((M / N) % i == 0) {
                long a = i;
                long b = (M / N) / i;
                
                if(gcd(a, b) == 1) {
                    A = a * N;
                    B = b * N;
                }
            }
        }
        
        if(A > B) {
            long tmp = A;
            A = B;
            B = tmp;
        }
        
        System.out.println(A + " " + B);
    }
    
    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}