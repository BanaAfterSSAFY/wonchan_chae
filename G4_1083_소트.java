import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
            int maxi = 0;
            int idx = -1;

            for(int j = i + 1; j < N; j++) {
                if(list.get(i) < list.get(j) && M >= j - i) {
                    if(list.get(j) > maxi) {
                        maxi = list.get(j);
                        idx = j;
                    }
                }
            }

            if(idx != -1) {
                list.remove(idx);
                list.add(i, maxi);
                M -= idx - i;
            }
        }
        System.out.println(list.toString().replaceAll("\\[", "").replaceAll("\\]", "").replace(",", ""));
    }
}