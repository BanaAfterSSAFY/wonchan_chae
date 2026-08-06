import java.util.*;

class Solution {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int n, int m, int[][] timetable) {

        int[] arr = new int[722];

        for(int i = 0; i < m; i++) {
            arr[timetable[i][0] - 600]++;
            arr[timetable[i][1] - 600 + 1]--;
        }

        int sum = 0, max = 0;
        for(int i = 0; i <= 720; i++) {
            sum += arr[i];
            arr[i] = sum;
            max = Math.max(max, arr[i]);
        }

        if(max <= 1) {
            return 0;
        }

        ArrayList<Point> list = new ArrayList<>();
        for(int k = 2 * (n - 1); k >= 1; k--) {
        
            for(int t = 0; t < n; t++) {
                list.clear();
                int cnt = 0;

                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        
                        if(i == 0 && j < t) {
                            continue;
                        }
                        
                        boolean flag = true;
                        for(Point at : list) {
                            if(Math.abs(at.x - i) + Math.abs(at.y - j) >= k) {
                                continue;
                            }
                            flag = false;
                            break;
                        }
                        if(flag == true) {
                            if(++cnt == max) {
                                return k;
                            }
                            list.add(new Point(i, j));
                        }
                    }
                }
            }
        }
        return 0;
    }
}