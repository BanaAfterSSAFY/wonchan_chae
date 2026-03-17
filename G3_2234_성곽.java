import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int N, M, idx;
    static int[][] arr;
    static int[][] check;
    static int[] map;
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int cnt, max, sum;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[M][N];
        map = new int[2510];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        check = new int[M][N];
        
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(check[i][j] == 0) {
                    solve(i, j);
                    cnt++;
                }
            }
        }
        
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(j != N-1 && check[i][j] != check[i][j+1]) {
                    sum = Math.max(sum, map[check[i][j]] + map[check[i][j+1]]);
                }
                
                if(i != M-1 && check[i][j] != check[i+1][j]) {
                    sum = Math.max(sum, map[check[i][j]] + map[check[i+1][j]]);
                }
            }
        }
        
        System.out.println(cnt);
        System.out.println(max);
        System.out.println(sum);
        
    }
    
    public static void solve(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        check[r][c] = ++idx;
        int res = 1;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            for(int i=0; i<4; i++) {
                if((arr[cur.r][cur.c] & (1 << i)) == (1 << i)) {
                    continue;
                }
                
                int nr = cur.r + dir[i][0];
                int nc = cur.c + dir[i][1];
                
                if(nr < 0 || nr >= M || nc < 0 || nc >= N) {
                    continue;
                }
                
                if(check[nr][nc] != 0) {
                    continue;
                }
                
                check[nr][nc] = idx;
                q.offer(new Node(nr, nc));
                res++;
            }
        }
        max = Math.max(max, res);
        map[idx] = res;
    }
}