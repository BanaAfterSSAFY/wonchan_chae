import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int num;
        int time;
        
        Node(int n, int t){
            num = n;
            time = t;
        }
    }
    static int N, M, T;
    static List<Integer>[] arr;
    static int[] degree;
    static int[] ans;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        degree = new int[N+1];
        ans = new int[N+1];
        arr = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            arr[a].add(b);
            degree[b]++;
        }
        
        Queue<Node> q = new LinkedList<>();
        
        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                q.offer(new Node(i, 1));
            }
        }
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            ans[cur.num] = cur.time;
            
            for(int at : arr[cur.num]) {
                degree[at]--;
                if(degree[at] == 0) {
                    q.offer(new Node(at, cur.time + 1));
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}