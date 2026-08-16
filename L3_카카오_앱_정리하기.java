import java.util.*;

class Solution {

    static class Node {
        int r, c, id;
        Node(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id; 
        }
    }

    static int N, M;
    static int[][] board;

    static int[][] dir = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int[][] solution(int[][] board, int[][] commands) {
        this.N = board.length;
        this.M = board[0].length;
        this.board = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            this.board[i] = board[i].clone();
        }
        
        for(int[] at : commands) {
            solve(at[0], at[1]);
        }
        
        return this.board;
    }
    
    static void solve(int start, int d) {
        Set<Integer> group = get(start, d);
        move(group, d);
        
        while(true) {
            List<Integer> tmp = find(d);
            if(tmp.isEmpty()) {
                break;
            }
            
            int cnt = tmp.get(0);
            Set<Integer> newGroup = get(cnt, d);
            move(newGroup, d);
        }
    }
    
    static Set<Integer> get(int start, int d) {
        Set<Integer> group = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        
        group.add(start);
        q.offer(start);
        
        while(q.isEmpty() == false) {
            int cur = q.poll();
            
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    if(board[r][c] != cur) {
                        continue;
                    }

                    int nr = (r + dir[d][0] + N) % N;
                    int nc = (c + dir[d][1] + M) % M;
                    
                    int next = board[nr][nc];
                    if(next != 0 && group.contains(next) == false) {
                        group.add(next);
                        q.offer(next);
                    }
                }
            }
        }
        
        return group;
    }
    
    static void move(Set<Integer> group, int d) {
        List<Node> list = new ArrayList<>();
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(group.contains(board[r][c])) {
                    list.add(new Node(r, c, board[r][c]));
                }
            }
        }
        
        for(Node at : list) {
            board[at.r][at.c] = 0;
        }
        
        for(Node at : list) {
            int nr = (at.r + dir[d][0] + N) % N;
            int nc = (at.c + dir[d][1] + M) % M;
            board[nr][nc] = at.id;
        }
    }
    
    static List<Integer> find(int d) {
        List<Integer> tmp = new ArrayList<>();
        boolean[] added = new boolean[101];
        
        if(d == 1 || d == 3) {
            for(int r = 0; r < N; r++) {
                int left = board[r][0];
                int right = board[r][M - 1];
                
                if(left == 0 || left != right) {
                    continue;
                }
                
                boolean flag = false;
                for(int c = 0; c < M; c++) {
                    if(board[r][c] != left) {
                        flag = true;
                        break;
                    }
                }
                
                if(flag && added[left] == false) {
                    tmp.add(left);
                    added[left] = true;
                }
            }
        }
        else {
            for(int c = 0; c < M; c++) {
                int top = board[0][c];
                int bot = board[N - 1][c];
                
                if(top == 0 || top != bot) {
                    continue;
                }
                
                boolean flag = false;
                for(int r = 0; r < N; r++) {
                    if(board[r][c] != top) {
                        flag = true;
                        break;
                    }
                }
                
                if(flag && added[top] == false) {
                    tmp.add(top);
                    added[top] = true;
                }
            }
        }
        
        return tmp;
    }
}