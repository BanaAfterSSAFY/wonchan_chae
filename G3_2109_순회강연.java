import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int val;
        int day;
        
        Node(int v, int d) {
            val = v;
            day = d;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.day != o.day)
                return this.day - o.day;
            return o.val - this.val;
        }
    }
    
    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        List<Node> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            list.add(new Node(p, d));
        }
        
        Collections.sort(list);
        int day = Integer.MAX_VALUE;
        
        for(Node at : list) {
            pq.offer(at.val);
            
            if(pq.size() > at.day) {
                pq.poll();
            }
        }
        
        int ans = 0;
        while(pq.isEmpty() == false) {
            ans += pq.poll();
        }
        
        System.out.println(ans);
        
    }
}