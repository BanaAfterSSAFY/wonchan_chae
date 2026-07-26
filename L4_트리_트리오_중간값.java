import java.util.*;

class Solution {

    static class Node {
        List<Integer> list;
        int d;

        Node(List<Integer> list, int d) {
            this.list = list;
            this.d = d;
        }
    }

    static List<List<Integer>> map = new ArrayList<>();

    public Node bfs(int leaf, int n) {
        boolean[] check = new boolean[n + 1];
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        q1.add(leaf);
        check[leaf] = true;
        int d = 0;
        List<Integer> list = new ArrayList<>();
        
        while(true) {
            while(q1.isEmpty() == false) {
                int cur = q1.poll();
                for(int next : map.get(cur)) {
                    if(check[next]) {
                        continue;
                    }

                    check[next] = true;
                    q2.add(next);
                }
            }

            if(q2.isEmpty() == true) {
                break;
            }

            d++;
            list = new ArrayList<>(q2);

            while(q2.isEmpty() == false) {
                q1.add(q2.poll());
            }
        }
        return new Node(list, d);
    }

    public int solution(int n, int[][] edges) {
        for(int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            map.get(from).add(to);
            map.get(to).add(from);
        }

        int cand = 0;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] check = new boolean[n+1];

        q.add(1);
        check[1] = true;

        while(q.isEmpty() == false) {
            int cur = q.poll();
            if(map.get(cur).size() == 1) {
                cand = cur;
            }

            for(int next : map.get(cur)) {
                if(check[next]) {
                    continue;
                }

                check[next] = true;
                q.add(next);
            }
        }

        Node res1 = bfs(cand, n);
        if(res1.list.size() > 1) {
            return res1.d;
        }

        Node res2 = bfs(res1.list.get(0), n);
        if(res2.list.size() > 1) {
            return res2.d;
        }
        else {
            return res2.d-1;
        }
    }
}