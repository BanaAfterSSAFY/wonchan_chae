import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[][] arr;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inp;
        inp = br.readLine().split(" ");

        N = Integer.parseInt(inp[0]);
        M = Integer.parseInt(inp[1]);
        R = Integer.parseInt(inp[2]);

        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            inp = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(inp[j]);
            }
        }
        
        inp = br.readLine().split(" ");
        for(int i=0; i<R; i++) {
            int C = Integer.parseInt(inp[i]);

            if(C == 1)
                solve1();
            else if(C == 2)
                solve2();
            else if(C == 3)
                solve3();
            else if(C == 4)
                solve4();
            else if(C == 5)
                solve5();
            else if(C == 6)
                solve6();
        }
        
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void solve1() {
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[N-1-i][j];
                arr[N-1-i][j] = tmp;
            }
        }
    }
    
    public static void solve2() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M/2; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[i][M-1-j];
                arr[i][M-1-j] = tmp;
            }
        }
    }
    
    public static void solve3() {
        int[][] tmp = new int[M][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[j][N-1-i] = arr[i][j];
            }
        }
        arr = tmp;
        
        int t = M;
        M = N;
        N = t;
    }
    
    public static void solve4() {
        int[][] tmp = new int[M][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[M-1-j][i] = arr[i][j];
            }
        }
        arr = tmp;
        
        int t = M;
        M = N;
        N = t;
    }
    
    public static void solve5() {
        int[][] tmp = new int[N/2][M/2];
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i][j] = arr[i+N/2][j];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i+N/2][j] = arr[i+N/2][j+M/2];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i+N/2][j+M/2] = arr[i][j+M/2];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i][j+M/2] = tmp[i][j];
            }
        }
    }
    
    public static void solve6() {
        int[][] tmp = new int[N/2][M/2];
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i][j] = arr[i][j+M/2];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i][j+M/2] = arr[i+N/2][j+M/2];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i+N/2][j+M/2] = arr[i+N/2][j];
            }
        }
        
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<M/2; j++) {
                arr[i+N/2][j] = tmp[i][j];
            }
        }
    }
}