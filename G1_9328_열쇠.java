import java.io.*;
import java.util.*;

class Node {
    int r;
    int c;
    int k;
    
    public Node(int r, int c, int k) {
        this.r = r;
        this.c = c;
        this.k = k;
    }
}

public class Main {
    
    static int N;
    static char[][] arr;
    static boolean[][] check;
    static int Ans;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            
            arr = new char[H][W];
            check = new boolean[H][W];
            Ans = 0;
            
            for(int i=0; i<H; i++) {
                String inp = br.readLine();
                for(int j=0; j<W; j++) {
                    arr[i][j] = inp.charAt(j);
                }
            }
            
            String key = br.readLine();
            int keys = 0;
            if(key.charAt(0) != '0') {
                for(int i=0; i<key.length(); i++) {
                    keys |= (1 << (key.charAt(i) - 'a'));
                }
            }
            
            Queue<Node> q = new LinkedList<>();
            Queue<Node> wq = new LinkedList<>();
            
            int[] way = {0, W-1};
            int[] hay = {0, H-1};
            
            for(int i=0; i<H; i++) {
                for(int j : way) {
                    if(arr[i][j] != '*') {
                        if(arr[i][j] == '$') {
                            Ans++;
                            q.offer(new Node(i, j, 0));
                        }
                        else if(arr[i][j] - 'a' >= 0 && arr[i][j] - 'a' < 26) {
                            keys |= (1 << (arr[i][j] - 'a'));
                            q.offer(new Node(i, j, 0));
                            
                            int num = wq.size();
                            while(num-- != 0) {
                                Node tmp = wq.poll();
                                if((keys & tmp.k) == tmp.k) {
                                    q.offer(tmp);
                                    check[tmp.r][tmp.c] = true;
                                }
                                else {
                                    wq.offer(tmp);
                                }
                            }
                        }
                        else if(arr[i][j] - 'A' >= 0 && arr[i][j] - 'A' < 26) {
                            int temp = 1 << (arr[i][j] - 'A');
                            if((keys & temp) == temp) {
                                q.offer(new Node(i, j, 0));
                            }
                            else {
                                wq.offer(new Node(i, j, temp));
                                continue;
                            }
                        }
                        else{
                            q.offer(new Node(i, j, 0));
                        }
                        check[i][j] = true;
                    }
                }
            }
            
            for(int i=1; i<W; i++) {
                for(int j : hay) {
                    if(arr[j][i] != '*' && check[j][i] == false) {
                        if(arr[j][i] == '$') {
                            Ans++;
                            q.offer(new Node(j ,i, 0));
                        }
                        else if(arr[j][i] - 'a' >= 0 && arr[j][i] - 'a' < 26) {
                            keys |= (1 << (arr[j][i] - 'a'));
                            q.offer(new Node(j, i, 0));
                            
                            int num = wq.size();
                            while(num-- != 0) {
                                Node tmp = wq.poll();
                                if((keys & tmp.k) == tmp.k) {
                                    q.offer(tmp);
                                    check[tmp.r][tmp.c] = true;
                                }
                                else {
                                    wq.offer(tmp);
                                }
                            }
                        }
                        else if(arr[j][i] - 'A' >= 0 && arr[j][i] - 'A' < 26) {
                            int temp = 1 << (arr[j][i] - 'A');
                            if((keys & temp) == temp) {
                                q.offer(new Node(j, i, 0));
                            }
                            else {
                                wq.offer(new Node(j, i, temp));
                                continue;
                            }
                        }
                        else{
                            q.offer(new Node(j, i, 0));
                        }
                        check[j][i] = true;
                    }
                }
            }
            
            while(q.isEmpty() == false) {
                Node cur = q.poll();
                
                for(int d=0; d<4; d++) {
                    int nr = cur.r + dir[d][0];
                    int nc = cur.c + dir[d][1];
                    
                    if(nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        continue;
                    }
                    
                    if(arr[nr][nc] == '*' || check[nr][nc] == true) {
                        continue;
                    }
                    
                    if(arr[nr][nc] == '$') {
                        Ans++;
                        q.offer(new Node(nr, nc, 0));
                        check[nr][nc] = true;
                    }
                    else if(arr[nr][nc] == '.') {
                        q.offer(new Node(nr, nc, 0));
                        check[nr][nc] = true;
                    }
                    else if(arr[nr][nc] - 'a' >= 0 && arr[nr][nc] - 'a' < 26) {
                        keys |= (1 << (arr[nr][nc] - 'a'));
                        q.offer(new Node(nr, nc, 0));
                        check[nr][nc] = true;
                        
                        int num = wq.size();
                        while(num-- != 0) {
                            Node tmp = wq.poll();
                            if((keys & tmp.k) == tmp.k) {
                                q.offer(tmp);
                                check[tmp.r][tmp.c] = true;
                            }
                            else {
                                wq.offer(tmp);
                            }
                        }
                        
                    }
                    else if(arr[nr][nc] - 'A' >= 0 && arr[nr][nc] - 'A' < 26) {
                        int temp = 1 << (arr[nr][nc] - 'A');
                        if((keys & temp) == temp) {
                            q.offer(new Node(nr, nc, 0));
                            check[nr][nc] = true;
                        }
                        else {
                            wq.offer(new Node(nr, nc, temp));
                        }
                    }
                }
            }
            System.out.println(Ans);
        }
    }
}