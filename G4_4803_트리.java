import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static List<Integer>[] list;
    static boolean[] check;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = 0;
        while(true) {
            t++;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            if(N == 0 && M == 0) {
                break;
            }
            
            init();
            
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                list[a].add(b);
                list[b].add(a);
            }
            
            int Ans = 0;
            for(int i=1; i<=N; i++) {
                if(tree(i, -1) == true) {
                    Ans++;
                }
            }
            
            System.out.print("Case " + t + ": ");
            
            if(Ans == 0) {
                System.out.print("No trees.\n");
            }
            else if(Ans == 1) {
                System.out.print("There is one tree.\n");
            }
            else {
                System.out.print("A forest of " + Ans + " trees.\n");
            }
        }
    }
    
    public static boolean tree(int idx, int pre) {
        check[idx] = true;
        
        for(int next : list[idx]) {
            if(next == pre) {
                continue;
            }
            
            if(check[next] == true) {
                return false;
            }
            
            if(tree(next, idx) == false) {
                return false;
            }
            
        }
        return true;
    }
    
    public static void init() {
        list = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }
        check = new boolean[N+1];
    }
}