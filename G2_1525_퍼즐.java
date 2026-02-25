import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int idx;
        int cnt;
        int map;
        
        Node(int i, int c, int m) {
            idx = i;
            cnt = c;
            map = m;
        }
    }
    
    static int R, C;
    static int[][] arr;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int ans = Integer.MAX_VALUE;
    static Set<Integer> set = new HashSet<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        arr = new int[3][3];
        int tmp = 0;
        for(int i=0; i<3 ;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0) {
                    R = i;
                    C = j;
                }
                tmp *= 10;
                tmp += arr[i][j];
            }
        }
        
        move(tmp);
        
        if(ans != Integer.MAX_VALUE) {
            System.out.println(ans);
        }
        else {
            System.out.println(-1);
        }
    }
    
    
    public static void move(int tmp) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R * 3 + C, 0, tmp));
        set.add(tmp);
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            
            int r = cur.idx / 3;
            int c = cur.idx % 3;

            if(cur.map == 123456780) {
                ans = Math.min(ans, cur.cnt);
                break;
            }
                        
            for(int i=0; i<4; i++) {
                int rr = r + dir[i][0];
                int cc = c + dir[i][1];
                
                if(rr < 0 || rr >= 3 || cc < 0 || cc >= 3) {
                    continue;
                }
                
                int res = 0;
                
                String test = "" + cur.map;
                if(test.length() == 8)
                    test = "0" + test;
                
                for(int j=0; j<9; j++) {
                    res *= 10;
                    if(j == cur.idx) {
                        res += test.charAt(rr * 3 + cc) - '0';
                    }
                    else if(j == rr * 3 + cc) {
                        res += 0;
                    }
                    else {
                        res += test.charAt(j) - '0';
                    }
                }
                
                if(set.contains(res) == true) {
                    continue;
                }
                else {
                    set.add(res);
                }
                
                q.offer(new Node(rr * 3 + cc, cur.cnt + 1, res));
                
            }
            
        }
        
    }
}