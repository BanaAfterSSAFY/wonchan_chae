import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int end;
		int val;
		
		Node(int e, int v){
			end = e;
			val = v;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.val != o.val)	
				return this.val - o.val;
			return this.end - o.end;
		}
	}
	
	static int N, M;
	static List<Node>[] arr;
	static int base, Ans;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a].add(new Node(b, c));
			arr[b].add(new Node(a, c));
		}
		
		solve(-1, -1);
		
		if(Ans == 1234567890) {
			System.out.println(-1);
		}
		else if(Ans > 0) {
			System.out.println(Ans);
		}
		else {
			System.out.println(0);
		}
	}
	
	public static void solve(int a, int b) {

        int[] dist = new int[N+1];
		int[] trace = new int[N+1];

		for(int i=2; i<N+1; i++) {
			dist[i] = 1234567890;
		}
		dist[1] = 0;
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(1, 0));
		
		while(q.isEmpty() == false) {
			Node cur = q.poll();
			
			if(cur.val > dist[cur.end]) {
				continue;
			}
			
			for(Node at : arr[cur.end]) {
				
				if((cur.end == a && at.end == b) || (cur.end == b && at.end == a)) {
					continue;
				}

                if(dist[at.end] > cur.val + at.val) {
					dist[at.end] = cur.val + at.val;
					trace[at.end] = cur.end;
					q.offer(new Node(at.end, cur.val + at.val));
				}
				
			}
		}
		
		if(a == -1 && b == -1) {
			base = dist[N];
			int test = N;
			while(trace[test] != 0) {
				solve(test, trace[test]);			
				test = trace[test];
			}
			return;
		}
		
		if(dist[N] == 1234567890) {
			Ans = dist[N];
		}
		else {
			Ans = Math.max(Ans, dist[N] - base);
		}
		return;
	}
}