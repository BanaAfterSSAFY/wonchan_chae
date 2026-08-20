import java.util.*;

class Solution {

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            return this.x == ((Node) o).x && this.y == ((Node) o).y;
        }
    }
    
    static Map<Node, HashSet<Node>> map = new HashMap<>();
    
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1 ,-1 ,-1};
    
    public int solution(int[] arrows) {
        int answer = 0;
    
        map.put(new Node(0, 0), new HashSet<>());
        
        Node cur = new Node(0, 0);
        
        for(int i = 0 ; i < arrows.length; i++) {
            
            int direction = arrows[i];
            
            for(int j = 0; j < 2; j++) {
                int x = cur.x + dx[direction];
                int y = cur.y + dy[direction];

                Node next = new Node (x, y);

                if(!map.containsKey(next)) { 
                    map.put(next, new HashSet<>());
                }
                else if(map.containsKey(next) && !map.get(cur).contains(next)) {
                    answer++;
                } 

                map.get(cur).add(next);
                map.get(next).add(cur);

                cur = next; 
            }     
        }   
        
        return answer;
    }
}