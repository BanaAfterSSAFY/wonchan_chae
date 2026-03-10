import java.io.*;
import java.util.*;

public class Main {

    static int T, N, M;
    static int[][] arr; 
    static int[] up, down;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        
        arr = new int[N][2];
        up = new int[N];
        down = new int[N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            arr[i][0] = a;
            arr[i][1] = b;
            
            up[i] = a + b;
            down[i] = a - b;
        }
        
        Arrays.sort(up);
        Arrays.sort(down);
        
        for(int i=0; i<N; i++) {
            int l = i+1, m = i+1;
            int s = 0, e = N;
            while(s < e) {
                int mid = (s + e) / 2;
                if(up[mid] < arr[i][0] - arr[i][1]) {
                    s = mid + 1;
                }
                else {
                    e = mid;
                }
            }
            l = s + 1;
            
            s = 0;
            e = N;
            while(s < e) {
                int mid = (s + e) / 2;
                if(down[mid] <= arr[i][0] + arr[i][1]) {
                    s = mid + 1;
                }
                else {
                    e = mid;
                }
            }
            m = s;
            
            sb.append(l + " " + m + "\n");
        }
        System.out.println(sb);
    }
}