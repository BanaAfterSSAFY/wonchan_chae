import java.io.*;
import java.util.*;

public class Main {

    static int N = 0;
    static int[] par;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N * N + 1][3];
        par = new int[N+1];

        int idx = 1;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int inp = Integer.parseInt(st.nextToken());

                if(i < j) {
                    arr[idx][0] = i;
                    arr[idx][1] = j;
                    arr[idx][2] = inp;
                    idx = idx + 1;
                }
            }
        }

        Arrays.sort(arr, 1, idx, (o1, o2) -> {
            return o1[2] - o2[2];
        });
        
        for(int i = 1; i <= N; i++) {
            par[i] = i;
        }

        long cost = 0;
        for(int i = 1; i < idx; i++) {
            if(find(arr[i][0]) != find(arr[i][1])) {
                cost += arr[i][2];
                union(arr[i][0], arr[i][1]);
            }
        }
        System.out.println(cost);
    }


    public static int find(int a) {
        if(a == par[a]) return a;
        return par[a] = find(par[a]);
    }


    public static void union(int a,int b) {
        a = find(a);
        b = find(b);

        if(a < b){
            par[b] = a;
        }
        else{
            par[a] = b;
        }
    }
}