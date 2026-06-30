import java.util.*;

class Solution {

    int N, W, A;
    int[] arr, dices, adc, bdc;
    List<Integer> asum, bsum;
    boolean[] check = new boolean[15];
    
    public int lower(List<Integer> arr, int t) {
        int s = 0;
        int e = arr.size();

        while(s < e) {
            Integer mid = (s + e) / 2;
            if(arr.get(mid) < t) {
                s = mid + 1;
            }
            else {
                e = mid;
            }
        }
        return e;
    }
    
    public void game(List<Integer> asum, List<Integer> bsum) {
        Collections.sort(bsum);
        for(Integer at : asum) {
            A += lower(bsum, at);
        }
    }
    
    public void roll(int n, int sum, int[] now, int[][] dice, List<Integer> comb) {
        if(n == N / 2) {
            comb.add(sum);
            return;
        }
        for(int i = 0; i < 6; i++) {
            roll(n+1, sum + dice[now[n]][i], now, dice, comb);
        }
    }
    
    public void exp(int[][] dice) {
        adc = new int[N / 2];
        bdc = new int[N / 2];
        int aSize = 0, bSize = 0;
        
        for(int i = 0; i < N; i++) {
            if(check[i]) {
                adc[aSize++] = i;
            }
            else {
                bdc[bSize++] = i;
            }
        }

        asum = new ArrayList<>(10000);
        bsum = new ArrayList<>(10000);
        
        roll(0, 0, adc, dice, asum);
        roll(0, 0, bdc, dice, bsum);
        
        game(asum, bsum);
        
        return;
    }
    
    public void solve(int n, int k, int[][] dice) {
        if(n == N / 2) {
            A = 0;
            exp(dice);
            if(A > W) {
                for(int i = 0; i < N / 2; i++) {
                    arr[i] = adc[i] + 1;
                }
                W = A;
            }
            return;
        }

        for(int i = k; i < N; i++) {
            check[i] = true;
            solve(n + 1, i + 1, dice);
            check[i] = false;
        }
    }

    public int[] solution(int[][] dice) {
        N = dice.length;
        dices = new int[N];
        arr = new int[N / 2];
        solve(0, 0, dice);
        return arr;
    }
}