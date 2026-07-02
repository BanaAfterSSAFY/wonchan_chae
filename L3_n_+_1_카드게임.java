import java.util.*;

class Solution {

    static Set<Integer> card = new HashSet<>();
    static Set<Integer> test = new HashSet<>();
    static int N, P;
    static int round = 1;
    static int cnt;

    public int solution(int coin, int[] cards) {
        this.cnt = coin;
        this.N = cards.length;

        for(int i = 0; i < N / 3; i++) {
            card.add(cards[i]);
        }

        for(int at : card) {
            if(card.contains(N + 1 - at)) {
                P++;
            }
        }
        P /= 2;

        for(int i = N / 3; i < N; i += 2) {
            solve(cards[i]);
            solve(cards[i + 1]);

            if(P < 1 && cnt > 1) {
                for(int card : test) {
                    if(test.contains(N + 1 - card)) {
                        P++;
                        cnt -= 2;
                        test.remove(card);
                        break;
                    }
                }
            }

            if(P < 1) {
                break;
            }
            round++;
            P--;
        }

        return round;
    }

    public void solve(int num) {
        if(cnt > 0 && card.contains(N + 1 - num)) {
            cnt--;
            P++;
            return;
        }
        test.add(num);
    }
}