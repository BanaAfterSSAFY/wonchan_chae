class Solution {
    
    static class Point{
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    
    int[][] map;
    int N, M;
    
    public int solution(int[][] map, int[] aloc, int[] bloc) {
        int answer = -1;
        this.map = map;
        N = map.length;
        M = map[0].length;
        
        Point A = new Point(aloc[0], aloc[1]);
        Point B = new Point(bloc[0], bloc[1]);
        
        answer = solve(A, B);
        return answer;
    }
    
    public int solve(Point o1, Point o2) {
        if(map[o1.x][o1.y] == 0) {
            return 0;
        }
        
        int x = o1.x;
        int y = o1.y;
        int result = 0;
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            
            if(nx < 0 || ny < 0 || nx >= N|| ny >= M || map[nx][ny] == 0) {
                continue;
            }

            map[x][y] = 0;
            
            int val = solve(o2, new Point(nx, ny)) + 1;

            map[x][y] = 1;
            
            if(val % 2 == 1 && result % 2 == 0) {
                result = val;
            }
            else if(val % 2 == 0 && result % 2 == 0 ) {
                result = Math.max(result, val);
            }
            else if(val % 2 == 1 && result % 2 == 1 ) {
                result = Math.min(result, val);
            }
        }
        return result;
    }
}