import java.util.*;

class Solution {

    public int solution(int k, int n, int[][] reqs) {
        int[] people = new int[k];
        int answer = solve(0, n, k, people, reqs);
        return answer;
    }

    public int solve(int depth, int n, int k,int[] people, int[][] reqs) {
        if(depth == k && n == 0) {
            return time(people, k, reqs);
        }

        if(depth == k) {
            return 987654321;
        }

        int result = 987654321;
        
        for(int i = 1 ; i <= n ; i++) {
            people[depth] = i;
            result = Math.min(result, solve(depth + 1, n - i, k, people, reqs));
        }
        return result;
    }

    public int time(int[] people, int k, int[][] reqs) {
        int cnt = 0;
        PriorityQueue<Integer>[] pq = new PriorityQueue[k];

        for(int i = 0; i < k; i++) {
            pq[i] = new PriorityQueue<>();
        }

        for(int[] at : reqs) {
            int s = at[0];
            int d = at[1];
            int type = at[2] - 1;
            int t = s;

            if(pq[type].size() >= people[type]) {
                int e = pq[type].poll();
                if(e > s) {
                    cnt += e - s;
                    t = e;
                }
            }
            pq[type].add(t + d);
        }
        return cnt;
    }
}