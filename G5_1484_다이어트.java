import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        
        int idx = 0;
        for(int i = 1; i * i - idx * idx <= N; i++) {
            arr.add(idx = i);
        }
        
        Set<Integer> set = new HashSet<>();
        
        int cnt = 0;
        for(int at : arr) {
            set.add(at * at);
            if (set.contains(at * at - N) == false) {
                continue;
            }
            sb.append(at).append('\n');
            cnt++;
        }
        System.out.print(cnt == 0 ? -1 : sb);
    }
}