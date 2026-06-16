import java.util.*;

class Solution {

    public int solution(int[] food_times, long k) {

        List<Integer> list = new ArrayList<>();

        list.add(0);

        for(int at : food_times) {
            list.add(at);
        }
        Collections.sort(list);
        
        int ret = food_times.length;
        int ans =-1;
        
        for(int i = 1; i < list.size(); i++) {
            long tmp = (long) (list.get(i) - list.get(i - 1)) * ret;
            if(tmp > k) {
                k %= ret;
                int cnt = 0;
                
                for(int j = 0; j < food_times.length; j++) {
                    if(food_times[j] < list.get(i)) {
                        continue;
                    }
                    if(cnt == k) {
                        ans = j + 1;
                        break;
                    }
                    cnt++;
                }
                break;
            }
            k -= tmp;
            ret--;
        }
        return ans;
    }
}