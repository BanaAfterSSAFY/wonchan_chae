import java.util.*;

class Solution {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 0;
        long end = 400000000000000L;

        while(start + 1 < end) {
            long mid = (start + end) / (long)2;

            if(solve(mid, a, b, g, s, w, t)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        return end;
    }
    
    public boolean solve(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        int N = g.length;
        long T = 0L;
        long G = 0L;
        long S = 0L;

        for(int i = 0; i < N; i++) {

            long cnt = time / (2L * t[i]);
            
            if(time % (2L * t[i]) >= t[i]) {
                cnt++;
            }

            long tmp = Math.min(cnt * w[i], g[i] + s[i]);

            T += tmp;
            G += Math.min(tmp, g[i]);
            S += Math.min(tmp, s[i]);
        }

        if(T >= a + b && G >= a && S >= b) {
            return true;
        }

        return false;
    }
}