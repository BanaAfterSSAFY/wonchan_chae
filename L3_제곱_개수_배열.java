import java.util.*;

class Solution {

    static int N;
    static int[] arr;
    static long[] length;
    static long[] sum;

    public long[] solution(int[] arr, long l, long r) {

        this.arr = arr;

        N = arr.length;

        length = new long[N + 1];
        sum = new long[N + 1];

        for(int i = 0; i < N; i++) {

            length[i + 1] = length[i] + arr[i];

            sum[i + 1] = sum[i] + (long) arr[i] * arr[i];
        }

        long wl = r - l + 1;
        long K = solve(r) - solve(l - 1);
        long tl = length[N];
        long ls = tl - wl;

        long[] events = new long[2 * N + 2];

        int cnt = 0;

        events[cnt++] = 0;
        events[cnt++] = ls;

        for(int i = 1; i < N; i++) {
            long point = length[i];

            if(0 < point && point < ls) {
                events[cnt++] = point;
            }

            long shifted = point - wl;

            if(0 < shifted && shifted < ls) {
                events[cnt++] = shifted;
            }
        }

        Arrays.sort(events, 0, cnt);

        int size = 0;

        for(int i = 0; i < cnt; i++) {
            if(size == 0 || events[size - 1] != events[i]) {
                events[size++] = events[i];
            }
        }

        long cur = solve(wl);
        long C = 0;

        for(int i = 0; i < size - 1; i++) {
            long start = events[i];
            long end = events[i + 1];
            long count = end - start;

            long out = val(start);
            long in = val(start + wl);
            long diff = in - out;

            if(diff == 0) {
                if(cur == K) {
                    C += count;
                }
            }
            else {
                long target = K - cur;

                if(target % diff == 0) {
                    long move = target / diff;
                    if(0 <= move && move < count) {
                        C++;
                    }
                }
            }

            cur += count * diff;
        }

        if(cur == K) {
            C++;
        }

        return new long[]{K, C};
    }

    static long solve(long position) {
        if(position == 0) {
            return 0;
        }

        if(position == length[N]) {
            return sum[N];
        }

        int index = search(position) - 1;

        return sum[index] + (position - length[index]) * arr[index];
    }

    static long val(long position) {
        int index = search(position) - 1;

        return arr[index];
    }

    static int search(long target) {
        int left = 0;
        int right = N;

        while(left < right) {

            int mid = (left + right) / 2;

            if(length[mid] <= target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}