import java.io.*;
import java.util.*;


public class Main {
    
    static int K, N;
    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        arr = new long[K];
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<K; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            pq.offer(arr[i]);
        }

        int idx = 0;
        
        while(true) {
            long cur = pq. poll();
            idx++;
            
            if(idx == N) {
                System.out.println(cur);
                break;
            }
            
            for(int i=0; i<K; i++) {
                pq.offer(cur * arr[i]);
                if(cur % arr[i] == 0) break;
            }
        }
        
    }

}