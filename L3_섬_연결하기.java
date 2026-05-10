import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (o1, o2) -> {
            return o1[2] - o2[2];
        });
        
        int prr[] = new int[101];
        for(int i = 1; i <= n; i++) {
            prr[i] = i;
        }
        
        for(int i = 0; i < costs.length; i++) {
            if(find(costs[i][0], prr) != find(costs[i][1], prr)) {
                union(costs[i][0], costs[i][1], prr);
                answer += costs[i][2];
            }
        }
        
        return answer;
    }
    
    public void union(int a, int b, int prr[]) {
        int A = find(a, prr);
        int B = find(b, prr);
        if(A != B) {
            prr[A] = B;
        }
    }
    
    public int find(int i, int prr[]) {
        if(prr[i] == i) {
            return i;
        }
        return find(prr[i], prr);
    }
}
