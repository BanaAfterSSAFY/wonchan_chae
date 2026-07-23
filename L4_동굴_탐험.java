import java.util.*;

class Solution {
    List<Integer>[] list;
    boolean[] check;
    Map<Integer, Integer> map = new HashMap<>();

    public boolean solution(int n, int[][] path, int[][] order) {
        list = new List[n];
        check = new boolean[n];
        Arrays.fill(check, true);

        for(int[] at : order) {
            map.put(at[0], at[1]);
            check[at[1]] = false;
        }

        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int[] at : path) {
            list[at[0]].add(at[1]);
            list[at[1]].add(at[0]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n];
        visit[0] = true;
        q.add(0);
        int cnt = 1;

        while(q.isEmpty() == false) {
            int cur = q.poll();

            for(int next : list[cur]) {
                if(check[next] && visit[next] == false) {
                    visit[next] = true;
                    q.add(next);
                    cnt++;
                }
            }

            if(map.containsKey(cur)) {
                int next = map.get(cur);
                check[next] = true;
                for(int check : list[next]) {
                    if(visit[check]) {
                        visit[next] = true;
                        q.add(next);
                        cnt++;
                        break;
                    }
                }
            }
        }
        return cnt == n ? true : false;
    }
}