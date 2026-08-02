import java.util.*;

class Solution {

    public class Node {
        int num;
        Queue<Node> child;
        List<Integer> tmpChild;

        public Node(int n) {
            this.num = n;
            child = new ArrayDeque<>();
            tmpChild = new ArrayList<>();
        }

        public void makeQueue() {
            Collections.sort(tmpChild);
            for (Integer cur : tmpChild)
                child.add(node[cur]);
        }
    }

    int N;
    Node[] node;
    Node root;
    List<Integer> leaves = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();
    Map<Integer, Integer> dropCntMap = new HashMap<>();
    Map<Integer, Integer> targetMap = new HashMap<>();

    public int[] solution(int[][] edges, int[] target) {
        init(edges, target);
        if (!simulate()) return new int[] { - 1};
        return makeResult();
    }

    private int[] makeResult() {
        int[] result = new int[sequence.size()];
        for (int i = 0; i < sequence.size(); i++) {
            int num = sequence.get(i);
            int dropCntValue = dropCntMap.get(num);
            int targetValue = targetMap.get(num);
            int res;
            if (targetValue - 1 <= (dropCntValue - 1) * 3) res = 1;
            else if (targetValue - 2 <= (dropCntValue - 1) * 3) res = 2;
            else res = 3;
            result[i] = res;
            dropCntMap.put(num, dropCntMap.get(num) - 1);
            targetMap.put(num, targetMap.get(num) - res);
        }
        return result;
    }

    private boolean simulate() {

        while(true) {
            Node cur = root;
            while(true) {
                if (cur.child.isEmpty()) break;
                Node next = cur.child.poll();
                cur.child.add(next);
                cur = next;
            }

            sequence.add(cur.num);
            dropCntMap.put(cur.num, dropCntMap.get(cur.num) + 1);
            boolean contFlag = false;

            for (Integer leafNum : leaves) {
                int targetValue = targetMap.get(leafNum);
                int dropCntValue = dropCntMap.get(leafNum);

                if (targetValue == 0) {
                    if (dropCntValue != 0) return false;
                }
                else {
                    if (dropCntValue == 0 || targetValue / dropCntValue > 3 || ( targetValue / dropCntValue == 3 && targetValue % dropCntValue != 0)) { //숫자 더 떨어뜨려야함
                        contFlag = true;
                        break;
                    } else if (targetValue / dropCntValue == 0) {
                        return false;
                    }
                }
            }
            if (!contFlag) break;
        }
        return true;
    }

    private void init(int[][] edges, int[] target) {

        N = target.length;
        node = new Node[N];
        for (int i = 0; i < N; i++) node[i] = new Node(i);
        root = node[0];

        for (int[] edge : edges) {
            int parent = edge[0] - 1;
            int child = edge[1] - 1;
            node[parent].tmpChild.add(child);
        }

        for (int i = 0; i < N; i++) node[i].makeQueue();

        for (int i = 0; i < N; i++) {
            if (!node[i].child.isEmpty()) continue;
            leaves.add(i);
            dropCntMap.put(i, 0);
            targetMap.put(i, target[i]);
        }
    }
}