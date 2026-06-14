import java.util.*;

class Solution {

    public int solution(int[] money) {
    
        int[] T = new int[money.length];
        int[] F = new int[money.length];
        int l = money.length;

        T[0] = money[0];
        T[1] = money[0];

        F[0] = 0;
        F[1] = money[1];

        for (int i = 2; i < l; i++) {
            T[i] = Math.max(T[i - 1], money[i] + T[i - 2]);
            F[i] = Math.max(F[i - 1], money[i] + F[i - 2]);
        }

        return Math.max(T[l - 2], F[l - 1]);
    }
}