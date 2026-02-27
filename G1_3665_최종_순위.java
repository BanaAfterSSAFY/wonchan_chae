import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, T;
    static int[] arr;
    static int[] rank;
    static int[][] degree;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            
            arr = new int[N+1];
            rank = new int[N+1];
            degree = new int[N+1][2];
            
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                rank[arr[i]] = i;
            }
            
            M = Integer.parseInt(br.readLine());
            
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if(rank[a] < rank[b]) {
                    degree[a][0]++;
                    degree[b][1]++;
                }
                else {
                    degree[b][0]++;
                    degree[a][1]++;
                }
            }
            
            int[] test = new int[N+1];
            boolean flag = true;
            
            for(int i=1; i<=N; i++) {
                if(degree[arr[i]][0] == 0 && degree[arr[i]][1] == 0) {
                    test[i] = arr[i];
                }
                else {
                    int idx = i + degree[arr[i]][0] - degree[arr[i]][1];
                    if(idx < 1 || idx > N || test[idx] != 0) {
                        flag = false;
                        break;
                    }
                    test[idx] = arr[i];
                }
            }
            
            if(flag == true) {
                for(int i=1; i<=N; i++) {
                    System.out.print(test[i] + " ");
                }
                System.out.println();
            }
            else {
                System.out.println("IMPOSSIBLE");
            }
            
        }
        
    }
}