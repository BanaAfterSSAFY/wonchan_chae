import java.io.*;
import java.util.*;

public class Main {

    static int M, N, S;
    static long MOD = 1000000007L;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        M = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            
            int tmp = gcd(Math.max(N, S), Math.min(N, S));
            N /= tmp;
            S /= tmp;
            ans += S * solve(N, MOD-2) % MOD;
            ans %= MOD;
        }
        System.out.println(ans);
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public static long solve(long b, long n) {
        if(n == 1) return b;
        
        long p = solve(b, n / 2);
        long ret = p * p % MOD;
        
        if(n % 2 == 1) {
            ret = ret * b % MOD;
        }
        return ret;
    }
}