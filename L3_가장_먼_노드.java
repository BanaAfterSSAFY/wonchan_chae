import java.util.*;

class Solution {
    
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] check;

    public int solution(int n, int[][] edge) {
        for(int i = 0 ; i <= n ; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] at : edge) {
            int a = at[0];
            int b = at[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        check = new boolean[n + 1];
        return bfs(n);
    }
    
    public static int bfs(int n) {
        Queue<int[]> q = new LinkedList<>();
        int ans = 0;
        
        q.add(new int[] {1, 0});
        check[1] = true;
        int d = 0;
        
        while(q.isEmpty() == false) {
            int[] arr = q.poll();
            int value = arr[0];
            int depth = arr[1];
            
            
            if(d == depth) {
                ans++;
            }
            else if(d < depth) {
                d = depth;
                ans = 1;
            }

            
            for(int i = 0; i < list.get(value).size(); i++) {
                int tmp = list.get(value).get(i);
                if(check[tmp] == false) {
                    q.add(new int[] { tmp, depth + 1 });
                    check[tmp] = true;
                }
            }
        }

        return ans;
    }
}