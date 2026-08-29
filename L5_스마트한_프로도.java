import java.util.*;

class Solution {

    static int[] A;
    static int[] B;
    static int[] old;
    static int[] cur;

    static boolean[] inOld;
    static boolean[] inNew;
    static boolean[] check;

    static ArrayList<int[]> sop;
    static ArrayList<int[]> lop;

    public int[][] solution(int n, int m, int[] A, int[] B, int k, int m1, int m2, int[] e1, int[] e2) {
        this.A = A;
        this.B = B;

        inOld = new boolean[m + 1];
        inNew = new boolean[m + 1];
        check = new boolean[m + 1];

        old = new int[n];
        cur = new int[n];

        Arrays.fill(old, -1);
        Arrays.fill(cur, -1);

        for(int edge : e1) {
            inOld[edge] = true;
        }

        for(int edge : e2) {
            inNew[edge] = true;
        }

        for(int edge : e1) {
            if(inNew[edge]) {
                continue;
            }

            int u = A[edge - 1] - 1;
            int v = B[edge - 1] - 1;

            old[u] = edge;
            old[v] = edge;
        }

        for(int edge : e2) {
            if(inOld[edge]) {
                continue;
            }

            int u = A[edge - 1] - 1;
            int v = B[edge - 1] - 1;

            cur[u] = edge;
            cur[v] = edge;
        }

        sop = new ArrayList<>();
        lop = new ArrayList<>();

        for(int edge : e1) {
            if(!inNew[edge] && !check[edge]) {
                solve(edge);
            }
        }

        for(int edge : e2) {
            if(!inOld[edge] && !check[edge]) {
                solve(edge);
            }
        }

        int[][] answer = new int[sop.size() + lop.size()][2];
        int index = 0;

        for(int[] op : sop) {
            answer[index++] = op;
        }

        for(int[] op : lop) {
            answer[index++] = op;
        }

        return answer;
    }

    static void solve(int startEdge) {
        ArrayList<Integer> component = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(startEdge);

        int endpoint = -1;
        int oldCount = 0;
        int newCount = 0;

        while(!stack.isEmpty()) {
            int edge = stack.pop();

            if(check[edge]) {
                continue;
            }

            check[edge] = true;
            component.add(edge);

            if(inOld[edge]) {
                oldCount++;
            }
            else {
                newCount++;
            }

            int u = A[edge - 1] - 1;
            int v = B[edge - 1] - 1;

            int degreeU = degree(u);
            int degreeV = degree(v);

            if(degreeU == 1) {
                endpoint = u;
            }

            if(degreeV == 1) {
                endpoint = v;
            }

            pushEdges(stack, u);
            pushEdges(stack, v);
        }

        boolean cycle = endpoint == -1;

        int size = component.size();
        int[] order = new int[size];

        if(cycle) {
            int first = -1;

            for(int edge : component) {
                if(inOld[edge]) {
                    first = edge;
                    break;
                }
            }

            order[0] = first;

            int u = A[first - 1] - 1;
            int v = B[first - 1] - 1;

            int current = v;
            int previous = first;

            for(int i = 1; i < size; i++) {
                int edge = nextEdge(current, previous);

                order[i] = edge;

                current = other(edge, current);
                previous = edge;
            }
        }
        else {
            int current = endpoint;
            int previous = -1;

            for(int i = 0; i < size; i++) {
                int edge = nextEdge(current, previous);

                order[i] = edge;

                current = other(edge, current);
                previous = edge;
            }
        }

        ArrayList<int[]> ops = new ArrayList<>();

        if(cycle) {
            ops.add(new int[]{0, order[0]});

            for(int i = 2; i < size; i += 2) {
                ops.add(new int[]{0, order[i]});
                ops.add(new int[]{1, order[i - 1]});
            }

            ops.add(new int[]{1, order[size - 1]});
        }
        else if(inOld[order[0]]) {
            ops.add(new int[]{0, order[0]});

            for(int i = 2; i < size; i += 2) {
                ops.add(new int[]{0, order[i]});
                ops.add(new int[]{1, order[i - 1]});
            }

            if(size % 2 == 0) {
                ops.add(new int[]{1, order[size - 1]});
            }
        }
        else {
            for(int i = 1; i < size; i += 2) {
                ops.add(new int[]{0, order[i]});
                ops.add(new int[]{1, order[i - 1]});
            }

            if(size % 2 == 1) {
                ops.add(new int[]{1, order[size - 1]});
            }
        }

        if(newCount < oldCount) {
            lop.addAll(ops);
        }
        else {
            sop.addAll(ops);
        }
    }

    static int degree(int vertex) {
        int result = 0;

        if(old[vertex] != -1) {
            result++;
        }

        if(cur[vertex] != -1) {
            result++;
        }

        return result;
    }

    static void pushEdges(ArrayDeque<Integer> stack, int vertex) {
        if(old[vertex] != -1 && !check[old[vertex]]) {
            stack.push(old[vertex]);
        }

        if(cur[vertex] != -1 && !check[cur[vertex]]) {
            stack.push(cur[vertex]);
        }
    }

    static int nextEdge(int vertex, int previous) {
        if(old[vertex] != -1 && old[vertex] != previous) {
            return old[vertex];
        }

        return cur[vertex];
    }

    static int other(int edge, int vertex) {
        int u = A[edge - 1] - 1;
        int v = B[edge - 1] - 1;

        return u == vertex ? v : u;
    }
}