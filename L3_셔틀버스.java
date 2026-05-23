import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String at : timetable) {
            int tmp = Integer.parseInt(at.substring(0, 2)) * 60 + Integer.parseInt(at.substring(3));
            pq.add(tmp);
        }

        int s = 540, e = 0;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            sum = 0;

            while(pq.isEmpty() == false) {
                int cur = pq.peek();

                if(cur <= s && sum < m) {
                    pq.poll();
                    sum++;
                }
                else {
                    break;
                }
                e = cur - 1;
            }
            s += t;
        }

        if(sum < m) {
            e = s - t;
        }

        String a = String.format("%02d", e / 60);
        String b = String.format("%02d", e % 60);
        
        return a + ":" + b;
    }
}