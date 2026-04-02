import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int n;
        int w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    static int N, M;
    static List<ArrayList<Node>> arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new ArrayList<ArrayList<Node>>();
        
        for(int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        for(int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
                        
            arr.get(p).add(new Node(q, r));
            arr.get(q).add(new Node(p, r));
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int tmp = solve(k, v, N);
            ans.add(tmp);
        }

        for(int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }

    static int solve(int k, int v, int N) {
        int[] check = new int[N+1];
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        check[v] = 1;
 
        while(q.isEmpty() == false) {
            int cur = q.poll();
 
            for(int i = 0; i < arr.get(cur).size(); i++) {
                int w = arr.get(cur).get(i).w;
                int n = arr.get(cur).get(i).n;

                if(w < k || check[n] != 0) {
                    continue;
                }
 
                q.add(n);
                check[n] = 1;
                cnt++;
            }
        }
        return cnt;
    }
}