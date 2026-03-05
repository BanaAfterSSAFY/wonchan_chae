import java.io.*;
import java.util.*;

public class Main {
    
    static int N, A, B;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int cnt = 0;
        
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                int dist = arr[i][j];
                boolean flag = true;
                
                for(int k=0; k<N; k++) {
                    if(k == i || k == j) {
                        continue;
                    }
                    if(dist == arr[i][k] + arr[k][j]) {
                        flag = false;
                    }
                    else if(dist > arr[i][k] + arr[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
                
                if(flag == true) {
                    cnt += dist;
                }
            }
        }
        System.out.println(cnt);
    }
}