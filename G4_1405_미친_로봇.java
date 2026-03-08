import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static double ans;
    static double[] arr;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        arr = new double[4];
        check = new boolean[29][29];
        
        arr[0] = E / 100.0;
        arr[1] = W / 100.0;
        arr[2] = S / 100.0;
        arr[3] = N / 100.0;

        check[14][14] = true;
        solve(14, 14, 0, 1.0);

        System.out.printf("%.9f", ans);
    }

    static void solve(int r, int c, int cnt, double val) {

        if(cnt == N) {
            ans += val;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];

            if(check[nr][nc] == false && arr[i] > 0) {
                check[nr][nc] = true;
                solve(nr, nc, cnt + 1, val * arr[i]);
                check[nr][nc] = false;
            }
        }
    }
}