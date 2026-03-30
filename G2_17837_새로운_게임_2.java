import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[][] arr;
    static int[][] horse;
    static int[][] dir = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static Deque<Integer>[][] dq;
    static boolean flag;
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1][N+1];
        horse = new int[M+1][3];
        dq = new ArrayDeque[13][13];
        for(int i=0; i<13; i++) {
            for(int j=0; j<13; j++) {
                dq[i][j] = new ArrayDeque<>();
            }
        }
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            
            horse[i][0] = Integer.parseInt(st.nextToken());
            horse[i][1] = Integer.parseInt(st.nextToken());
            horse[i][2] = Integer.parseInt(st.nextToken());
            
            dq[horse[i][0]][horse[i][1]].offerLast(i);
        }
        
        int t = 0;
        while(t <= 1000) {
            t++;
            for(int i=1; i<=M; i++) {
                
                move(i, false);

            }
            
            if(flag == true) {
                break;
            }
            
        }
        
        if(t == 1001) {
            System.out.println(-1);
        }
        else {
            System.out.println(t);
        }
    }
    
    public static void print() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                System.out.print(dq[i][j].size() + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        for(int i=1; i<=M; i++) {
            System.out.print(horse[i][2] + " ");
        }
        System.out.println("\n");
    }
    
    public static void move(int num, boolean f) {
        int nr = horse[num][0] + dir[horse[num][2]][0];
        int nc = horse[num][1] + dir[horse[num][2]][1];
        
        if(nr < 1 || nr > N || nc < 1 || nc > N) {
            if(f == true) return;
            horse[num][2] = changeDir(horse[num][2]);
            move(num, true);
            return;
        }
        
        if(arr[nr][nc] == 2) {
            if(f == true) return;
            horse[num][2] = changeDir(horse[num][2]);
            move(num, true);
            return;
        }
        
        else if(arr[nr][nc] == 0) {
            boolean t = false;
            int r = horse[num][0];
            int c = horse[num][1];
            int sz = dq[r][c].size();
            for(int i=0; i<sz; i++) {
                int cur = dq[r][c].pollFirst();
                
                if(cur == num) {
                    t = true;
                }
                
                if(t == false) {
                    dq[r][c].offerLast(cur);
                }
                else {
                    dq[nr][nc].offerLast(cur);
                    horse[cur][0] = nr;
                    horse[cur][1] = nc;
                }
            }
        }
        
        else if(arr[nr][nc] == 1) {
            int r = horse[num][0];
            int c = horse[num][1];
            int sz = dq[r][c].size();
            for(int i=0; i<sz; i++) {
                int cur = dq[r][c].pollLast();
                dq[nr][nc].offerLast(cur);
                horse[cur][0] = nr;
                horse[cur][1] = nc;
                
                if(cur == num) {
                    break;
                }
                
                    
            }
        }
        
        if(dq[nr][nc].size() >= 4) {
            flag = true;
        }
    }
    
    public static int changeDir(int d) {
        if(d == 1) return 2;
        if(d == 2) return 1;
        if(d == 3) return 4;
        if(d == 4) return 3;
        return 0;
    }

}