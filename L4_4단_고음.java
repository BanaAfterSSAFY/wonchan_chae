import java.util.*;

class Solution {

    public int solution(int n) {
        int mul = multiple(n);
        int plus = mul * 2;
        
        int answer = solve(n - 2, mul, plus - 2);
        return answer;
    }

    public int multiple(int n) {
        return (int)(Math.log(n) / Math.log(3));
    }
    
    public int solve(int n , int mul, int plus) {
        if(mul * 2 < plus) {
            return 0;
        }

        if(n == 3 && mul == 1 && plus == 0) {
            return 1;
        }

        if(n == 4 && mul == 1 && plus == 1) {
            return 1;
        }

        if(n == 5 && mul == 1 && plus == 2) {
            return 1;
        }

        int count = 0;
        for(int i = 0; i <= plus; i++) {
            if((n - i > 0) && (n - i) % 3 == 0) {
                count += solve((n - i) /3, mul - 1, plus - i);
            }
        }
        
        return count;
    }
}