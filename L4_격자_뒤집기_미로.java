import java.util.*;

class Solution {

    public int solution(int[][] visible, int[][] hidden, int k) {
        int answer = 0;        
        double max = Math.pow(2, visible.length);
        boolean[] check = new boolean[visible.length];

        for(int i = 0; i < max; i++) {
            int idx = 0;
            while(idx < check.length) {
                if(check[idx]) {
                    check[idx] = false;
                }
                else {
                    check[idx] = true;
                    break;
                }
                idx++;
            }
            answer = Math.max(answer, solve(check, visible, hidden, k));
        }
        return answer;
    }
    
    static int solve(boolean[] check, int[][] visible, int[][] hidden, int k) {
        int ret = 0;
        int n = visible.length;
        int m = visible[0].length;
        boolean flag = n % 2 == 0 && m % 2 == 0;
        int mini = 101;
        
        for(int i = 0; i < n; i++) {
            if(check[i]) {
                ret -= k;
            }         
        }
 
        for(int x = 0; x < m; x++) {
            int[] sum = new int[]{0, -k};            
            int[] minNum = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
           
            for(int y = 0; y < n; y++) {
                for(int l = 0; l < 2; l++) {
                    int num = ((l==1) ^ check[y]) ? hidden[y][x] : visible[y][x];
                    
                    sum[l] += num;
                    
                    if(flag && (x + y) % 2 == 1) {
                        minNum[l] = Math.min(minNum[l], num);    
                    }
                }
            }
            ret += Math.max(sum[0], sum[1]);
            if(flag) {
                int loss = Math.max(sum[0], sum[1]) - Math.max(sum[0] - minNum[0], sum[1] - minNum[1]);
                mini = Math.min(mini, loss);
            }
        }
        return flag ? ret - mini : ret;
    }
}