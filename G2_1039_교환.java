import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        String s;
        int n;

        Node(String s,int n) {
            this.s = s;
            this.n = n;
        }
    }
    static int N, M, K;
    static int ans = -1;
    static boolean[][] check = new boolean[1000001][11];
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String first = st.nextToken();
        q.add(new Node(first,0));

        N = Integer.parseInt(first);
        M = first.length();
        K = Integer.parseInt(st.nextToken());
        
        check[N][0] = true;
        
        bfs();
        
        System.out.println(ans);
    }

    static void bfs() {
        while(q.isEmpty() == false) {
            Node cur = q.poll();

            if(cur.n == K)  {
                if(ans < Integer.parseInt(cur.s)) {
                    ans = Integer.parseInt(cur.s);
                }
                continue;
            }
            else if(cur.n > K) {
                return;
            }

            for(int i = 0; i < M; i++) {
                for(int j = i + 1; j < M; j++) {
                    char[] arr = cur.s.toCharArray();
                    char tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;

                    String next = new String(arr);
                    if(arr[0] != '0' && check[Integer.parseInt(next)][cur.n + 1] == false) {
                        check[Integer.parseInt(next)][cur.n + 1] = true;
                        q.add(new Node(next, cur.n + 1));
                    }
                }
            }

        }

    }
}