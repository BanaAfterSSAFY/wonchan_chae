import java.util.*;

class Solution {

    static String[][] map = new String [50][50];
    static int[] parent = new int [2500];
    static ArrayList<String> answers = new ArrayList<>();
    
    public String[] solution(String[] commands) {

        for(int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < 50; i++) {
            Arrays.fill(map[i], "EMPTY");
        }

        for(String at : commands) {
            String[] inp = at.split(" ");

            if(inp[0].equals("UPDATE")) {
                if(inp.length == 3) {
                    for(int i = 0; i < 50; i++) {
                        for(int j = 0; j < 50; j++) {
                            if(map[i][j].equals(inp[1])) {
                                map[i][j] = inp[2]; 
                            }
                        }
                    }
                }
                else{
                    int r = Integer.parseInt(inp[1]) - 1;
                    int c = Integer.parseInt(inp[2]) - 1;
                    
                    String value = inp[3];
                    int tmp = find(r * 50 + c);
                    
                    for(int i = 0; i < 2500; i++) {
                        if(parent[i] == tmp) {
                            int x = i / 50;
                            int y = i % 50;
                            map[x][y] = value;
                        }
                    }
                }
            }
            else if(inp[0].equals("MERGE")) {
                int r1 = Integer.parseInt(inp[1]) - 1;
                int c1 = Integer.parseInt(inp[2]) - 1;
                int r2 = Integer.parseInt(inp[3]) - 1;
                int c2 = Integer.parseInt(inp[4]) - 1;
                
                int root1 = find(r1 * 50 + c1);
                int root2 = find(r2 * 50 + c2);
                union(root1, root2);
                
                int tmp = find(root1);
                
                String value = "EMPTY";
                if(map[r1][c1].equals("EMPTY") && !map[r2][c2].equals("EMPTY")) {
                    value = map[r2][c2];
                }
                else {
                    value = map[r1][c1];
                }
                
                for(int i = 0; i < 2500; i++) {
                    if(parent[i] == root1 || parent[i] == root2 || parent[i] == tmp) {
                        parent[i] = tmp;
                        int x = i / 50;
                        int y = i % 50;
                        map[x][y] = value;
                    }
                }
            }
            else if(inp[0].equals("UNMERGE")) {
                int r = Integer.parseInt(inp[1]) - 1;
                int c = Integer.parseInt(inp[2]) - 1;
                
                String value = map[r][c];
                int tmp = find(r * 50 + c);
                
                for(int i = 0; i < 2500; i++) {
                    if(parent[i] == tmp) {
                        parent[i] = i;
                        int x = i / 50;
                        int y = i % 50;
                        map[x][y] = "EMPTY";
                    }
                }
                map[r][c] = value;
            }
            else {
                int r = Integer.parseInt(inp[1]) - 1;
                int c = Integer.parseInt(inp[2]) - 1;
                answers.add(map[r][c]);
            }
        }

        String[] answer = new String [answers.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }

    public static int find(int x) {
        if(x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            if(x > y) {
                parent[x] = y;
            }
            else {
                parent[y] = x;
            }
        }
    } 
}