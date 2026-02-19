import java.io.*;
import java.util.*;

public class Main {
    
    static int N, K;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }
        
        long Ans = 0;
        map.put(0, 1);
        for(int i=1; i<=N; i++) {
            Ans += map.getOrDefault(arr[i] - K, 0);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }		
        System.out.println(Ans);
    }
}