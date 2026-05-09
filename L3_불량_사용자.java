import java.util.*;

class Solution {
    
    Stack<Integer> st = new Stack<>();
    List<Integer> list = new ArrayList<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        boolean[][] check = new boolean[banned_id.length][user_id.length];
        
        for(int i = 0; i < check.length; i++) {
            for(int j = 0; j < check[0].length; j++) {
                boolean flag = true;
                
                if(banned_id[i].length() != user_id[j].length()) {
                    continue;
                }
                
                for(int k = 0; k < banned_id[i].length(); k++) {
                    if(banned_id[i].charAt(k) == '*') {
                        continue;
                    }
                    if(banned_id[i].charAt(k) != user_id[j].charAt(k)) {
                        flag = false;
                        break;
                    }
                }

                if(flag == true) {
                    check[i][j] = true;
                }
            }
        }

        dfs(check, 0);
        
        return list.size();
    }
    
    public void dfs(boolean[][] check, int cnt) {
        if(cnt >= check.length) {
            int sum = 0;
            for(int i = 0; i < st.size(); i++) {
                sum += Math.pow(2, st.get(i));
            }

            if(list.indexOf(sum) != -1) {
                return;
            }
            
            list.add(sum);
        }
        else {
            for(int i = 0; i < check[cnt].length; i++) {
                if(check[cnt][i] == false) {
                    continue;
                }

                if(st.indexOf(i) != -1) {
                    continue;
                }

                st.add(i);
                dfs(check, cnt+1);
                st.pop();
            }
        }   
    }
}