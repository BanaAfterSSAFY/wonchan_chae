import java.io.*;
import java.util.*;

public class Main {
    
    
    static int N, Q;
    static List<Integer>[] tree;
    static int[] subSum;
    static TreeSet<Integer> set;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        
        subSum = new int[N+1];
        tree = new ArrayList[N+1];
        set = new TreeSet<>();
        
        for(int i=1; i<=N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            tree[a].add(b);
            tree[b].add(a);
        }
        
        
        
        for(int i=1; i<=N; i++){
            if(tree[i].size() != 1) {
                continue;
            }
            getSub(i, -1);
            set.add(getCent(i, -1, N));
        }
        
        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        
    }
    
    public static int getSub(int v, int b) {
        int ret = 1;
        for(int at : tree[v]) {
            if(at != b) {
                ret += getSub(at, v);
            }
        }
        return subSum[v] = ret;
    }
    
    public static int getCent(int v, int b, int cap) {
        
        for(int at : tree[v]) {
            if(at != b && subSum[at] * 2 > cap) {
                return getCent(at, v, cap);
            }
        }
        return v;
    }
}