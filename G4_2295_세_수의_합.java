import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                list.add(arr[i] + arr[j]);
            }
        }

        Arrays.sort(arr);
        Collections.sort(list);

        for(int i = N-1; i >= 0; i--) {
            for(int j = N-1; j >= 0; j--) {
                int ltmp = arr[i] - arr[j];

                if(Collections.binarySearch(list, ltmp) >= 0) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}