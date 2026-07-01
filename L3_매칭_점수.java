import java.util.*;

class Solution {
    static int N;
    static int[] arr;
    static List<List<String>> list;
    static String[] str;
    static double[] scores;

    public int solution(String word, String[] pages) {

        N = pages.length;
        arr = new int[N];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        str = new String[N];
        scores = new double[N];
        word = word.toLowerCase();

        for(int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(pages[i], "\n");

            while(st.hasMoreTokens()) {
                String line = st.nextToken();

                if(line.contains("meta") && line.contains("content") && str[i] == null) {
                    StringTokenizer inner = new StringTokenizer(line, "\"");
                    while(inner.hasMoreTokens()) {
                        String text = inner.nextToken();
                        if(text.startsWith("https")) {
                            str[i] = text;
                            break;
                        }
                    }
                }

                if(line.contains("<a href=")) {
                    StringTokenizer inner = new StringTokenizer(line, "\"");
                    while(inner.hasMoreTokens()) {
                        String text = inner.nextToken();
                        if(text.startsWith("https")) {
                            list.get(i).add(text);
                        }
                    }
                }

                line = line.toLowerCase();
                line = line.replaceAll("[^a-z]", " ");

                StringTokenizer inner = new StringTokenizer(line);
                
                while(inner.hasMoreTokens()) {
                    if(word.equals(inner.nextToken())) {
                        arr[i]++;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            double res = arr[i] / (double)list.get(i).size();
            for(int j = 0; j < list.get(i).size(); j++) {
                String tmp = list.get(i).get(j);
                for(int k = 0; k < N; k++) {
                    if(str[k].equals(tmp)) {
                        scores[k] += res;
                    }
                }
            }
        }

        int ret = 0;
        double max = 0;
        for(int i =0; i < N; i++) {
            double tmp = arr[i] + scores[i];
            if(tmp > max) {
                max = tmp;
                ret = i;
            }
        }
        return ret;
    }
}