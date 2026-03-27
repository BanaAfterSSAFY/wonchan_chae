import java.io.*;
import java.util.*;

public class Main {
    
    static long T, N, M;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    
        N = Long.parseLong(br.readLine());
        
        long tmp = 1;
        
        while(tmp < N) {
            tmp *= 2;
        }
                
        System.out.println(tmp - (tmp - N) / 2 - 1);
    }
    
}