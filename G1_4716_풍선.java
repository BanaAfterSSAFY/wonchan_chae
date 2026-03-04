import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node>{
        int t;
        int a;
        int b;
        
        Node(int t, int a, int b){
            this.t = t;
            this.a = a;
            this.b = b;
        }
        
        @Override
        public int compareTo(Node o) {
            return Math.abs(o.a - o.b) - Math.abs(this.a - this.b);
        }
    }
    
    static int N, A, B;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            
            List<Node> list = new ArrayList<>();
            
            if(N == 0 && A == 0 && B == 0) {
                break;
            }
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                
                list.add(new Node(K, p, q));
            }
            
            Collections.sort(list);
            
            
            int ans = 0;
            
            for(Node at : list) {				
                
                if(at.a < at.b) {
                    if(A >= at.t) {
                        ans += at.a * at.t;
                        A -= at.t;
                    }
                    else {
                        ans += at.a * A + at.b * (at.t - A);
                        B -= at.t - A;
                        A = 0;
                    }
                }
                else {
                    if(B >= at.t) {
                        ans += at.b * at.t;
                        B -= at.t;
                    }
                    else {
                        ans += at.b * B + at.a * (at.t - B);
                        A -= at.t - B;
                        B = 0;
                    }
                }
            }
            
            System.out.println(ans);
        }
    }
}