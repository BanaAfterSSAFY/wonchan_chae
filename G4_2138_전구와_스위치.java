import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();
        String B = br.readLine();

        int[] a = new int[N];
        int[] b = new int[N];

        for(int i = 0; i < N; i++) {
            a[i] = A.charAt(i) - '0';
        }

        for(int i = 0; i < N; i++) {
            b[i] = B.charAt(i) - '0';
        }

        int[] c = Arrays.copyOf(a, N);

        c[0] = 1 - c[0];
        c[1] = 1 - c[1];

        int ans1 = solve(N, a, b);
        int ans2 = solve(N, c, b);

        if(ans2 != -1) {
            ans2++;
        }

        if(ans1 == -1) {
            System.out.println(ans2);
        }
        else if(ans2 == -1) {
            System.out.println(ans1);
        }
        else {
            System.out.println(Math.min(ans2, ans1));
        }
    }

    public static int solve(int n, int[] a, int[] b) {
        int cnt = 0;
        for(int i = 0; i < n-1; i++) {
            if(a[i] != b[i]) {
                cnt++;
                a[i] = 1 - a[i];
                a[i+1] = 1 - a[i+1];
                if(i != n - 2) {
                    a[i+2] = 1 - a[i+2];
                }
            }
        }
        return a[n-1] != b[n-1] ? -1 : cnt;
    }
}