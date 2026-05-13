import java.util.*;

class Solution {

    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int tmp = routes[0][1];
        answer++;
    
        for(int i = 1; i < routes.length; i++) {
            if(tmp < routes[i][0]) {
                tmp = routes[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}