import java.io.*;
import java.util.*;

public class Main {
    
    static class Union {
        int arr[] = new int[200001];
        
        Union(){
            Arrays.fill(arr, -1);
        }
        
        int find(int idx) {
            if(arr[idx] < 0) {
                return idx;
            }
            return arr[idx] = find(arr[idx]);
        }
        
        boolean merge(int a, int b) {
            a = find(a);
            b = find(b);
            
            if(a == b) {
                return false;
            }
            arr[b] = a;
            return true;
        }
    }
    
    static int N, Q;
    static int[] arr;
    static int[][] quest;
    static List<Integer>[] par;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        Union u = new Union();
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        Q += N - 1;
        
        arr = new int[N + 1];
        quest = new int[Q + 1][3];
        
        for(int i=1; i<N; i++) {
            int inp = Integer.parseInt(br.readLine());
            arr[i] = inp - 1;
        }
        
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            quest[i][0] = Integer.parseInt(st.nextToken());
            quest[i][1] = Integer.parseInt(st.nextToken());
            quest[i][1]--;
            
            if(quest[i][0] == 1) {
                quest[i][2] = Integer.parseInt(st.nextToken());
                quest[i][2]--;
            }
        }
        
        for(int i=Q-1; i>=0; i--) {
            if(quest[i][0] == 0) {
                u.merge(quest[i][1], arr[quest[i][1]]);
            }
            else {
                if(u.find(quest[i][1]) == u.find(quest[i][2])) {
                    sb.append("\nSEY");
                }
                else {
                    sb.append("\nON");
                }
                
            }
        }
        
        sb.reverse();
        System.out.print(sb);
        
    }
}