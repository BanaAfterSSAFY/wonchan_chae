class Solution {
    boolean[] check;
    int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        check = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        return answer;
    }
    
    public void dfs(String begin, String target, String[] words, int cnt) {
        if(begin.equals(target)) {
            answer = cnt;
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(check[i]) {
                continue;
            }
             
            int tmp = 0;
            for(int j = 0; j < begin.length(); j++) {
                if(begin.charAt(j) == words[i].charAt(j)) {
                    tmp++;
                }
            }
             
            if(tmp == begin.length() - 1) {
                check[i] = true;
                dfs(words[i], target, words, cnt+1);
                check[i] = false;
            }
        }
    }
}