import java.util.*;

class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        for(int i = 0; i <= stations.length; i++) {
            int sz = 0;
            
            if(i == 0) {
                sz = stations[i] - 1 - w;
            }
            else if(i == stations.length) {
                sz = n - stations[i-1] - w;
            }
            else {
                sz = stations[i] - stations[i-1] - (w * 2) -1;
            }
            
            if(sz >= 0) {
                int cnt = sz / (w * 2 + 1);
                int rt = sz % (w * 2 + 1);
                answer += cnt;
                
                if(rt != 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}