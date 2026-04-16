import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static int[][] arr;
    static int[] dir = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][6];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 6; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = -1;

        for(int i = 0; i < 6; i++) {
            int max = 0;
            for(int j = 0; j <6; j++) {
                if(j == i || j == dir[i]) {
                    continue;
                }
                max = Math.max(max, arr[0][j]);
            }
            solve(arr[0][i], max, 1);
        }
        System.out.println(ans);
    }

    public static void solve(int num, int sum, int cnt) {
        if(cnt == N) {
            ans = Math.max(sum, ans);
            return;
        }

        int idx = 0;
        for(int i = 0; i <6; i++) {
            if(arr[cnt][i] == num) {
                idx = i; 
                break;
            }
        }

        int next = dir[idx];
        int m = 0;
        for(int j = 0; j < 6; j++) { 
            if(j == next || j == idx) {
                continue;
            }

            m = Math.max(m, arr[cnt][j]);
        }
        solve(arr[cnt][next], sum + m, cnt + 1);
    }
}
