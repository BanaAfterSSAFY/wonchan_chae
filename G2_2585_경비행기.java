import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int s;
        int e;
        int d;
        
        Node(int s, int e, int d){
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }
    
    static int T, N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
            
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+2][2];
        
        arr[0][0] = arr[0][1] = 0;
        arr[1][0] = arr[1][1] = 10000;
                
        for(int i=2; i<N+2; i++) {
            st = new StringTokenizer(br.readLine());
            
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int s = 0, e = 1500;
        while(s <= e) {
            int mid = (s + e) / 2;
            
            int res = solve(mid);

            if(res > M) {
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
        }
        
        
        System.out.println(s);
    }
    
    public static int solve(int dist) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        boolean[] check = new boolean[N+2];
        check[0] = true;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            if(cur.e == 1) {
                return cur.s - 1;
            }
            
            if(cur.s > M) {
                continue;
            }
            
            for(int i=0; i<N+2; i++) {
                if(check[i] == true) {
                    continue;
                }
                
                int d = (int) Math.ceil(Math.sqrt(Math.pow(arr[cur.e][0] - arr[i][0], 2) + Math.pow(arr[cur.e][1] - arr[i][1], 2)) / 10);
                if(d <= dist) {
                    check[i] = true;
                    q.offer(new Node(cur.s + 1, i, cur.d + d));
                }
            }
        }
        
        return 1234;
    }
}