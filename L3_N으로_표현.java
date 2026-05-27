import java.util.*;
 
class Solution {

    public int solution(int N, int number) {

        if (N == number) {
            return 1;
        }
 
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
 
        dp.get(1).add(N);
 
        for(int i = 2; i <= 8; i++) {

            StringBuilder sb = new StringBuilder().append(N);
            
            for(int j = 1; j < i; j++) {
                sb.append(N);
            }

            dp.get(i).add(Integer.parseInt(sb.toString()));

            for(int j = 1; j < i; j++) {
                int tmp = i - j;
                for(int at : dp.get(j)) {
                    for(int bt : dp.get(tmp)) {
                        dp.get(i).add(at + bt);
                        dp.get(i).add(at - bt);
                        dp.get(i).add(at * bt);
                        if(bt != 0) {
                            dp.get(i).add(at / bt);
                        }
                    }
                }
            }

            if (dp.get(i).contains(number)) {
                return i;
            }
        }
 
        return -1;
    }
}