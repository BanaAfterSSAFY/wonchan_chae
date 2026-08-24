import java.util.*;

class Solution {

    static class Node {
        long x;
        int y1;
        int y2;
        int type;

        Node(long x, int y1, int y2, int type) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
        }
    }

    static long[] ys;
    static int[] count;
    static long[] length;

    public long solution(int[][] rectangles) {
        int n = rectangles.length;

        Node[] nodes = new Node[n * 2];
        long[] vals = new long[n * 2];

        for(int i = 0; i < n; i++) {
            int x1 = rectangles[i][0];
            int y1 = rectangles[i][1];
            int x2 = rectangles[i][2];
            int y2 = rectangles[i][3];

            nodes[i * 2] = new Node(x1, y1, y2, 1);
            nodes[i * 2 + 1] = new Node(x2, y1, y2, -1);

            vals[i * 2] = y1;
            vals[i * 2 + 1] = y2;
        }

        Arrays.sort(nodes, Comparator.comparingLong(e -> e.x));
        Arrays.sort(vals);

        int size = 0;

        for(long y : vals) {
            if(size == 0 || vals[size - 1] != y) {
                vals[size++] = y;
            }
        }

        ys = Arrays.copyOf(vals, size);

        count = new int[size * 4];
        length = new long[size * 4];

        long answer = 0;
        long prev = nodes[0].x;

        int i = 0;

        while(i < nodes.length) {
            long x = nodes[i].x;

            answer += (x - prev) * length[1];

            while(i < nodes.length && nodes[i].x == x) {
                Node event = nodes[i];

                int left = solve(event.y1);
                int right = solve(event.y2) - 1;

                update(1, 0, size - 2, left, right, event.type);

                i++;
            }

            prev = x;
        }

        return answer;
    }

    static void update(int node, int start, int end, int left, int right, int value) {
        if(right < start || end < left) {
            return;
        }

        if(left <= start && end <= right) {
            count[node] += value;
            pull(node, start, end);
            return;
        }

        int mid = (start + end) / 2;

        update(node * 2, start, mid, left, right, value);
        update(node * 2 + 1, mid + 1, end, left, right, value);

        pull(node, start, end);
    }

    static void pull(int node, int start, int end) {
        if(count[node] > 0) {
            length[node] = ys[end + 1] - ys[start];
        }
        else if(start == end) {
            length[node] = 0;
        }
        else {
            length[node] = length[node * 2] + length[node * 2 + 1];
        }
    }

    static int solve(long target) {
        int left = 0;
        int right = ys.length;

        while(left < right) {
            int mid = (left + right) / 2;

            if(ys[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }
}