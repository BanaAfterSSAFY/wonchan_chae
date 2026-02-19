import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    static int N, M;
    static int[][] arr;
    static int[][] check;
    static int[] val;
    static int idx = 1;
    static int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        check = new int[N][M];
        val = new int[N * M / 2 + 2];
        
        for(int i=0; i<N; i++) {
            String inp = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = inp.charAt(j) - '0';
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j] == 0 && check[i][j] == 0) {
                    solve(i, j);
                }
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j] == 1) {
                    int ans = 1;
                    int[] tmp = {0, 0, 0, 0};
                    
                    for(int d=0; d<4; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        
                        if(r < 0 || r >= N || c < 0 || c >= M) {
                            continue;
                        }
                        
                        boolean flag = true;
                        for(int p=0; p<d; p++) {
                            if(tmp[p] == check[r][c]) {
                                flag = false;
                                break;
                            }
                        }
                        
                        if(flag == true) {
                            ans += val[check[r][c]];
                            tmp[d] = check[r][c];
                        }
                    }
                    System.out.print(ans % 10);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
        
    }
    
    public static void solve(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        check[r][c] = idx;
        int cnt = 1;
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            for(int i=0; i<4; i++) {
                int nr = cur.r + dir[i][0];
                int nc = cur.c + dir[i][1];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                
                if(check[nr][nc] == idx || arr[nr][nc] != 0) {
                    continue;
                }
                
                q.offer(new Node(nr, nc));
                check[nr][nc] = idx;
                cnt++;
            }
        }
        
        val[idx++] = cnt;
    }
}