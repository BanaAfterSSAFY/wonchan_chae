import java.util.*;

class Solution {

    static class Edge {
        int r;
        int c;
        int d;
        
        public Edge(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solution(int[][] land, int height) {
        int cnt = 0;
        int up = 0;

        boolean[][] check = new boolean[land.length][land[0].length];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(0, 0));
        check[0][0] = true;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.d - o2.d;
        });
        
        while(cnt < land.length * land[0].length) {      
            while(q.isEmpty() == false) {
                cnt++;
                Node poll = q.poll();

                for(int i = 0; i < 4; i++) {
                    int nr = poll.r + dir[i][0];
                    int nc = poll.c + dir[i][1];

                    if(nr < 0 || nc < 0 || nr >= land.length || nc >= land[0].length || check[nr][nc]) {
                        continue;
                    }

                    if(Math.abs(land[nr][nc] - land[poll.r][poll.c]) <= height) {
                        q.offer(new Node(nr, nc));
                        check[nr][nc] = true;
                    }
                    else {
                        pq.offer(new Edge(nr, nc, Math.abs(land[nr][nc] - land[poll.r][poll.c])));
                    }
                }
            }

            if(cnt >= land.length * land[0].length) {
                break;
            }

            while(pq.isEmpty() == false) {
                Edge poll = pq.poll();
                if(check[poll.r][poll.c] == false) {
                    q.offer(new Node(poll.r, poll.c));
                    check[poll.r][poll.c] = true;
                    up += poll.d;
                    break;
                }
            }
        }
        
        return up;
    }
}