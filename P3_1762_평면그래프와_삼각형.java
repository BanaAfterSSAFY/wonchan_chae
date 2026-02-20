import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[][] edge;
    static int[] degree;
    static Set<Integer>[] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        edge = new int[M][2];
        degree = new int[N+1];
        
        arr = new HashSet[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = new HashSet<>();
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            edge[i][0] = a;
            edge[i][1] = b;
            degree[a]++;
            degree[b]++;
        }
        
        for(int i=0; i<M; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            if(degree[a] < degree[b] || (degree[a] == degree[b] && a < b)) {
                arr[a].add(b);
            }
            else {
                arr[b].add(a);
            }
        }
        
        int Ans = 0;

        
        for(int i=1; i<=N; i++) {
            if(arr[i].size() == 0) {
                continue;
            }
            
            Integer[] tmp = arr[i].toArray(new Integer[0]);
            
            for(int j=0; j<tmp.length; j++) {
                
                int a = tmp[j];
                
                for(int k=j+1; k<tmp.length; k++) {
                    
                    int b = tmp[k];
                    
                    if(degree[a] < degree[b] || (degree[a] == degree[b] && a < b)) {
                        if(arr[a].contains(b)) {
                            Ans++;
                        }
                    }
                    else {
                        if(arr[b].contains(a)) {
                            Ans++;
                        }
                    }
                }
            }
        }
        
        System.out.println(Ans);
    }
}