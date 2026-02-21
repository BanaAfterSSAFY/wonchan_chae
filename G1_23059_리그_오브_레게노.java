import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        
        int[] degree = new int[2 * N + 2];
        
        List<Integer>[] list = new ArrayList[2 * N + 2];
        for(int i=1; i<2 * N + 2; i++){
            list[i] = new ArrayList<>();
        }

        Map<String, Integer> map = new HashMap<>();
        String[] name = new String[2 * N + 2];
        int idx = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> name[a].compareTo(name[b]));

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            String b = st.nextToken();

            if(map.containsKey(a) == false){
                map.put(a, idx);
                name[idx++] = a;
            }
            if(map.containsKey(b) == false){
                map.put(b, idx);
                name[idx++] = b;
            }

            int aa = map.get(a);
            int bb = map.get(b);

            list[aa].add(bb);
            degree[bb]++;
                        
        }

        for(int i=1; i<idx; i++){
            if(degree[i] == 0){
                pq.offer(i);
            }
        }

        int cnt = 0;       

        while(pq.isEmpty() == false){
            List<Integer> tmp = new ArrayList<>();
            
            while(pq.isEmpty() == false){
                int cur = pq.poll();
                sb.append(name[cur]).append("\n");
                cnt++;

                for(int at : list[cur]){
                    degree[at]--;
                    if(degree[at] == 0){
                        tmp.add(at);
                    }
                }
            }

            for(int at : tmp){
                pq.offer(at);
            }
        }

        if(cnt != idx - 1){
            System.out.println(-1);
        }
        else{
            System.out.println(sb);
        }
    }
}