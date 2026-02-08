import java.io.*;
import java.util.*;
 
class Node implements Comparable<Node> {
    int num;
    int idx;
 
    Node(int n, int i) {
        num = n;
        idx = i;
    }
 
    @Override
    public int compareTo(Node o) {
        return num - o.num;
    }
 
}
 
public class Main {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
 
        Node[] arr = new Node[N + 1];
        for (int i=1; i<=N; i++) {
            int inp = Integer.parseInt(br.readLine());
            arr[i] = new Node(inp, i);
        }
        Arrays.sort(arr, 1, N + 1);
 
        int max = 0;
        for(int i=1; i<=N; i++) {
            max = Math.max(max, arr[i].idx - i);
        }
 
        bw.write((max + 1) + "\n");
        bw.close();
        br.close();
    }
}