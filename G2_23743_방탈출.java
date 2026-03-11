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
    
    static int T, N, M, A, B;
    static List<Node> arr;
    static int[] par;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new ArrayList<>();
        par = new int[N+1];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a =Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());
            int c =Integer.parseInt(st.nextToken());
            
            arr.add(new Node(a, b, c));
        }
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=1; i<=N; i++) {
            arr.add(new Node(0, i, Integer.parseInt(st.nextToken())));
        }
        
        int sum = 0;
        Collections.sort(arr, (o1, o2) -> {
            return o1.d - o2.d;
        });
        
        for(int i=1; i<=N; i++) {
            par[i] = i;
        }
        
        for(Node at : arr) {
            if(find(at.s) == find(at.e)) {
                continue;
            }
            
            merge(at.s, at.e);
            sum += at.d;
        }
        
        System.out.println(sum);
    }
    
    public static int find(int a) {
        if(par[a] == a) return a;
        return par[a] = find(par[a]);
    }
    
    public static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a != b) {
            par[b] = a;
        }
    }
}