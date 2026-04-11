import java.io.*;
import java.util.*;

public class Main {

    static long N;
    static int[] arr = {1, 0, 1, 1, 1, 1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = (Long.parseLong(br.readLine()) - 1) % 7;
        
        System.out.println(arr[(int) N] == 1 ? "SK" : "CY");
    }
}