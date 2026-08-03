import java.util.*;

class Solution {

    static int[][] map;
    static List<Node>[] lList, rList;
    static Map<Integer, Integer> tList;

    static class Node implements Comparable<Node> {
        int s;
        int w;
        int t;

        public Node(int s, int w, int t) {
            this.s = s;
            this.w = w;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        lList = new ArrayList[n+1];
        rList = new ArrayList[n+1];
        
        for(int i = 1; i < n + 1; i++) {
            lList[i] = new ArrayList<>();
            rList[i] = new ArrayList<>();
        }

        tList = new HashMap<>();
        for(int i = 0; i < traps.length; i++) {
            tList.put(traps[i], 1 << (i + 1));
        }

        for(int i = 0; i < roads.length; i++) {
            int f = roads[i][0];
            int s = roads[i][1];
            int w = roads[i][2];

            lList[f].add(new Node(s, w, 0));
            rList[s].add(new Node(f, w, 0));
        }

        map= new int[n + 1][1 << tList.size() + 1];
        for(int i = 0; i < n + 1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        solve(start, end);

        int answer = Integer.MAX_VALUE;
        for(int at : map[end]) {
            answer = Math.min(answer, at);
        }
        return answer;
    }
    
    static void solve(int start, int end) {
        Queue<Node> q = new PriorityQueue<>();
        map[start][0] = 0;
        q.add(new Node(start, 0, 0));

        while(q.isEmpty() == false) {
            Node node = q.poll();
            int s = node.s;
            int w = node.w;
            int t = node.t;

            if(s == end) {
                return;
            }

            int f1 = 0; 
            if(tList.containsKey(s)) {
                if((t & tList.get(s)) != 0) {
                    f1 = 1;
                }
            }

            int tmp = f1; 
            for(Node at : lList[s]) {
                tmp = f1;
                int cnt= t;
                if(tList.containsKey(at.s)) {
                    if((t & tList.get(at.s)) != 0){
                        tmp ^= 1;  
                    }
                    cnt ^= tList.get(at.s);
                }

                if(tmp == 0) {
                    if(map[at.s][t] > w + at.w) {
                        map[at.s][t] = w + at.w;
                        q.add(new Node(at.s, map[at.s][t], cnt));
                    }
                }
            }

            for(Node at : rList[s]) {
                tmp = f1;
                int cnt = t;
                if(tList.containsKey(at.s)) {
                    if((t & tList.get(at.s)) != 0) {
                        tmp ^= 1;  
                    }
                    cnt ^= tList.get(at.s);
                }
                if(tmp == 1) {
                    if(map[at.s][t] > w + at.w) {
                        map[at.s][t] = w + at.w;
                        q.add(new Node(at.s, map[at.s][t], cnt));
                    }
                }
            }
        }
    }
}