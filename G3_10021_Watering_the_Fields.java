import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int s;
        int e;
        int d;
        
        Node(int s, int e, int d){
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }
    
    static int T, N, C;
    static int[][] arr;
    static int[] par;
    static List<Node> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new int[N][2];
        par = new int[N];
        list = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            par[i] = i;
        }
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                
                int d = (int)Math.pow(arr[i][0] - arr[j][0], 2) + (int)Math.pow(arr[i][1] - arr[j][1], 2);
                
                if(d < C) {
                    continue;
                }
                
                list.add(new Node(i, j, d));
            }
        }
        
        Collections.sort(list, (o1, o2) -> {
            return o1.d - o2.d;
        });
        
        int cnt = 0;
        long ans = 0;
        for(Node at : list) {
            if(find(at.s) == find(at.e)) {
                continue;
            }
            
            merge(at.s, at.e);
            ans += at.d;
            cnt++;
        }
        
        if(cnt != N-1) {
            System.out.println(-1);
        }
        else {
            System.out.println(ans);
        }
    }
    
    public static int find(int a) {
        if(par[a] == a) return a;
        return par[a] = find(par[a]);
    }
    
    public static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        par[b] = a;
    }
}