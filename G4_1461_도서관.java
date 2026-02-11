import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> plusQ = new PriorityQueue<>((p1, p2) -> p2 - p1);
        PriorityQueue<Integer> minusQ = new PriorityQueue<>((p1, p2) -> p2 - p1);

        int Max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int inp = Integer.parseInt(st.nextToken());

            if(inp > 0) {
                plusQ.offer(inp);
            }
            else {
                minusQ.offer(Math.abs(inp));
            }

            Max = Math.max(Max, Math.abs(inp));
        }

        int Ans = 0;

        while(plusQ.isEmpty() == false) {
            Ans += plusQ.poll() * 2;
            for(int i=0; i<M-1; i++) {
                plusQ.poll();
            }
        }
        
        while(minusQ.isEmpty() == false) {
            Ans += minusQ.poll() * 2;            
            for(int i=0; i<M-1; i++) {
                minusQ.poll();
            }
        }

        Ans -= Max;
        System.out.println(Ans);
    }
}