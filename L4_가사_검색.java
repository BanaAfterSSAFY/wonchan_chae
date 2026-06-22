import java.util.*;

class Solution {
    static class Node {
        Map<Integer, Integer> t = new HashMap<>();
        Map<Character, Node> c = new HashMap<>();
        
        public void add(String str, int sch) {
            int l = str.length() - sch;

            t.put(l, t.getOrDefault(l, 0) + 1);
            
            if(l > 0) {
                char o = str.charAt(sch);
                Node tmp = c.getOrDefault(o, new Node());
                tmp.add(str, sch + 1);
                c.put(o, tmp);
            }
        }
        
        public int count(String str, int sch) {
            if(str.charAt(sch) == '?') {
                return t.getOrDefault(str.length() - sch, 0);
            }

            char o = str.charAt(sch);
            
            if(c.containsKey(o) == false) {
                return 0;
            }

            return c.get(o).count(str, sch + 1);
        }
    }
    
    private int solve(String query, Node a, Node b) {
        if (query.startsWith("?")) {
            return b.count(new StringBuilder(query).reverse().toString(), 0);
        }
        return a.count(query, 0);
    }
    
    public int[] solution(String[] words, String[] queries) {
        Node a = new Node();
        Node b = new Node();

        for(String at : words) {
            a.add(at, 0);
            b.add(new StringBuilder(at).reverse().toString(), 0);
        }
        
        return Arrays.stream(queries)
                .mapToInt(query -> solve(query, a, b))
                .toArray();
    }
}