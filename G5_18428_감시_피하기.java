import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static char[][] arr;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Node> teacher;
    static boolean ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new char[N][N];
        teacher = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = st.nextToken().charAt(0);
                if(arr[i][j] == 'T') {
                    teacher.add(new Node(i, j));
                }
            }
        }

        perm(0, 0);

        if(ans == true) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    public static void perm(int idx, int cnt) {
        if(cnt == 3) {
            check();
            return;
        }

        for(int i=idx; i<N*N; i++) {
            int r = i / N;
            int c = i % N;

            if(arr[r][c] == 'X') {
                arr[r][c] = 'O';
                perm(i + 1, cnt + 1);
                arr[r][c] = 'X';
            }
        }
    }

    public static void check() {
        boolean flag = true;

        for(Node cur : teacher) {
            for(int i=0; i<4; i++) {
                int r = cur.r + dir[i][0];
                int c = cur.c + dir[i][1];

                while(true) {
                    if(r < 0 || r >= N || c < 0 || c >= N) {
                        break;
                    }

                    if(arr[r][c] == 'O') {
                        break;
                    }

                    if(arr[r][c] == 'S') {
                        flag = false;
                        break;
                    }

                    r += dir[i][0];
                    c += dir[i][1];
                }
            }
        }

        if(flag == true) {
            ans = true;
        }
    }
}