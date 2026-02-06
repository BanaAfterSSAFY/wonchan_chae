import java.io.*;
import java.util.*;

public class Main {
    
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        arr = new int[C + 101];
        
        Arrays.fill(arr, 1000000000);
        arr[0] = 0;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int cost = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            
            for(int j=num; j<C+101; j++) {
                arr[j] = Math.min(arr[j], arr[j - num] + cost);
            }
            
        }
        int Ans = 1000000000;
        for(int i=C; i<C+101; i++) {
            Ans = Math.min(Ans, arr[i]);
        }
        
        System.out.println(Ans);
        
    }

}