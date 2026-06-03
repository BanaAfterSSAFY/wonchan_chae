import java.util.*;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {

    Stack<Integer> st = new Stack<>();
    int size = n;

        for(String at : cmd) {
            char c = at.charAt(0);
            
            switch(c) {
                case 'U' -> {
                    k -= Integer.parseInt(at.substring(2));
                }
                case 'D' -> {
                    k += Integer.parseInt(at.substring(2));
                }
                case 'C' -> {
                    st.push(k);
                    size--;
                    if(k == size) {
                        k--;
                    }
                }
                case 'Z' -> {
                    if(st.pop() <= k) {
                        k++;
                    }
                    size++;
                }
            }

            if(k < 0) {
                k = 0;
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append("O".repeat(Math.max(0, size)));
        
        while(st.isEmpty() == false) {
            ans.insert(st.pop(), "X");
        }
        
        return ans.toString();
    }
}