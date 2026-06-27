import java.util.*;

class Solution {

    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    static int ans = 0;
    
    public int solution(int n, int[][] lighthouse) {
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] at : lighthouse) {
            int a = at[0];
            int b = at[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        solve(1, 0);
        
        return ans;
    }
    
    public int solve(int cur, int pre) {
        if(list.get(cur).size() == 1 && list.get(cur).get(0) == pre) {
            return 1;
        }
        
        int tmp = 0;
        
        for(int at : list.get(cur)) {
            if (at == pre) {
                continue;
            }
            tmp += solve(at, cur);
        }
        
        if(tmp == 0) {
            return 1;
        }
        else {
            ++ans;
        }
        
        return 0;
    }
}