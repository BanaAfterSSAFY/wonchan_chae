import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int end;
	int time;
	
	public Node(int e, int t){
		end = e;
		time = t;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.time - o.time;
	}
}

public class Main {
	
	static List<Node>[] list;
	static int N, D, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		String[] inp;
		
		for(int t=0; t<T; t++) {
			inp = br.readLine().split(" ");
			
			N = Integer.parseInt(inp[0]);
			D = Integer.parseInt(inp[1]);
			C = Integer.parseInt(inp[2]);
			
			list = new ArrayList[N+1];
	
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<D; i++) {
				inp = br.readLine().split(" ");
				int a = Integer.parseInt(inp[0]);
				int b = Integer.parseInt(inp[1]);
				int s = Integer.parseInt(inp[2]);
				list[b].add(new Node(a, s));
			}
			
			solve();
		}
	}
	
	public static void solve() {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.offer(new Node(C, 0));
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, 1000000000);
		dist[C] = 0;
		
		while(PQ.isEmpty() == false) {
			Node tmp = PQ.poll();
			
			if(dist[tmp.end] < tmp.time) {
				continue;
			}
			
			for(Node n : list[tmp.end]) {
				
				if(dist[n.end] > dist[tmp.end] + n.time) {
					dist[n.end] = dist[tmp.end] + n.time;
					PQ.offer(new Node(n.end, dist[tmp.end] + n.time));
				}
			}
		}
		
		int cnt = 0;
		int ans = 0;
		
		for(int i=1; i<=N; i++) {
			if(dist[i] == 1000000000) {
				continue;
			}
			cnt++;
			ans = Math.max(ans, dist[i]);
		}
		
		System.out.println(cnt + " " + ans);
	}
}