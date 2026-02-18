import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            
            PriorityQueue<Long> pq = new PriorityQueue<>();
            
            for(int i=0; i<N; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            
            long sum = 0;
            
            while(pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                sum += a + b;
                pq.add(a + b);
            }
            System.out.println(sum);
        }
    }
}