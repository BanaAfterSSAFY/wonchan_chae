import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int[][] arr = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr, (o1, o2) -> o2[1] - o1[1]);
        
        int ans = arr[0][1] - arr[0][0];
        
        for(int i = 1; i < N; i++) {
            if(arr[i][1] < ans) {
                ans = arr[i][1];
            }
            ans -= arr[i][0];
        }
        
        if(ans > 0) {
            System.out.println(ans);
        } 
        else {
            System.out.println(-1);
        }
    }

}