import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r;
        int c;
        int v;
        
        Node(int r, int c, int v){
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    
    static int N, M;
    static int[][] arr;
    static boolean[][] check;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int Ans, maxB, maxR;
    static Node maxN;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        check = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(true) {
            maxB = 0;
            maxR = 0;
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    check[i][j] = false;
                }
            }
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(arr[i][j] > 0 && check[i][j] == false) {
                        blockSet(i, j);
                    }
                }
            }
            
            if(maxB <= 1) {
                break;
            }
            
            remove(maxN);
            
            gravity();
            
            roll();
            
            gravity();
        }
        
        System.out.println(Ans);
    }

    
    public static void blockSet(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] tmp = new boolean[N][N];
        q.offer(new Node(r, c, arr[r][c]));
        check[r][c] = true;
        tmp[r][c] = true;
        
        int sizeB = 0, sizeR = 0;
        Node B = new Node(r, c, arr[r][c]);

        while(q.isEmpty() == false) {
            Node cur = q.poll();
            sizeB++;
            if(cur.v == 0) {
                sizeR++;
            }
            
            for(int d=0; d<4; d++) {
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                
                if(arr[nr][nc] == -1 || tmp[nr][nc] == true) {
                    continue;
                }
                
                if(arr[nr][nc] == arr[r][c]) {
                    check[nr][nc] = true;
                    q.offer(new Node(nr, nc, arr[nr][nc]));
                    tmp[nr][nc] = true;
                }
                else if(arr[nr][nc] == 0) {
                    q.offer(new Node(nr, nc, arr[nr][nc]));
                    tmp[nr][nc] = true;
                }
            }
        }
        
        if(maxB < sizeB) {
            maxB = sizeB;
            maxR = sizeR;
            maxN = B;
        }
        else if(maxB == sizeB) {
            if(maxR < sizeR) {
                maxR = sizeR;
                maxN = B;
            }
            else if(maxR == sizeR) {
                if(maxN.r < B.r) {
                    maxN = B;
                }
                else if(maxN.r == B.r) {
                    if(maxN.c < B.c) {
                        maxN = B;
                    }
                }
            }
        }
        
        return;
    }
    
    public static void remove(Node s) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] tmp = new boolean[N][N];
        
        q.offer(new Node(s.r, s.c, arr[s.r][s.c]));
        tmp[s.r][s.c] = true;
        arr[s.r][s.c] = -2;
                
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            for(int d=0; d<4; d++) {
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                
                if(arr[nr][nc] == -1 || tmp[nr][nc] == true) {
                    continue;
                }
                
                if(arr[nr][nc] == s.v || arr[nr][nc] == 0) {
                    q.offer(new Node(nr, nc, arr[nr][nc]));
                    tmp[nr][nc] = true;
                    arr[nr][nc] = -2;
                }
                
                
            }
        }
        
        Ans += maxB * maxB;
        
        return;
    }
    
    public static void gravity() {
        for(int i=N-2; i>-1; i--) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] < 0) {
                    continue;
                }
                
                int r = i;
                while(true) {
                    if(r + 1 >= N) {
                        break;
                    }
                    if(arr[r + 1][j] != -2) {
                        break;
                    }
                    
                    r++;
                }
                if(r != i) {
                    arr[r][j] = arr[i][j];
                    arr[i][j] = -2;
                }
            }
        }
        return;
    }
    
    public static void roll() {
        int[][] tmp = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tmp[N-1-j][i] = arr[i][j];
            }
        }
        arr = tmp;
        return;
    }
}