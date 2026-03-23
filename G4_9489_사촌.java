import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M;
    static int[] par = new int[1000001];
    static int[] uar = new int[1000001];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
            
        while(true) {
            
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            if(N == 0 && M == 0) {
                break;
            }
            
            int[] arr = new int[N];
            int parIdx = -1;
            st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                par[arr[i]] = 0;
                uar[arr[i]] = -1;
            }
            
            for(int i=1; i<N; i++) {
                int inp = arr[i];
                
                if(arr[i] - arr[i-1] != 1) {
                    parIdx++;						
                }
                uar[inp] = arr[parIdx];
                par[arr[parIdx]]++;
            }
            
            int ans = 0;
            if(M != arr[0] && uar[M] != -1 && uar[uar[M]] != -1) {
                int parent = uar[M];
                int grand = uar[parent];
                
                for(int i = 0; i < N; i++) {
                    int cnt = arr[i];

                    if(uar[cnt] == grand && cnt != parent) {
                        ans += par[cnt];
                    }
                }
            }
            System.out.println(ans);
        }		
    }
}