import java.util.*;

class Solution {
    static boolean check[];
    static Queue<Integer> q = new LinkedList<>();

    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[n];

        for(int i = 0; i < n; i++) {
                if(check[i] == false) {
                    bfs(i, computers, n);
                    answer++;
                }
        }

        return answer;
    }

    static void bfs(int i, int computers[][], int n) {
        q.offer(i);
        check[i] = true;	

        while(q.isEmpty() == false) {
            int cur = q.poll();

            for(int j = 0; j < n; j++) {
                if(check[j] == false && computers[cur][j] == 1) {
                    check[j] = true;
                    q.offer(j);
                }
            }
        }
    }
}