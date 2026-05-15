import java.util.Arrays;

public class Solution {
    public int solution(String s) {
        int l = s.length();
        int[][] arr = new int[l][l];
        
        for(int[] at : arr) {
            Arrays.fill(at, -1);
        }
        
        for(int i = 0; i < s.length(); i++) {
            arr[i][i] = 1;
        }
        
        int max = 1;
        
        for(int i = l - 1; i >= 0; i--) { 
            for (int j = i + 1; j < l; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                
                int res = -1;
                
                if(j - i == 1) {
                    res = 2;
                }
                else if(arr[i + 1][j - 1] != -1) {
                    res = arr[i + 1][j - 1] + 2;
                }
                
                max = Math.max(max, res);
                
                arr[i][j] = res;
            }
        }
        return max;
    }
}