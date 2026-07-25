import java.util.*;

class Solution {

    public int[][] solution(int[][] rc, String[] operations) {

        int r = rc.length;
        int c = rc[0].length;

        ArrayDeque<Integer> L = new ArrayDeque<>();
        ArrayDeque<Integer> R = new ArrayDeque<>();
        ArrayDeque<ArrayDeque<Integer>> list = new ArrayDeque<>();

        for(int i = 0; i < r; i++) {
            L.add(rc[i][0]);
            R.add(rc[i][c-1]);
            ArrayDeque<Integer> tmp = new ArrayDeque<>();
            
            for(int j = 1; j < c - 1; j++) {
                tmp.add(rc[i][j]);
            }
            list.add(tmp);
        }

        for(String str : operations) {
            if(str.charAt(0) == 'S') {
                L.addFirst(L.removeLast());
                R.addFirst(R.removeLast());
                list.addFirst(list.removeLast());
            }
            else {
                list.getFirst().addFirst(L.removeFirst());
                R.addFirst(list.getFirst().removeLast());

                list.getLast().addLast(R.removeLast());
                L.addLast(list.getLast().removeFirst());
            }
        }

        int[][] result = new int[r][c];
        for(int i = 0; i < r; i++) {
            result[i][0] = L.removeFirst();
            result[i][c-1] = R.removeFirst();
            ArrayDeque<Integer> tmp = list.removeFirst();
            
            for(int j = 1; j < c - 1; j++) {
                result[i][j] = tmp.removeFirst();
            }
        }

        return result;
    }
}