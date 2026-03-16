import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        long y;
        long x;
        double d;
        
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.d != o.d) {
                return Double.compare(this.d, o.d);
            }
            return Long.compare(getDist(start, this), getDist(start, o));
        }
    }
    
    static int N;
    static Node start;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        List<Node> list = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list.add(new Node(b, a));
        }
        
        start = Collections.min(list, (o1, o2) -> {
            if(o1.y != o2.y) {
                return Long.compare(o1.y, o2.y);
            }
            return Long.compare(o1.x, o2.x);
        });
        
        list.remove(start);
        
        for(Node at : list) {
            at.d = Math.atan2(at.y - start.y, at.x - start.x);
        }
        
        Collections.sort(list);
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        
        for(Node at : list) {
            while(stack.size() >= 2 && ccw(stack.get(stack.size() - 2), stack.peek(), at) <= 0) {
                stack.pop();
            }
            stack.push(at);
        }
        
        System.out.println(stack.size());
    }
    
    public static int ccw(Node a, Node b, Node c) {
        long res = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
        if (res > 0) return 1;
        if (res < 0) return -1;
        return 0;
    }
    
    public static long getDist(Node o1, Node o2) {
        return (o1.y - o2.y) * (o1.y - o2.y) + (o1.x - o2.x) * (o1.x - o2.x);
    }
}