import java.util.*;

class Solution {
    int L = 0;
    boolean[] check;
    List<String> list;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        L = tickets.length;
        
        check = new boolean[L];
        list = new ArrayList<>();
        
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(list);
        answer = list.get(0).split(" ");
        
        return answer;
    }

    public void dfs(String start, String route, String[][] tickets, int count) {
        if(count == L) {
            list.add(route);
            return;
        }

        for(int i = 0; i < L; i++) {
            if(check[i] == false && start.equals(tickets[i][0])) {
                check[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, count+1);
                check[i] = false;
            }
        }
    }
}