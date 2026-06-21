import java.util.*;

class Solution {

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int answer = 0;
    
        int[] arr = new int[rocks.length + 1];
        arr[0] = rocks[0];
        arr[rocks.length] = distance - rocks[rocks.length - 1];
    
        for(int i = 1; i < rocks.length; i++) {
            arr[i] = rocks[i] - rocks[i - 1];
        }

        int max = distance;
        int min = 1;
        
        while(min <= max) {
            int mid = (min + max) / 2;
            int sum = 0;
            int cnt = 0;

            for(int i = 0; i < arr.length; i++) {
                sum += arr[i];
                if(sum < mid) {
                    cnt++;
                    continue;
                }
                sum = 0;
            }

            if(cnt > n) {
                max = mid - 1;
                continue;
            }
             
            min = mid + 1;
            answer = Math.max(answer, mid);
        }
        return answer;
    }
}