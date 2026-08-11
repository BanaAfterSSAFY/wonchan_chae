import java.util.*;

public class Solution {

    static int sum(int a, int b) {
        long sum = (long) a + b;
        return (int) (sum % 1000000007);
    }

    static class Node {
        long sum;
        int cnt;
        int off;

        public Node(long sum, int cnt, int off) {
            this.sum = sum;
            this.cnt = cnt;
            this.off = off;
        }
    }

    static int solve(int[] b) {
        List<Map<Long, Node>> list = new ArrayList<>(b.length);
        for(int i = 0; i < b.length; i++) {
            Map<Long, Node> map = new HashMap<>();
            list.add(map);

            long sum = b[i];
            if (i == 0) {
                map.put(sum, new Node(sum, 1, i));
            }
            else {
                map.put(sum, new Node(sum, list.get(i - 1).values().stream().mapToInt(c -> c.cnt).reduce(Solution::sum).orElse(0), i));
            }

            while (true) {
                sum *= 2;
                Node lnt = map.get(sum / 2);
                int pos = lnt.off - 1;
                
                if(pos < 0) {
                    break;
                }

                Map<Long, Node> tmp = list.get(pos);
                if(tmp.containsKey(sum / 2) == false) {
                    break;
                }

                Node cnt = tmp.get(sum / 2);
                map.put(sum, new Node(sum, cnt.cnt, cnt.off));
            }
        }
        return list.get(list.size() - 1).values().stream().mapToInt(c -> c.cnt).reduce(Solution::sum).orElse(0);
    }

    public int[] solution(int[] a, int[] s) {
        int[] answer = new int[s.length];
        int off = 0;
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = solve(Arrays.copyOfRange(a, off, off + s[i]));
            off += s[i];
        }
        
        return answer;
    }
}