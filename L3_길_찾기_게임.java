import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int v;
        int x;
        int y;
        Node left;
        Node right;

        Node(int v, int x, int y) { 
            this.v = v;
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }
    }

    public static ArrayList<Node> list = new ArrayList<>();
    public static int idx = 0;
    
    public int[][] solution(int[][] nodeinfo) {

        for(int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Collections.sort(list);
        
        Node root = list.get(0);
        
        for(int i = 1; i < list.size(); i++) {
            Node next = list.get(i);
            solve(root, next);
        }
        
        int[][] answer = new int[2][list.size()];
    
        pre(answer, root);
        idx = 0;

        post(answer, root);

        return answer;
    }
    
    public static void solve(Node prev, Node next) {
        if(next.x < prev.x) {
            if(prev.left == null) {
                prev.left = next;
            }
            else {
                solve(prev.left, next);
            }
        }
        else {
            if(prev.right == null) {
                prev.right = next;
            }
            else {
                solve(prev.right, next);
            }
        }
    }
    
    public static void pre(int[][] arr, Node node) {
        if(node != null) {
            arr[0][idx++] = node.v;
            
            if(node.left != null) {
                pre(arr, node.left);
            }

            if(node.right != null) {
                pre(arr, node.right);
            }
        }
    }

    public static void post(int[][] arr, Node node) {
        if(node != null) {
            
            if(node.left != null) {
                post(arr, node.left);
            }

            if(node.right != null) {
                post(arr, node.right);
            }
            
            arr[1][idx++] = node.v;
        }
    }
}