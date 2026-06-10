import java.util.*;

class Solution {

    static int len, answer;
    static int arr[];
    static boolean visit[];

    public int solution(int n, int[] weak, int[] dist) {

        len = weak.length;
        arr = new int[len*2];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < len; j++) {
                arr[j + (i * len)] = weak[j] + (i * n);
            }
        }

        Arrays.sort(dist);
        answer = -1;
        visit = new boolean[dist.length];
        
        for(int i = 1; i <= dist.length; i++) {
            int tmp[] = new int[i];
            System.arraycopy(dist, dist.length - i, tmp, 0, i);
            dfs(0, new int[i], tmp);

            if(answer > 0) {
                break;
            }
        }
        
        return answer;
    }

    static boolean check(int dist[]) {

        for(int i = 0; i < len; i++) {
            int idx = i;
            for(int at : dist) {
                int position = arr[idx++] + at;
                while(idx < arr.length && arr[idx] <= position) {
                    idx++;
                }
            }

            if(idx - i >= len) {
                return true;
            }
        }
        return false;
    }
    
    static void dfs(int n, int dist[], int tmp[]) {
        if(n == tmp.length) {

            if(check(dist)) {
                answer = n;
            }
            return;
        }

        for(int i = 0; i < tmp.length; i++) {
            if(visit[i] == false) {
                visit[i] = true;
                dist[n] = tmp[i];
                dfs(n + 1, dist, tmp);
                visit[i] = false;
            }
        }
    }
}