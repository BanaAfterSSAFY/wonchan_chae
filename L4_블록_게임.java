import java.util.*;

class Solution {

    static class Block {
        int sx, ex, sy, ey;

        public Block(int sx, int ex, int sy, int ey) {
            this.sx = sx;
            this.ex = ex;
            this.sy = sy;
            this.ey = ey;
        }
    }

    static public int N;

    public int solution(int[][] board) {
        N = board.length;
        Block[] blocks;

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = Math.max(cnt, board[i][j]);
            }
        }

        blocks = new Block[cnt + 1];
        
        for(int i = 0; i < cnt + 1; i++) {
            blocks[i] = new Block(50, -1, 50, -1);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int cur = board[i][j];
                if(cur == 0) {
                    continue;
                }

                blocks[cur].sx = Math.min(blocks[cur].sx, i);
                blocks[cur].ex = Math.max(blocks[cur].ex, i);
                blocks[cur].sy = Math.min(blocks[cur].sy, j);
                blocks[cur].ey = Math.max(blocks[cur].ey, j);
            }
        }

        int[] bounds = new int[N];
        Arrays.fill(bounds, -1);

        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N; i++) {
                if(board[i][j] == 0) {
                    continue;
                }

                bounds[j] = i;
                break;
            }
            if(bounds[j] == -1) {
                bounds[j] = N;
            }
        }

        int result = 0;
        while(true) {
            boolean flag = false;

            for(int ind = 0; ind < N; ind++) {
                if(bounds[ind] == N) {
                    continue;
                }

                int cur = board[bounds[ind]][ind];

                boolean fin = false;
                for(int i = blocks[cur].sx; i <= blocks[cur].ex; i++) {
                    for(int j = blocks[cur].sy; j <= blocks[cur].ey; j++) {
                        if(board[i][j] == cur) {
                            continue;
                        }

                        if(board[i][j] == 0 && bounds[j] > i) {
                            continue;
                        }

                        fin = true;
                        break;
                    }

                    if(fin == true)  {
                        break;
                    }
                }

                if(fin == true) {
                    continue;
                }

                result++;
                flag = true;

                for(int i = blocks[cur].sx; i <= blocks[cur].ex; i++) {
                    for(int j = blocks[cur].sy; j <= blocks[cur].ey; j++) {
                        if(board[i][j] == cur) {
                            board[i][j] = 0;
                        }
                    }
                }

                for(int j = blocks[cur].sy; j <= blocks[cur].ey; j++) {
                    bounds[j] = -1;
                    for(int i = 0; i < N; i++) {
                        if(board[i][j] == 0) {
                            continue;
                        }
                        bounds[j] = i;
                        break;
                    }

                    if(bounds[j] == -1) {
                        bounds[j] = N;
                    }
                }
            }

            if(flag == false) {
                break;
            }
        }
        return result;
    }
}