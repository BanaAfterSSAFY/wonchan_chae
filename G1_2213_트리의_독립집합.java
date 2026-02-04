import java.io.*;
import java.util.*;


public class Main {
    
    static int N;
    static int[] arr;
    static List<Integer>[] map;
    static int[][] dp;
    static boolean[] check;
    static int Ans;
    static List<Integer> answer = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        check = new boolean[N+1];
        map = new ArrayList[N+1];
        dp = new int[N+1][2];
        
        for(int i=1; i<=N; i++) {
            map[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][1] = arr[i];
        }
        
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            map[a].add(b);
            map[b].add(a);
        }
        
        
        int on = solve(0, 1, 1);
        int off = solve(0, 1, 0);
        System.out.println(Math.max(on, off));
        
        trace(1, 0, 0);
        
        Collections.sort(answer);
        
        for(int at : answer) {
            System.out.print(at + " ");
        }
        System.out.println();
        
    }
    
    public static int solve(int cur, int idx, int f) {
        if(check[idx] == true)
            return dp[idx][f];
        
        check[idx] = true;
        
        for(int at : map[idx]) {
            if(cur == at) continue;
            dp[idx][0] += Math.max(solve(idx, at, 1), solve(idx, at, 0));
        }
        
        for(int at : map[idx]) {
            if(cur == at) continue;
            dp[idx][1] += solve(idx, at, 0);
        }
        
        return dp[idx][f];
    }
    
    public static void trace(int idx, int pre, int c) {
        if(c == 1) {
            for(int at : map[idx]) {
                if(at != pre) {
                    trace(at, idx, 0);
                }
            }
        }
        else {
            if(dp[idx][0] < dp[idx][1]) {
                answer.add(idx);
                for(int at : map[idx]) {
                    if(at != pre) {
                        trace(at, idx, 1);
                    }
                }
            }
            else {
                for(int at : map[idx]) {
                    if(at != pre) {
                        trace(at, idx, 0);
                    }
                }
            }
        }
    }
}