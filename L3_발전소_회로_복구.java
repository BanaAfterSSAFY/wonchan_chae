import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int m, l, d;
        
        Node(int m, int l, int d) {
            this.m = m;
            this.l = l;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static int n, m, p;
    static int[][] map; 
    static int[] arr;
    static int[] mask;

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        
        this.n = grid.length;
        this.m = grid[0].length();
        this.p = panels.length;

        int er = 0, ec = 0;
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i].charAt(j) == '@') {
                    er = i;
                    ec = j;
                    flag = true;
                    break;
                }
            }
            if(flag == true) {
                break;
            }
        }

        map = new int[p][p];
        arr = new int[p];
        for(int i = 0; i < p; i++) {
            int[][] tmp = bfs(grid, panels[i][1] - 1, panels[i][2] - 1);
            
            for(int j = 0; j < p; j++) {
                map[i][j] = tmp[panels[j][1] - 1][panels[j][2] - 1];
            }
            arr[i] = tmp[er][ec];
        }

        mask = new int[p];
        for(int[] at : seqs) {
            mask[at[1] - 1] |= (1 << (at[0] - 1));
        }

        return solve(panels);
    }

    static int solve(int[][] panels) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] tmp = new int[1 << p][p];

        for(int[] at : tmp) {
            Arrays.fill(at, 100000000);
        }

        for(int i = 0; i < p; i++) {

            if(mask[i] == 0) {
                int d;
                if(i == 0) {
                    d = 0;
                }
                else {
                    d = dir(0, i, panels);
                }
                
                if(tmp[1 << i][i] > d) {
                    tmp[1 << i][i] = d;
                    pq.offer(new Node(1 << i, i, d));
                }
            }
        }

        int fullMask = (1 << p) - 1;
        while(pq.isEmpty() == false) {
            Node cur = pq.poll();

            if(cur.d > tmp[cur.m][cur.l]) {
                continue;
            }

            if(cur.m == fullMask) {
                return cur.d;
            }

            for(int next = 0; next < p; next++) {
                if((cur.m & (1 << next)) == 0 && (cur.m & mask[next]) == mask[next]) {
                    int nt = cur.m | (1 << next);
                    int d = dir(cur.l, next, panels);
                    
                    if(tmp[nt][next] > cur.d + d) {
                        tmp[nt][next] = cur.d + d;
                        pq.offer(new Node(nt, next, tmp[nt][next]));
                    }
                }
            }
        }
        return -1;
    }

    static int dir(int u, int v, int[][] panels) {
        if(panels[u][0] == panels[v][0]) {
            return map[u][v];
        }
        return arr[u] + Math.abs(panels[u][0] - panels[v][0]) + arr[v];
    }

    static int[][] bfs(String[] grid, int sr, int sc) {
        int[][] d = new int[n][m];
        for(int[] at : d) {
            Arrays.fill(at, -1);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        d[sr][sc] = 0;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        
        while(q.isEmpty() == false) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i], nc = cur[1] + dc[i];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr].charAt(nc) != '#' && d[nr][nc] == -1) {
                    d[nr][nc] = d[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return d;
    }
}