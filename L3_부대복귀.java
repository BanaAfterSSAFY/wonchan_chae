import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};

        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] at : roads) {            
            int a = at[0];
            int b = at[1];
                 
            List<Integer> tmpA = map.getOrDefault(a, new ArrayList<>());
            tmpA.add(b);
            map.put(a, tmpA);
            
            List<Integer> tmpB = map.getOrDefault(b, new ArrayList<>());
            tmpB.add(a);
            map.put(b, tmpB);
        }
        
        boolean[] check = new boolean[n+1];
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        Queue<Integer> q = new LinkedList<>();
        check[destination] = true;
        q.add(destination);
        
        dp[destination]=0;
        
        while(q.isEmpty() == false) {
            int p = q.poll();
           
            List<Integer> cur = map.get(p);
            
            for(int temp : cur) {
                if(check[temp] == false) { 
                    check[temp] = true;
                    dp[temp] = dp[p] + 1;
                    q.add(temp);
                }
            }
        }
      
        List<Integer> result = new ArrayList<>();
        for(int s : sources) {
            if(dp[s] == Integer.MAX_VALUE) {
                dp[s] = -1;
            }
            result.add(dp[s]);
        }
        
        answer = result.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}