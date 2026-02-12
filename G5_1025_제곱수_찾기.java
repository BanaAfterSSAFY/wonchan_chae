import java.io.*;
import java.util.*;

public class Main {
    
    static int N, T, M;
    static int[][] arr;
    static int Ans = -1;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());		
        
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
            String inp = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = inp.charAt(j) - '0';
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int p=-N+1; p<N; p++) {
                    for(int q=-M+1; q<M; q++) {
                        if(p == 0 && q == 0) {
                            solve(arr[i][j]);
                            continue;
                        }
                        
                        int tmp = 0;
                        int cnt = 0;
                        
                        while(true) {
                            if(i + p * cnt < 0 || i + p * cnt >= N || j + q * cnt < 0 || j + q * cnt >= M) {
                                break;
                            }
                            tmp = tmp * 10 + arr[i+p*cnt][j+q*cnt];
                            solve(tmp);
                            cnt++;
                        }
                    }
                }
            }
        }
        
        System.out.println(Ans);
    }
    
    public static void solve(int val) {
        if((int)Math.sqrt(val) * (int)Math.sqrt(val) == val) {
            Ans = Math.max(Ans, val);
        }
        return;
    }
}