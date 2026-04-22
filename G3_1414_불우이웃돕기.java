import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int start;
        int end;
        int cost;

        Node(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int[] par;

    public static void main(String[] args) throws IOException {

        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int sz = 0;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();

            for(int j = 0; j < N; j++) {
                int tmp = str.charAt(j) - 'a' + 1;
                if(tmp < 0) {
                    if(tmp == -48) {
                        continue;
                    }
                    tmp += 58;
                }
                arr[i][j] = tmp;
                sz += tmp;
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> {
            return o1.cost - o2.cost;
        }
        );

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j || arr[i][j] == 0) {
                    continue;
                }

                q.add(new Node(i, j, arr[i][j]));
            }
        }

        int ans = 0;
        int cnt = 0;
        par = new int[N];

        for(int i = 0; i < par.length; i++) {
            par[i] = i;
        }

        while(q.isEmpty() == false) {
            Node cur = q.poll();

            int a = find(cur.start);
            int b = find(cur.end);

            if(a != b) {
                union(a,b);
                ans += cur.cost;
                cnt++;
            }
        }

        if(cnt == N - 1) {
            System.out.println(sz - ans);
        }
        else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) {
            return;
        }
        par[b] = a;
    }

    public static int find(int a) {
        if(par[a] == a) {
            return a;
        }
        return par[a] = find(par[a]);
    }
}