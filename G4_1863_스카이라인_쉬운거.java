import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        Stack<Node> stack = new Stack<>();
        int ans = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(stack.isEmpty() == false && stack.peek().y > y) {
                Node cur = stack.pop();
                if(stack.isEmpty() == false && stack.peek().y == cur.y) {
                    continue;
                }
                if(cur.y == 0) {
                    continue;
                }
                ans++;
            }

            stack.push(new Node(x, y));
        }

        while(stack.isEmpty() == false) {
            Node cur = stack.pop();
            if(stack.isEmpty() == false && stack.peek().y == cur.y) {
                continue;
            }

            if(cur.y == 0) {
                continue;
            }

            ans++;
        }

        System.out.println(ans);
    }
}