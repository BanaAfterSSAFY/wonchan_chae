import java.util.*;
import java.io.*;

public class Main {
    
    static class Flower {
        int start, end;

        public Flower(int s, int e) {
            start = s;
            end = e;
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Flower[] arr = new Flower[N];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int start = a * 100 + b;
            int end = c * 100 + d;
            arr[i] = new Flower(start, end);
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1.start == o2.start) {
                return o2.end - o1.end;
            }
            return o1.start - o2.start;
        });

        int ss = 301, ee = 1201;
        int idx = 0, max = 0, answer = 0;
        while(ss < ee) {
            boolean flag = false;
            for(int i = idx; i < N; i++) {

                if(arr[i].start > ss) {
                    break;
                }

                if(arr[i].end > max) {
                    max = arr[i].end;
                    flag = true;
                    idx = i + 1;
                }
            }

            if(flag == true) {
                answer++; 
                ss = max;
            }
            else {
                break;
            }
        }
        if(max < ee) {
            System.out.println(0);
        }
        else {
            System.out.println(answer);
        }
    }
}