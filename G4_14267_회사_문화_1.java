import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] list;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        for(int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            int inp = Integer.parseInt(st.nextToken());
            if(inp != -1) {
                list[inp].add(i);
            }
        }
        
        arr = new int[N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int inp = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            arr[inp] += t;
            
        }

        solve(1);
        
        for(int i = 1; i < N + 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    static void solve(int idx) {
        for(int at : list[idx]) {
            arr[at] += arr[idx];
            solve(at);
        }
    }
}