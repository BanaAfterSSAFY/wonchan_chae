import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, L;
    static int[] arr;
    static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if(y > L) {
                continue;
            }
            
            int s = 0, e = M-1;
            int idx = 0;
            while(s <= e) {
                int mid = (s + e) / 2;
                
                if(x < arr[mid]) {
                    e = mid - 1;
                }
                else {
                    idx = mid;
                    s = mid + 1;
                }
            }
            
            if(Math.abs(arr[idx] - x) + y <= L) {
                ans++;
            }
            else if(idx+1 < M && Math.abs(arr[idx+1] - x) + y <= L) {
                ans++;
            }
        }
        
        System.out.println(ans);
    }
    
}