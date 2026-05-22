import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int c;
        int d;

        public Node(int x, int y, int c, int d) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    static int N, answer;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1},  {0, -1}};
    static int[][][] check;
    
    public static void bfs(int[][] arr) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0, -1)); 
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 4; k++) {
                    check[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        while(pq.isEmpty() == false) {
            Node cur = pq.poll();

            if(cur.x == N - 1 && cur.y == N - 1) {
                answer = Math.min(answer, cur.c);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == 1) {
                    continue;
                }

                int nc = cur.c;
                
                if(cur.d == -1) {
                    nc += 100;
                }
                else if(cur.d == i) {
                    nc += 100;
                }
                else {
                    nc += 600;
                }

                if(check[nx][ny][i] > nc) {
                    check[nx][ny][i] = nc;
                    pq.add(new Node(nx, ny, nc, i));
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;
        check = new int[N][N][4];
        bfs(board);
        return answer;
    }
}