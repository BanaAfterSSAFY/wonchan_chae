import java.util.*;

class Solution {

    static char[] arr;
    static boolean[] check = new boolean[26];
    static Map<Integer, String> map = new HashMap<>();

    public String solution(String sentence) {
        arr = sentence.toCharArray();
        Arrays.fill(check, false);
        map.clear();
        
        for(char c : arr) {
            if(c == ' ') {
                return "invalid";
            }
        }
        return solve(0, arr.length - 1, false).trim();
    }

    static String solve(int s, int e, boolean flag) {
        if(s > e) {
            return "";
        }

        if(map.containsKey(s)) {
            return map.get(s);
        }

        if(s + 1 <= e && isLower(arr[s]) && isLower(arr[s + 1])) {
            return process(s, "invalid", false, ' ');
        }

        StringBuilder sb = new StringBuilder();
        
        if(flag == false && isLower(arr[s])) {
            int l2 = s;

            char c = arr[l2];
            if(check[c - 'a'] == true) {
                return process(s, "invalid", false, ' ');
            }

            check[c - 'a'] = true;

            int r2 = find(l2, c);
            
            if(r2 == -1) {
                return process(s, "invalid", true, c);
            }

            String inner = solve(l2 + 1, r2 - 1, true).replace(" ", "").trim();
            String outer = solve(r2 + 1, e, false).trim();

            if(inner.contains("invalid") || outer.contains("invalid")) {
                return process(s, "invalid", true, c);
            }

            String result = inner + " " + outer;
            return process(s, result, false, ' ');

        }
        else {
            if(isLower(arr[s]) || (flag && !formatted(s, e))) {
                return process(s, "invalid", false, ' ');
            }

            int l = s;
            if(l + 1 <= e && isLower(arr[l + 1])) {
                String solved = solve(l + 1, e, flag).trim();

                if(solved.contains("invalid") == false) {
                    return process(s, arr[l] + " " + solved, false, ' ');
                }

                char c = arr[l + 1];
                
                if(check[c - 'a']) {
                    return process(s, "invalid", false, ' ');
                }

                check[c - 'a'] = true;

                for(int k = 0; l <= e; l++, k++) {
                    if(isUpper(arr[l])) {
                        if(k % 2 == 1) {
                            break;
                        }
                        sb.append(arr[l]);
                    }
                    else {
                        if(l == e || k % 2 == 0) {
                            return process(s, "invalid", true, c);
                        }

                        if(arr[l] != c) {
                            break;
                        }
                    }
                }

                solved = solve(l, e, flag).trim();
                
                if(solved.contains("invalid") == false) {
                    return process(s, sb + " " + solved, false, ' ');
                }

                return process(s, "invalid", true, c);
            }
            else {
                while(l <= e && !isLower(arr[l])) {
                    sb.append(arr[l++]);
                }

                String solved = solve(l, e, flag).trim();
                if(solved.contains("invalid") == false) {
                    return process(s, sb + " " + solved, false, ' ');
                }

                sb.deleteCharAt(sb.length() - 1);
                solved = solve(l - 1, e, false).trim();
                
                if(!solved.contains("invalid")) {
                    return process(s, sb + " " + solved, false, ' ');
                }

                return process(s, "invalid", false, ' ');
            }
        }
    }

    static boolean formatted(int s, int e) {
        if(s == e) {
            return !isLower(arr[s]);
        }

        if(isLower(arr[s])) {
            return false;
        }

        if(isLower(arr[s + 1])) {
            int lower = 0, upper = 0;
            for(int i = s, k = 0; i <= e; i++, k++) {
                if(isUpper(arr[i])) {
                    if(k % 2 == 1) {
                        return false;
                    }
                    upper++;
                }
                else {
                    if(k % 2 == 0) {
                        return false;
                    }
                    lower++;
                }
            }

            return lower != upper;
        }
        else {
            for(int i = s; i <= e; i++) {
                if(isLower(arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    static int find(int s, char c) {
        for(int i = s + 1; i < arr.length; i++) {
            if(arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    static String process(int s, String result, boolean hasReset, char c) {
        if(hasReset == true) {
            check[c - 'a'] = false;
        }

        map.put(s, result);
        return result;
    }
}