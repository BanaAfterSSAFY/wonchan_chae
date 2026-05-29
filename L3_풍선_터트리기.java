import java.util.*;

class Solution {

    public int solution(int[] a) {
        int answer = 2;

        int[] left = new int[a.length];
        int[] right = new int[a.length];
        
        int min = a[0];

        for(int i = 1; i < a.length - 1; i++) {
            left[i] = min;
            if(a[i] < min) {
                min = a[i];
            }
        }
        
        min = a[a.length - 1]; 
        for(int i = a.length - 2; i > 0; i--) {
            right[i] = min;
            if(a[i] < min) {
                min = a[i];
            }
        }
        
        for(int i = 1; i < a.length - 1; i++) {
            if(left[i] < a[i] && right[i] < a[i]) {
                continue;
            }
            answer++;
        }
        
        return answer;
    }
}