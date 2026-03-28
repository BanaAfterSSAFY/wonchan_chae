import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        HashSet<Integer> set = new HashSet<>();
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        for(int i = 0; i < N; i++) {
            if(set.contains(arr[i]) == true) {

                for(int j = s; j < i; j++) {
                    ans += i - j;
                    s++;
                    if(arr[j] == arr[i]) {
                        break;
                    }
                    set.remove(arr[j]);
                }
            }
            else {
                set.add(arr[i]);
            }
        }

        for(int i = s; i < N; i++) {
            ans += N - i;
        }
        
        System.out.println(ans);
    }
}