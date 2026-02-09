import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int dead;
        int cup;
        
        Node(int d, int c){
            dead = d;
            cup = c;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.cup != o.cup) {
                return o.cup - this.cup;
            }
            return this.dead - o.dead;
        }
    }
    
    static int N;
    static Node[] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new Node[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            arr[i] = new Node(D, C);
        }
        
        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.dead != o2.dead) {
                    return o2.dead - o1.dead;
                }
                return o2.cup - o1.cup;
            }
        });
        
        int cnt = 0, Ans = 0;
        
        for(int i=200000; i>0; i--) {
            while(cnt < N && arr[cnt].dead == i) {
                pq.offer(arr[cnt++]);
            }
            
            if(pq.isEmpty()) {
                continue;
            }
            
            Ans += pq.poll().cup;
        }

        System.out.println(Ans);
    }
}