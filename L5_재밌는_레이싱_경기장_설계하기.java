import java.util.*;

class Solution {

    public int solution(int[] heights) {
        Arrays.sort(heights);

        int n = heights.length;
        int mid = n / 2;

        if(n % 2 == 0) {
            int answer = Integer.MAX_VALUE;

            for(int i = 0; i < mid; i++) {
                answer = Math.min(answer, heights[i + mid] - heights[i]);
            }

            return answer;
        }

        int[] diff = new int[mid + 1];

        for(int i = 0; i < mid; i++) {
            diff[i] = heights[i + mid] - heights[i];
        }

        diff[mid] = heights[n - 1] - heights[mid];
        Arrays.sort(diff);

        return diff[1];
    }
}