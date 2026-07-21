import java.util.*;

public class Solution {
    
    public long solution(int[][] land, int P, int Q) {
        
        int N = land.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                min = Math.min(min, land[i][j]);
                max = Math.max(max, land[i][j]);
            }
        }

        int s = min;
        int e = max + 1;

        while(s + 1 < e) {
            int mid = (s + e) / 2;

            long left = 0;
            long cur = 0;
            long right = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(mid - 1 >= 0) {
                        left += (land[i][j] > mid - 1) ? (long)(land[i][j] - mid + 1) * Q : (long)(mid - 1 - land[i][j]) * P;
                    }

                    if(mid + 1 <= max) {
                        right += (land[i][j] > mid + 1) ? (long)(land[i][j] - mid - 1) * Q : (long)(mid + 1 - land[i][j]) * P;
                    }

                    cur += (land[i][j] > mid) ? (long)(land[i][j] - mid) * Q : (long)(mid - land[i][j]) * P;
                }
            }

            if(mid - 1 < min) {
                if(cur < right) {
                    e = mid;
                }
                else {
                    s = mid;
                }
            }
            else if(mid + 1 > max) {
                if(cur > left) {
                    e = mid;
                }
                else {
                    s = mid;
                }
            }
            else {
                if(cur <= left && cur <= right) {
                    s = mid;
                    break;
                }
                else if(cur > left) {
                    e = mid;
                }
                else {
                    s = mid;
                }
            }
        }

        long result = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result += (land[i][j] > s) ? (long)(land[i][j] - s) * Q : (long)(s - land[i][j]) * P;
            }
        }
        return result;
    }
}