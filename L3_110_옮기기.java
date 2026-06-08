import java.util.*;

class Solution {
    
    static Stack<Character> st = new Stack<>();
    static int cnt;
    static String[] answer;
    
    public String[] solution(String[] s) {
        answer = new String[s.length];
        
        for(int i = 0; i < s.length; i++) {
            st.clear();
            cnt = 0;
            String tmp = s[i];
            
            for(int j = 0; j < tmp.length(); j++) {
                if(tmp.charAt(j) == '1') {
                    st.push('1');
                }
                else {
                    if(st.size() >= 2) {
                        char a = st.pop();
                        char b = st.pop();
                        if((a == '1') && (b == '1')) {
                            cnt++;
                        }
                        else {
                            st.push(b);
                            st.push(a);
                            st.push('0');
                        }
                    }
                    else {
                        st.push('0');
                    }
                }
            }
            
            for(int j = 0; j < cnt; j++) {
                Stack<Character> tmps = new Stack<>();
                while(true) {
                    if(st.isEmpty() == true) {
                        st.push('1');
                        st.push('1');
                        st.push('0');
                        while(tmps.isEmpty() == false) {
                            st.push(tmps.pop());
                        }
                        break;
                    }
                    else {
                        char now = st.pop();
                        if(now == '0') {
                            st.push(now);
                            st.push('1');
                            st.push('1');
                            st.push('0');
                            while(tmps.isEmpty() == false) {
                                st.push(tmps.pop());
                            }
                            break;
                        }
                        else {
                            tmps.push(now);
                        }
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while(st.isEmpty() == false) {
                sb.append(st.pop());
            }
            String Result = sb.reverse().toString();

            answer[i] = Result;
            
            System.out.println();
        }       
        return answer;
    }
}