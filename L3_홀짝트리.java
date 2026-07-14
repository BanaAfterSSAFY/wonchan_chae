import java.util.*;

class Solution {

    static class Node {
        int o;
        int e;
        int ro;
        int re;
    }

    public int roe(Node n) {
        if((n.ro == 1 && n.re == 0) || (n.ro == 0 && n.re == 1)) {
            return 1;
        }
        return 0;
    }
    
    public int oe(Node n) {
        if((n.o == 1 && n.e == 0) || (n.o == 0 && n.e == 1)) {
            return 1;
        }
        return 0;
    }

    public void solve(Map<Integer, List<Integer>> tree, Set<Integer> check, Node res, int current) {
        List<Integer> tmp = tree.get(current);
        
        if(tmp.size() % 2 == 0 && current % 2 == 0) {
            res.e++;
        }
        
        if(tmp.size() % 2 == 1 && current % 2 == 0) {
            res.re++;
        }
        
        if(tmp.size() % 2 == 0 && current % 2 == 1) {
            res.ro++;
        }
        
        if(tmp.size() % 2 == 1 && current % 2 == 1) {
            res.o++;
        }
        
        check.add(current);

        for(int at : tmp) {
            if(check.contains(at)) {
                continue;
            }
            solve(tree, check, res, at);
        }
    }
    
    public Map<Integer, List<Integer>> init(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        
        for(int at : nodes) {
            tree.put(at, new ArrayList<>());
        }
        
        for(int[] at : edges) {
            tree.get(at[0]).add(at[1]);
            tree.get(at[1]).add(at[0]);
        }
        
        return tree;
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        Map<Integer, List<Integer>> tree = init(nodes, edges);
        
        Set<Integer> check = new HashSet<>();
        
        for(int key : tree.keySet()) {
            if(check.contains(key)) {
                continue;
            }
            Node res = new Node();
            solve(tree, check, res, key);
            answer[0] += oe(res);
            answer[1] += roe(res);
            
        }
        return answer;
    }
}