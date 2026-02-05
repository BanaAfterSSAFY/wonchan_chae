import java.io.*;
import java.util.*;


public class Main {
    
    static class Node {
        int time;
        int cur;
        
        public Node(int t, int c) {
            time = t;
            cur = c;
        }
    }
    
    static int[] time;
    static List<Integer>[] job;
    static boolean[] check;
    static Queue<Node> q = new LinkedList<>();
    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        job = new ArrayList[N + 1];
        check = new boolean[N + 1];
        answer = new int[N + 1];
        
        for(int i=1; i<=N; i++) {
            job[i] = new ArrayList<>();
            answer[i] = Integer.MAX_VALUE;
        }
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            
            time[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            if(m == 0) {
                q.offer(new Node(0, i));
            }
            
            for(int j=0; j<m; j++) {
                int pre = Integer.parseInt(st.nextToken());
                job[i].add(pre);
            }
        }
        
        int Ans = 0;
        
        while(q.isEmpty() == false) {
            Node n = q.poll();
            
            if(check[n.cur] == true) {
                continue;
            }
            
            answer[n.cur] = Math.min(answer[n.cur], n.time + time[n.cur]);
            
            check[n.cur] = true;
            
            for(int i=1; i<=N; i++) {
                if(check[i] == true) {
                    continue;
                }
                boolean flag = true;
                int t = 0;
                for(int at : job[i]) {
                    if(check[at] == false) {
                        flag = false;
                        break;
                    }
                    t = Math.max(t, answer[at]);
                }
                
                if(flag == true) {
                    q.offer(new Node(t, i));
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            Ans = Math.max(Ans, answer[i]);
        }
        
        System.out.println(Ans);
        
    }

}