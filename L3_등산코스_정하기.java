import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int s;
        int e;
        int w;

        Node(int a, int b, int c) {
            s = a;
            e = b;
            w = c;
        }

        public int compareTo(Node o) {
            if(this.w == o.w) {
                return this.e - o.e;
            }
            return this.w - o.w;
        }
    }

    List<Node>[] list;
    Set<Integer> start, end;
    int[] arr;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        list = new List[n + 1];
        arr = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList();
        }

        start = new HashSet();
        end = new HashSet();

        for(int at : gates) {
            start.add(at);
        }
        for(int at : summits) {
            end.add(at);
        }

        for(int[] at : paths) {
            if(start.contains(at[1]) == false && end.contains(at[0]) == false) {
                list[at[0]].add(new Node(at[0], at[1], at[2]));
            }

            if(start.contains(at[0]) == false && end.contains(at[1]) == false) {
                list[at[1]].add(new Node(at[1], at[0], at[2]));
            }
        }


        int[] answer = new int[2];
        solve(answer);

        return answer;
    }

    public void solve(int[] answer) {
        Arrays.fill(arr, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue();

        for(int at : start) {
            for(Node n : list[at]) {
                pq.add(n);
            }
        }

        answer[0] = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;

        while(pq.isEmpty() == false) {
            Node cur = pq.poll();
            
            if(cur.w > max) {
                break;
            }

            arr[cur.e] = cur.w;

            if(end.contains(cur.e) == true) {

                for(int at : arr) {
                    if(at > max && at != Integer.MAX_VALUE || max == Integer.MAX_VALUE) {
                        max = at;
                    }
                }

                answer[0] = answer[0] < cur.e ? answer[0] : cur.e;
                answer[1] = max;
            }

            for(int i = 0; i < list[cur.e].size(); i++) {
                Node tmp = list[cur.e].get(i);

                if(arr[tmp.e] == Integer.MAX_VALUE) {
                    pq.add(tmp);
                }
            }
        }
    }
}