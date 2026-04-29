import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] arr = scores[0];

        int answer = 1;
        int max = 0;
        int sum = arr[0] + arr[1];

        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        for(int[] at : scores) {
            if(max <= at[1]) {
                max = at[1];
                if(at[0] + at[1] > sum) {
                    answer++;
                }
                
            }
            else {
                if(at.equals(arr)) {
                    return -1;
                }
            }
        }

        return answer;
    }
}