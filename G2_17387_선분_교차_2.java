import java.io.*;
import java.util.*;

public class Main {
    
    static long T, N, M;
    static long[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        arr = new long[2][4];
        
        for(int i=0; i<2; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        if(solve() == true) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
        
    }
    public static int ccw(long ay, long ax, long by, long bx, long cy, long cx) {
        long ret = (ay * bx + by * cx + cy * ax) - (ay * cx + cy * bx + by * ax);
        
        if(ret < 0) return -1;
        if(ret > 0) return 1;
        return 0;
    }
    
    public static boolean solve() {
        long tmp1 = ccw(arr[0][0], arr[0][1], arr[0][2], arr[0][3], arr[1][0], arr[1][1]) * ccw(arr[0][0], arr[0][1], arr[0][2], arr[0][3], arr[1][2], arr[1][3]);
        long tmp2 = ccw(arr[1][0], arr[1][1], arr[1][2], arr[1][3], arr[0][0], arr[0][1]) * ccw(arr[1][0], arr[1][1], arr[1][2], arr[1][3], arr[0][2], arr[0][3]);
        
        if(tmp1 == 0 && tmp2 == 0) {
            long minX1 = Math.min(arr[0][0], arr[0][2]), maxX1 = Math.max(arr[0][0], arr[0][2]);
            long minY1 = Math.min(arr[0][1], arr[0][3]), maxY1 = Math.max(arr[0][1], arr[0][3]);
            long minX2 = Math.min(arr[1][0], arr[1][2]), maxX2 = Math.max(arr[1][0], arr[1][2]);
            long minY2 = Math.min(arr[1][1], arr[1][3]), maxY2 = Math.max(arr[1][1], arr[1][3]);

            return minX1 <= maxX2 && minX2 <= maxX1 && minY1 <= maxY2 && minY2 <= maxY1;
        }
        
        return tmp1 <= 0 && tmp2 <= 0;
    }
}