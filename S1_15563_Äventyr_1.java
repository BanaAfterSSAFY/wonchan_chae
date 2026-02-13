import java.io.*;
import java.util.*;

public class Main {
    
    static int N, Q;
    static int[][] arr;
    static int Ans = -1;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
                
        st = new StringTokenizer(br.readLine());
        
        int cmd, inp;
        TreeSet goal = new TreeSet<>();
        
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            inp = Integer.parseInt(st.nextToken());
        
            if(cmd == 1) {
                goal.add(inp);
            }
            else {
                if(goal.size() == 0) {
                    sb.append("-1\n");
                }
                else {
                    
                    int min = Integer.MAX_VALUE;
                    Integer s = (Integer) goal.floor(inp); 
                    Integer e = (Integer) goal.ceiling(inp);
                    
                    if(e != null)
                        min = Math.min(min, Math.abs(e - inp));
                    if(s != null)
                        min = Math.min(min, Math.abs(s - inp));
                    
                    sb.append(min + "\n");
                }
            }
        }
        System.out.println(sb);
    }
}