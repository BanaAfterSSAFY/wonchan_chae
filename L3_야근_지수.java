import java.util.*;

class Solution {
    public long solution(int n, int[] works) {

        long answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i = 0; i < works.length; i++) {
            q.offer(works[i]);
        }
        
        while(n > 0) {
            int tmp = q.poll();
            if(tmp == 0) {
                break;
            }

            tmp--;
            n--;
            q.offer(tmp);
        }
        
        while(q.isEmpty() == false) {
            int cur = q.poll();
            answer += cur * cur;
        }
        return answer;
    }
}