import java.util.*;

class Solution {

    public int[] solution(String[] operations) {
        Queue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Integer> minQ = new PriorityQueue<>();
        
        for(int i = 0; i < operations.length; i++) {
            String[] strs = operations[i].split(" ");
            if(strs[0].equals("I")) {
                minQ.offer(Integer.valueOf(strs[1]));
                maxQ.offer(Integer.valueOf(strs[1]));
            }
            else if(strs[0].equals("D") && strs[1].equals("1") && maxQ.isEmpty() == false) {
                minQ.remove(maxQ.poll());
            }
            else if(strs[0].equals("D") && strs[1].equals("-1") && minQ.isEmpty() == false) {
                maxQ.remove(minQ.poll());
            }
        }
        
        int min = minQ.isEmpty() ? 0 : minQ.poll();
        int max = maxQ.isEmpty() ? 0 : maxQ.poll();
        
        return new int[]{max, min} ;
    }
}