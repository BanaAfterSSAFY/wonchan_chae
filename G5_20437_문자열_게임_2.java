import java.io.*;
import java.util.*;

class Node {
    int a;
    int b;
    
    public Node() {}
    
    public Node(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class Main {
    
    static int[] alpha = new int[26];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int t=0; t<T; t++) {
            String S = br.readLine();
            int K = Integer.parseInt(br.readLine());
            
            Node res = solve(S, K);
            
            if(res.a == Integer.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(res.a + " " + res.b);
            }
            
        }
        
    }
    
    public static Node solve(String w, int k) {
        Node ret = new Node(Integer.MAX_VALUE, -1);
        
        if(k == 1) {
            ret.a = 1;
            ret.b = 1;
            return ret;
        }
        
        for(int i=0; i<w.length(); i++) {
            alpha[w.charAt(i) - 'a']++;
        }
        
        for(int i=0; i<w.length(); i++) {
            
            if(alpha[w.charAt(i) - 'a'] < k) {
                continue;
            }
            
            char start = w.charAt(i);
            int cnt = 1;
            for(int j=i+1; j<w.length(); j++) {
                if(w.charAt(j) == start) {
                    cnt++;
                }
                if(cnt == k) {
                    ret.a = Math.min(ret.a, j - i + 1);
                    ret.b = Math.max(ret.b, j - i + 1);
                    break;
                }
            }
        }
        return ret;
    }

}