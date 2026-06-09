import java.util.*;

class Solution {

    public int[] solution(long[] numbers) {
        
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            StringBuilder sb = new StringBuilder();
            long num = numbers[i];
            while(num > 0) {
                if(num % 2 == 1) {
                    sb.insert(0, "1");
                }
                else {
                    sb.insert(0, "0");
                }
                num /= 2;
            }

            int cnt = 1;
            int len = sb.length();
            int sed;
            
            while(true) {
                cnt *= 2;
                if(cnt - 1 >= len) {
                    sed = cnt - 1 - len;
                    break;
                }
            }
            for(int j = 0; j < sed; j++) {
                sb.insert(0, "0");
            }

            int res = solve(sb);

            if(res == -1) {
                answer[i] = 0;    
            }
            else {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    public int solve(StringBuilder sb) {
        int len = sb.length();
        if(len == 1) {
            return sb.charAt(0) - '0';
        }
        StringBuilder leftS = new StringBuilder(sb.substring(0, len / 2));
        StringBuilder rightS = new StringBuilder(sb.substring(len / 2 + 1));
        
        int left = solve(leftS);
        if(left == -1) {
            return -1;
        }

        int right = solve(rightS);
        if(right == -1) {
            return -1;
        }
        int mid = sb.charAt(len / 2) - '0';
        if(left + right > 0 && mid == 0) {
            return -1;
        }
        else if(left + right + mid == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }
}