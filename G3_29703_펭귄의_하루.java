import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r;
        int c;
        int t;
        
        Node(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
    
    static int N, M, SR, SC, HR, HC, F;
    static char[][] arr;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] fish;
    static int[][] mapS;
    static int[][] mapH;
    static int ans = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new char[N][M];
        fish = new int[1000][2];
        mapS = new int[N][M];
        mapH = new int[N][M];
        
        for(int i=0; i<N; i++) {
            String inp = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = inp.charAt(j);
                if(arr[i][j] == 'S') {
                    SR = i;
                    SC = j;
                }
                else if(arr[i][j] == 'H') {
                    HR = i;
                    HC = j;
                }
                else if(arr[i][j] == 'F') {
                    fish[F][0] = i;
                    fish[F][1] = j;
                    F++;
                }
            }
        }

        solve(SR, SC, 0);
        solve(HR, HC, 1);
        
        
        for(int i=0; i<F; i++) {
            if(mapS[fish[i][0]][fish[i][1]] != 0 && mapH[fish[i][0]][fish[i][1]] != 0) {
                ans = Math.min(ans, mapS[fish[i][0]][fish[i][1]] + mapH[fish[i][0]][fish[i][1]] - 2);
            }
        }
        
        if(ans != Integer.MAX_VALUE)
            System.out.println(ans);
        else
            System.out.println(-1);
    }
    
    public static int solve(int sr, int sc, int f) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sr, sc, 1));
        boolean[][] check = new boolean[N][M];
        check[sr][sc] = true;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            if(f == 0) {
                mapS[cur.r][cur.c] = cur.t;
            }
            else {
                mapH[cur.r][cur.c]= cur.t; 
            }
            
            for(int i=0; i<4; i++) {
                int nr = cur.r + dir[i][0];
                int nc = cur.c + dir[i][1];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                
                if(check[nr][nc] == true || arr[nr][nc] == 'D') {
                    continue;
                }
                
                check[nr][nc] = true;
                q.offer(new Node(nr, nc, cur.t+1));
            }
        }
        return -1;
    }
}