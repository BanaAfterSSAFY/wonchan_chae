import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        long r;
        long c;
        
        public Node(long r, long c) {
            this.r = r;
            this.c = c;
        }
    }

    static Node[][] Node = new Node[2][2];
    
    static public void  main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            
            Node[i][0] = new Node(r1, c1);
            Node[i][1] = new Node(r2, c2);
        }

        if(ccw(Node[0][0], Node[0][1], Node[1][0]) * ccw(Node[0][0], Node[0][1], Node[1][1]) < 0 && ccw(Node[1][0], Node[1][1], Node[0][0]) * ccw(Node[1][0], Node[1][1], Node[0][1]) < 0) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }

    static int ccw(Node A, Node B, Node C ) {
        long sumA = A.r * B.c + B.r * C.c + C.r * A.c;
        long sumB = A.c * B.r + B.c * C.r + C.c * A.r;
        
        if(sumA - sumB < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }
}