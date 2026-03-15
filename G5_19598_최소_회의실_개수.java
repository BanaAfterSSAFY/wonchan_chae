import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int s;
        int e;

        public Node (int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node next) {
            if (this.s == next.s) {
                return this.e - next.e;
            }
            else {
                return this.s - next.s;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        Node[] arr = new Node[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            arr[i] = new Node(a, b);
        }

        Arrays.sort(arr);

        pq.offer(arr[0].e);

        for(int i = 1; i < N; i++) {
            if(arr[i].s >= pq.peek()) {
                pq.poll();
            }

            pq.offer(arr[i].e);
        }

        System.out.println(pq.size());
    }
}