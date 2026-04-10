import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] cnt = new int[N + 1];
        int[][] nrr = new int[N + 1][N + 1];
        boolean[] check = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nrr[a][b] = Integer.parseInt(st.nextToken());
            cnt[b]++;
            check[a] = true;
        }

        Queue q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(cnt[i] == 0) {
                q.add(i);
                arr[i] = 1;
            }
        }

        while(q.isEmpty() == false) {

            int n = (int) q.poll();
            
            for(int i = 1; i <= N; i++) {
                if(nrr[n][i] != 0) {
                    arr[i] += arr[n] * nrr[n][i];
                    cnt[i]--;

                    if(cnt[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            if(check[i] == false) {
                System.out.print(i + " " + arr[i] + "\n");
            }
        }

    }
}