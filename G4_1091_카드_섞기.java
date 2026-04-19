import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] P, S, arr, tmp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        P = new int[N];
        S = new int[N];
        arr = new int[N];
        tmp = new int[N];
        
        int cnt = 0;
        
        for(int i = 0; i < N; i++) {
            arr[i] = i % 3;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        
        while(true) {
            boolean flag = true; 
            boolean out = true;

            for(int i = 0; i < N; i++) {
                if(arr[i] != P[i]) {
                    flag = false;
                    break;
                }
            }

            if(flag == false) {
                for(int i = 0; i < N; i++) {
                    tmp[i] = arr[S[i]];
                }

                arr = tmp.clone();
                cnt++;
            }
            else {
                System.out.println(cnt);
                break;
            }
            
            for(int i = 0; i < N; i++) {
                if(arr[i] != i % 3) {
                    out = false;
                }
            }
            if(out == true) {
                System.out.println(-1);
                break;
            }
        }
        
    }
}