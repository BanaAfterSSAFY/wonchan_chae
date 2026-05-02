class Solution {

    public int[] solution(int n, int s) {
        int[] answer;
        
        if(n > s) {
            answer = new int[]{-1};
            return answer;
        }
        
        int start = s / n;
        int cnt = s % n;
        
        answer = new int[n];

        for(int i = 0; i < n; i++) {
            answer[i] = start;
        }
        
        int idx = n - 1;
        for(int i = 0; i < cnt; i++) {
            answer[idx]++;
            idx--;
        }
        
        return answer;
    }
}