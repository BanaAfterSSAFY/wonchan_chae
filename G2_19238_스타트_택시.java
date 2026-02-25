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
    
    static int N, M, F;
    static int R, C;
    static int[][] arr;
    static int[][] goal;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        goal = new int[M][4];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                goal[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        
        for(int i=0; i<M; i++) {
            int num = find();
            if(num == -1) {
                System.out.println(-1);
                return;
            }
            num = drive(num);
            if(num == -1) {
                System.out.println(-1);
                return;
            }
        }
        
        System.out.println(F);
    }
    
    public static int find() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R, C, 0));
        boolean[][] check = new boolean[N][N];
        check[R][C] = true;
        
        int ret = 0;
        int dist = Integer.MAX_VALUE;
        int maxR = 0, maxC = 0;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            for(int i=0; i<M; i++) {
                if(cur.r == goal[i][0] && cur.c == goal[i][1]) {
                    
                    if(cur.v < dist) {
                        ret = i+1;
                        dist = cur.v;
                        maxR = cur.r;
                        maxC = cur.c;
                    }
                    else if(cur.v == dist) {
                        if(cur.r < maxR) {
                            ret = i+1;
                            maxR = cur.r;
                            maxC = cur.c;
                        }
                        else if(cur.r == maxR) {
                            if(cur.c < maxC) {
                                ret = i+1;
                                maxC = cur.c;
                            }
                        }
                    }
                }
            }
            
            
            for(int i=0; i<4; i++) {
                int r = cur.r + dir[i][0];
                int c = cur.c + dir[i][1];
                
                if(r < 0 || r >= N || c < 0 || c >= N) {
                    continue;
                }
                
                if(check[r][c] == true || arr[r][c] == 1) {
                    continue;
                }
                
                q.offer(new Node(r, c, cur.v + 1));
                check[r][c] = true;
            }
        }
        
        if(dist <= F) {
            F -= dist;
            R = maxR;
            C = maxC;
            
            goal[ret-1][0] = -1;
            goal[ret-1][1] = -1;
            
            return ret;
        }
        
        return -1;
    }
    
    public static int drive(int num) {
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R, C, 0));
        boolean[][] check = new boolean[N][N];
        check[R][C] = true;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            if(cur.r == goal[num-1][2] && cur.c == goal[num-1][3]) {
                
                if(cur.v <= F) {
                    F += cur.v;
                    R = cur.r;
                    C = cur.c;
                    return arr[cur.r][cur.c];
                }
                else {
                    return -1;
                }
            }
            
            for(int i=0; i<4; i++) {
                int r = cur.r + dir[i][0];
                int c = cur.c + dir[i][1];
                
                if(r < 0 || r >= N || c < 0 || c >= N) {
                    continue;
                }
                
                if(check[r][c] == true || arr[r][c] == 1) {
                    continue;
                }
                
                q.offer(new Node(r, c, cur.v + 1));
                check[r][c] = true;
            }
        }
        return -1;
    }
}