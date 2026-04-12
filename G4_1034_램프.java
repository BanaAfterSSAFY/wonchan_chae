import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static String[] arr;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new String[N];
        cnt = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            for(int j = 0; j < M; j++) {
                if(arr[i].charAt(j) == '0') {
                    cnt[i]++;
                }
            }
        }

        K = Integer.parseInt(br.readLine());

        int max = 0;
        for(int i = 0; i < N; i++) {
            if(cnt[i] <= K && (cnt[i] - K) % 2 == 0) {
                int tmp = 1;
                for(int j = 0; j < N; j++) {
                    if(j == i) {
                        continue;
                    }

                    if(arr[i].equals(arr[j])) {
                        tmp++;
                    }
                }

                max = Math.max(max, tmp);
            }
        }

        System.out.println(max);
    }
}