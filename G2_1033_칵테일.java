import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int b;
        int p;
        int q;

        Node(int b, int p, int q) {
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }

    static int N;
    static List<List<Node>> arr = new ArrayList<>();
    static List<Integer> num = new ArrayList<>();
    static long[] tmp;
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
    
        tmp = new long[N];
    
        for(int i = 0; i < N; i++) {
            tmp[i] = 1;
            arr.add(new ArrayList<>());
        }

        long cnt = 1;
        
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());    
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            arr.get(a).add(new Node(b, p, q));
            arr.get(b).add(new Node(a, q, p));

            cnt *= lcm(p, q);
        }
        
        tmp[0] = cnt;
        bfs(0, N);

        long gcd = tmp[0];
        for(int i = 1; i < tmp.length; i++) {
            gcd = gcd(gcd, tmp[i]);
        }

        for(int i = 0; i < tmp.length; i++) {
            tmp[i] /= gcd;
            System.out.printf(tmp[i] + " ");
        }
        System.out.println();
    }

    public static void bfs(int s,int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[n];

        check[s] = true;
        q.add(s);

        while(q.isEmpty() == false) {
            int cur = q.poll();
            
            for(Node at : arr.get(cur)) {
                if(check[at.b] == false) {
                    tmp[at.b] = ((tmp[cur] * at.q) / at.p);
                    check[at.b] = true;
                    q.add(at.b);
                }
            }
        }
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
}