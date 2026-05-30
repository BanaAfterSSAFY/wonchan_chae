import java.util.*;

class Solution {

    public Map<String,Integer> map = new HashMap<>();
    public int[] ans;
    public String[] rep;
    
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        rep = new String[enroll.length];
        ans = new int[enroll.length];
        
        for(int i = 0; i < enroll.length; i++) {
            map.put(enroll[i],i);
            if(referral[i].equals("-")) {
                continue;
            }
            rep[i] = referral[i];
        }
        
        for(int i = 0; i < seller.length; i++) {
            solve(seller[i], amount[i] * 100);
        }
        return ans;
    }

    public void solve(String seller, int price){

        if(price <= 0 || seller == null) {
            return;
        }
        
        int tmp = price / 10;
        price -= tmp;
        ans[map.get(seller)] += price;
        
        solve(rep[map.get(seller)], tmp);
    }
}