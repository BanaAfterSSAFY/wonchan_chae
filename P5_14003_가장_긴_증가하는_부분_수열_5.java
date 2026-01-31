import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();

        int arr[] = new int[N + 1];
        int idx[] = new int[N + 1];

        for(int i=1 ; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list.add(Integer.MIN_VALUE);

        for(int i=1 ; i<=N; i++) {
            int num = arr[i];
            int s = 1;
            int e = list.size() - 1;

            if(num > list.get(list.size() - 1)) {
                list.add(num);
                idx[i] = list.size() - 1;
            }
            else {
                while(s < e) {
                    int mid = (s + e) / 2;

                    if(list.get(mid) >= num) {
                        e = mid;
                    }
                    else {
                        s = mid + 1;
                    }
                }
                list.set(e, num);
                idx[i] = e;
            }
        }

        sb.append(list.size() - 1 + "\n");

        Stack<Integer> ST = new Stack();

        int cur = list.size() - 1;

        for(int i=N; i>0; i--) {
            if(idx[i] == cur) {
                cur--;
                ST.push(arr[i]);
            }
        }

        while (!ST.isEmpty()){
            sb.append(ST.pop() + " ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}