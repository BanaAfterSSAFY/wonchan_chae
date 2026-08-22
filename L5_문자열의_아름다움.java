import java.util.*;

class Solution {

    static int N;
    static char[] crr;
    static int[] arr;
    static int cnt;
    static long[] ct;
    static long[] at;
    static long[] rr;
    static long[] bt;

    public long solution(String s) {
        N = s.length();

        crr = new char[N];
        arr = new int[N];

        cnt = 0;

        int start = 0;

        while(start < N) {
            int end = start;

            while(end < N && s.charAt(start) == s.charAt(end)) {
                end++;
            }

            crr[cnt] = s.charAt(start);
            arr[cnt] = end - start;
            cnt++;
            start = end;
        }

        long answer = (long) N * (N - 1) * (N + 1) / 6;

        for(int i = 0; i < cnt; i++) {
            long length = arr[i];

            answer -= cube(length);
        }

        ct = new long[N + 2];
        at = new long[N + 2];
        rr = new long[N + 2];
        bt = new long[N + 2];

        for(char ch = 'a'; ch <= 'z'; ch++) {
            Arrays.fill(ct, 0);
            Arrays.fill(at, 0);
            Arrays.fill(rr, 0);
            Arrays.fill(bt, 0);

            long cntSum = 0;
            long lenSum = 0;

            for(int i = 0; i < cnt; i++) {
                if(crr[i] != ch) {
                    continue;
                }

                int length = arr[i];
                long t = triangle(length);
                long c = cube(length);

                long mt = solve(rr, length);
                long mc = solve(bt, length);
                long ml = (long) length * mt - mc;
                long mn = solve(ct, length);
                long mg = solve(at, length);

                long ln = cntSum - mn;
                long lg = lenSum - mg;
                long ll = t * lg - c * ln;

                answer -= ml + ll;

                update(ct, length, 1);
                update(at, length, length);
                update(rr, length, t);
                update(bt, length, c);

                cntSum++;
                lenSum += length;
            }
        }

        return answer;
    }

    static long triangle(long x) {
        return x * (x + 1) / 2;
    }

    static long cube(long x) {
        return x * (x - 1) * (x + 1) / 6;
    }

    static void update(long[] tree, int index, long value) {
        while(index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    static long solve(long[] tree, int index) {
        long result = 0;

        while(index > 0) {
            result += tree[index];
            index -= index & -index;
        }

        return result;
    }
}