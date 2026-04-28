import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        
        int n = sequence.length;
        
        List<long[]> list = List.of(new long[n], new long[n]);
        
        list.get(0)[0] = solve(0, 0, sequence);
        list.get(1)[0] = solve(1, 0, sequence);
        answer = Math.max(list.get(0)[0], list.get(1)[0]);
        
        for(int i = 1; i < n; i++) {
            for(int k = 0; k < 2; k++) {
                long tmp = solve(k, i, sequence);
                list.get(k)[i] = Math.max(tmp, list.get(k)[i - 1] + tmp);
            }
            answer = Math.max(answer, Math.max(list.get(0)[i], list.get(1)[i]));
        }
        
        return answer;
    }
    
    private long solve(int k, int i, int[] arr) {
        long ret;
        if(i % 2 == 0) {
            ret = (k == 0) ? (arr[i] * -1) : arr[i];
        }
        else {
            ret = (k == 0) ? arr[i] : (arr[i] * -1);
        }
        return ret;
    }
}