import java.util.*;

class Solution {

    static List<Integer>[] arr;
    static int ans = 0;

    public int solution(int[] info, int[][] edges) {
        arr = new ArrayList[info.length];
        
        for(int i = 0; i < info.length; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int[] at : edges) {
            arr[at[0]].add(at[1]);
        }
        
        List<Integer> next = new ArrayList<>();
        
        next.add(0);
        
        solve(0, 0, 0, next, info);
                
        return ans;
    }
    
    public void solve(int cur, int sheep, int wolf, List<Integer> next, int[] info) {
        if(info[cur] == 0) {
            sheep++;
        }
        else {
            wolf++;
        }
        
        if(wolf >= sheep) {
            return;
        }

        ans = Math.max(ans, sheep);
        
        List<Integer> list = new ArrayList<>(next);
        list.remove(Integer.valueOf(cur));
        list.addAll(arr[cur]);
        
        for(int n : list) {
            solve(n, sheep, wolf, list, info);
        }
    }
}