import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> list = new ArrayList<>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            if(tmp == -1) {
                continue;
            }
            list.get(tmp).add(i);
        }

        int ans = solve(0);
        System.out.println(ans);
    }

    public static int solve(int n) {
        int[] ret = list.get(n).stream().map(Main::solve).mapToInt(Integer::intValue).sorted().toArray();
        for(int i = ret.length; i > 0; i--) {
            ret[ret.length - i] += i;
        }
        return Arrays.stream(ret).max().orElse(0);
    }
}