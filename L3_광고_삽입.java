import java.util.*;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int[] arr = new int[360001];
        
        for(String at : logs) {
            String[] l = at.split("-");
            int s= solve(l[0]);
            int e = solve(l[1]);
            
            arr[s]++;
            arr[e]--;
        }
        
        int e = solve(play_time);
        int adv = solve(adv_time);
        int ms= e - adv;
        
        for(int i = 1;i < arr.length; i++) {
            arr[i] += arr[i-1];
        }
        
        
        long max = 0;  
        long current=max;
        int sp = 0;
        
        for(int i = adv; i <= e; i++) {
           
            current += arr[i] - arr[i-adv];
            
            if(current > max) {
                sp = i - adv + 1;
                max = current;       
            }
        }   
        answer = tsolve(sp);
      
        return answer;
    }
    
    public int solve(String time) {
        
        String[] t = time.split(":");
        
        return Integer.parseInt(t[2])+
            Integer.parseInt(t[1]) * 60+
            Integer.parseInt(t[0]) * 60 * 60;
    }
    
    public String tsolve(int time) {
        
        int hour= time / 3600;
        String sh = String.valueOf(hour);
        if(hour<10) sh="0"+sh;
        
        
        time -= hour*3600;
        
        int min = time/60;
        time -= min*60;
        String mh = String.valueOf(min);
        
        if(min < 10) mh = "0"+mh;
        
        String ch = String.valueOf(time);
        if(time < 10) ch = "0"+ch;
        
        return sh+":"+mh+":"+ch;
    }
  
}