import java.util.*;

class Solution {

    long answer = 0;
    long[] tmp; 
    int[] check;
    List<Integer>[] list;
    
    public long solution(int[] a, int[][] edges) {
        
        int l = a.length;
        
        tmp = new long[l];
        check = new int[l];

        long sum = 0;
        list = new ArrayList[l];
        
        for(int i = 0; i < l; i++) {
			sum += a[i];
            tmp[i] = a[i];
            list[i] = new ArrayList<Integer>();
	    }

        if(sum != 0) {
            return -1;
        }

        for(int i = 0; i < edges.length; i++) {
	    	list[edges[i][0]].add(edges[i][1]);
	    	list[edges[i][1]].add(edges[i][0]);
		}
        
        solve(0);
        
        return answer;
    }
    
    long solve(int node) {
        check[node] = 1;

        for(int i = 0; i < list[node].size(); i++) {
            int next = list[node].get(i);
            if(check[next] == 1) {
                continue;
            }
            tmp[node] += solve(next);
        }
        long num = tmp[node];
        answer += Math.abs(num);
        return num;
    }
}