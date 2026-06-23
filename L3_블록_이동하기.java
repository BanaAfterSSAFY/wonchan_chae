import java.util.*;

class Solution {
    static class Node {
        Point a; 
        Point b;
        int t;
        int v;

        Node(Point a, Point b, int t, int v) {
            this.a = a;
            this.b = b;
            this.t = t;
            this.v = v;
        }
    }
    
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] board) {
        int answer = 0;
        Queue<Node> q = new LinkedList<>();
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        int l = board.length;
        boolean[][][] check = new boolean[l][l][2];
        
        q.add(new Node(new Point(0, 0), new Point(0, 1), 0, 0));
        
        while(q.isEmpty() == false) {
            Node node = q.poll();
            
            if(node.a.r < 0 || node.a.c < 0 || node.a.r >= l || node.a.c >= l || node.b.r < 0 || node.b.c < 0 || node.b.r >= l || node.b.c >= l) {
                continue;
            }
        
            if(board[node.a.r][node.a.c] == 1 || board[node.b.r][node.b.c] == 1) {
                continue;
            }
            
            if(check[node.a.r][node.a.c][node.v] == true && check[node.b.r][node.b.c][node.v] == true) {
                continue;
            }
            
            if((node.a.r == l - 1 && node.a.c == l - 1) || (node.b.r == l - 1 && node.b.c == l - 1)) {
                answer = node.t;
                break;
            }
            
            check[node.a.r][node.a.c][node.v] = true;
            check[node.b.r][node.b.c][node.v] = true;

            for(int i = 0; i < 4; i++) {
                int nr1 = node.a.r + dir[i][0];
                int nr2 = node.b.r + dir[i][0];
                int nc1 = node.a.c + dir[i][1];
                int nc2 = node.b.c + dir[i][1];
        
                q.add(new Node(new Point(nr1, nc1), new Point(nr2, nc2), node.t + 1, node.v));
            }
            
            if(node.v == 1) {

                if(node.a.c - 1 >= 0 && board[node.a.r][node.a.c - 1] == 0 && board[node.b.r][node.b.c - 1] == 0) {
                    q.add(new Node(new Point(node.a.r, node.a.c), new Point(node.a.r, node.b.c - 1), node.t + 1, 0));
                    q.add(new Node(new Point(node.b.r, node.a.c - 1), new Point(node.b.r, node.b.c), node.t + 1, 0));
                }

                if(node.a.c + 1 < l && board[node.a.r][node.a.c + 1] == 0 && board[node.b.r][node.b.c + 1] == 0) {
                    q.add(new Node(new Point(node.a.r, node.a.c), new Point(node.a.r, node.b.c + 1), node.t + 1, 0));
                    q.add(new Node(new Point(node.b.r, node.a.c + 1), new Point(node.b.r, node.b.c), node.t + 1, 0));
                }
            }
            else {

                if(node.a.r - 1 >= 0 && board[node.a.r - 1][node.a.c] == 0 && board[node.b.r - 1][node.b.c] == 0) {
                    q.add(new Node(new Point(node.a.r - 1, node.b.c), new Point(node.b.r, node.b.c), node.t + 1, 1));
                    q.add(new Node(new Point(node.a.r, node.a.c), new Point(node.b.r - 1, node.a.c), node.t + 1, 1));
                    
                }

                if(node.a.r + 1 < l && board[node.a.r + 1][node.a.c] == 0 && board[node.b.r + 1][node.b.c] == 0) {
                    q.add(new Node(new Point(node.a.r + 1, node.b.c), new Point(node.b.r, node.b.c), node.t + 1, 1));   
                    q.add(new Node(new Point(node.a.r, node.a.c), new Point(node.b.r + 1, node.a.c), node.t + 1, 1));   
                }
            }
            
        }
        return answer;
    }
}