import java.util.*;

class Solution {

    public String[] solution(String[] expressions) {

        String[] answer;
        
        List<Integer> nums = new ArrayList<>(Arrays.asList(2,3,4,5,6,7,8,9));
        List<String> list = new ArrayList<>();
        String f, s, r;
        int cnt;
        
        for(String at : expressions) {
            f = at.split(" ")[0];
            s = at.split(" ")[2];
            r = at.split(" ")[4];
            cnt = "+".equals(at.split(" ")[1]) ? 1 : -1;
            
            if("X".equals(r)) {
                list.add(at);
            }

            for(int i = 2; i <= 9; i++) {
                if(nums.indexOf(i) == -1) {
                    continue;
                }
                
                try {
                    if("X".equals(r)) {
                        Integer.parseInt(f, i);
                        Integer.parseInt(s, i);
                    }
                    else if(Integer.parseInt(f, i) + Integer.parseInt(s, i) * cnt != Integer.parseInt(r, i)) {
                        throw new NumberFormatException();
                    }
                }
                catch(NumberFormatException e) {
                    nums.remove(nums.indexOf(i));
                }
            }
        }
        
        answer = new String[list.size()];
        String res, now;
        
        for(int i = 0; i < list.size(); i++) {
            res = "";
            f = list.get(i).split(" ")[0];
            cnt = "+".equals(list.get(i).split(" ")[1]) ? 1 : -1;
            s = list.get(i).split(" ")[2];
            
            for(int at : nums) {
                now = Integer.toString(Integer.parseInt(f, at) + Integer.parseInt(s, at) * cnt, at);
                
                if("".equals(res) == false && now.equals(res) == false) {
                    res = "?";
                    break;
                }
                
                res = now;
            }
            
            answer[i] = list.get(i).replace("X", res);
        }
        
        return answer;
    }
}