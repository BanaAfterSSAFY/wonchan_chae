import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r;
        int c;
        int v;
        
        Node(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    
    static int T, N, M, R, C;
    static int[][] arr;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2) {
                    R = i;
                    C = j;
                }
            }
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        int[][] check = new int[N][M];
        check[0][0] = 1;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            for(int i=0; i<4; i++) {
                int r = cur.r + dir[i][0];
                int c = cur.c + dir[i][1];
                
                if(r < 0 || r >= N || c < 0 || c >= M) {
                    continue;
                }
                
                if(check[r][c] != 0 || arr[r][c] == 1) {
                    continue;
                }
                
                check[r][c] = cur.v;
                q.offer(new Node(r, c, cur.v+1));
            }
        }
        
        ans = Math.min(check[N-1][M-1] == 0 ? Integer.MAX_VALUE : check[N-1][M-1], check[R][C] == 0 ? Integer.MAX_VALUE : check[R][C] + Math.abs(N-1 - R) + Math.abs(M-1 - C));
        
        if(ans <= T) {
            System.out.println(ans);
        }
        else {
            System.out.println("Fail");
        }
    }
}