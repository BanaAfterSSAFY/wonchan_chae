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

    static int N, M, T;
    static int[][] arr;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int cur = x;
            while(cur <= N){
                turn(cur, d, k);
                cur += x;
            }

            int delete = 0, sum = 0, num = 0;
            for(int j=1; j<=N; j++){
                for(int p=1; p<=M; p++){
                    if(arr[j][p] != 0){
                        delete += solve(j, p);
                    }
                    sum += arr[j][p];
                    if(arr[j][p] != 0){
                        num++;
                    }
                }
            }

            if(delete == 0){

                for(int j=1; j<=N; j++){
                    for(int p=1; p<=M; p++){
                        if(arr[j][p] == 0) continue;
                        
                        if(arr[j][p] * num < sum){
                            arr[j][p]++;
                        }
                        else if(arr[j][p] * num> sum){
                            arr[j][p]--;
                        }
                    }
                }
            }
        }

        int sum = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void turn(int x, int d, int k) {
        while(k-- != 0){
            if(d == 0){
                int tmp = arr[x][M];
                for(int i=M; i>1; i--){
                    arr[x][i] = arr[x][i-1];
                }
                arr[x][1] = tmp;
            }
            else{
                int tmp = arr[x][1];
                for(int i=1; i<M; i++){
                    arr[x][i] = arr[x][i+1];
                }
                arr[x][M] = tmp;
            }
        }
    }

    public static int solve(int r, int c) {
        int res = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        int tmp = arr[r][c];
        arr[r][c] = 0;
        while(q.isEmpty() == false){
            Node cur = q.poll();

            for(int i=0; i<4; i++){
                int nr = cur.r + dir[i][0];
                int nc = cur.c + dir[i][1];

                if(nr < 1 || nr > N){
                    continue;
                }

                if(nc == 0){
                    nc = M;
                }
                else if(nc == M + 1){
                    nc = 1;
                }

                if(arr[nr][nc] == 0){
                    continue;
                }

                if(arr[nr][nc] == tmp){
                    q.offer(new Node(nr, nc));
                    arr[nr][nc] = 0;
                    res++;
                }
            }
        }

        if(res == 0){
            arr[r][c] = tmp;
        }

        return res;
    }
}