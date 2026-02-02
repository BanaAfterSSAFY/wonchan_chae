import java.io.*;
import java.util.*;

class Node {
    int r;
    int c;
    int t;
    int k;
    
    public Node(int r, int c, int t, int k) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.k = k;
    }
}

public class Main {
    
    static int N;
    static char[][] arr;
    static boolean[][][] check;
    static int Ans = Integer.MAX_VALUE;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        arr = new char[N][M];
        check = new boolean[N][M][64];
        Queue<Node> q = new LinkedList<>();
        
        for(int i=0; i<N; i++) {
            String inp = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = inp.charAt(j);
                if(arr[i][j] == '0') {
                    q.offer(new Node(i, j, 0, 0));
                    check[i][j][0] = true;
                }
            }
        }
        
        while(q.isEmpty() == false) {
            Node cur= q.poll();
            
            for(int d=0; d<4; d++) {
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                
                if(check[nr][nc][cur.k] == true || arr[nr][nc] == '#') {
                    continue;
                }
                
                if(arr[nr][nc] == '1') {
                    Ans = Math.min(Ans, cur.t + 1);
                }
                else if(arr[nr][nc] - 'a' >= 0 && arr[nr][nc] - 'a' < 26) {
                    int temp = 1 << (arr[nr][nc] - 'a');
                    
                    q.offer(new Node(nr, nc, cur.t+1, cur.k | temp));
                    check[nr][nc][cur.k | temp] = true;
                }
                else if(arr[nr][nc] - 'A' >= 0 && arr[nr][nc] - 'A' < 26) {
                    int temp = 1 << (arr[nr][nc] - 'a');
                    if((cur.k & temp) == temp) {
                        q.offer(new Node(nr, nc, cur.t+1, cur.k));
                        check[nr][nc][cur.k] = true;
                    }
                }
                else {
                    q.offer(new Node(nr, nc, cur.t+1, cur.k));
                    check[nr][nc][cur.k]= true; 
                }
            }
        }
        
        if(Ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(Ans);
        }
    }
}