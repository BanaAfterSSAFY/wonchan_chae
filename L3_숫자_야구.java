import java.util.function.Function;
import java.util.*;

class Solution {

    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> list = new ArrayList<>();
        insertAllCase(list);

        while(list.size() != 1) {
            int num = list.get(0);
            String result = submit.apply(num);
            list = filterList(list, num, result.charAt(0) - '0', result.charAt(3) - '0');
        }

        return list.get(0);
    }

    static void insertAllCase(List<Integer> list) {
        for(int a = 1; a <= 9; a++) {
            for(int b = 1; b <= 9; b++) {
                if(a == b) {
                    continue;
                }

                for(int c = 1; c <= 9; c++) {
                    if(a == c || b == c) {
                        continue;
                    }

                    for(int d = 1; d <= 9; d++) {
                        if(a == d || b == d || c == d) {
                            continue;
                        }

                        list.add(1000 * a + 100 * b + 10 * c + d);
                    }
                }
            }
        }
    }

    static List<Integer> filterList(List<Integer> list, int num, int strike, int ball) {
        List<Integer> tmp = new ArrayList<>();
        String str = Integer.toString(num);
        Set<Character> set = new HashSet<>();

        set.add(str.charAt(0));
        set.add(str.charAt(1));
        set.add(str.charAt(2));
        set.add(str.charAt(3));

        for(int at : list) {
            String s = Integer.toString(at);
            int cnt = 0;
            int nnt = 0;

            for(int i = 0; i < 4; i++) {
                if(str.charAt(i) == s.charAt(i)) {
                    cnt++;
                }
                else if(set.contains(s.charAt(i))) {
                    nnt++;
                }
            }

            if(cnt == strike && nnt == ball) {
                tmp.add(at);
            }
        }

        return tmp;
    }
}