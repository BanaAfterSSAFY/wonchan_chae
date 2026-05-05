import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();

        int l = genres.length;
        Map<String, Integer> sum = new HashMap<>();
        Map<String, HashMap<Integer , Integer>> info = new HashMap<>();

        for(int idx = 0; idx < l; idx++) {
            String cur = genres[idx];
            int now = plays[idx];

            if(info.containsKey(cur) == false) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(idx, now);
                info.put(cur, map);
            }
            else {
                info.get(cur).put(idx, now);
            }

            sum.put(cur, sum.getOrDefault(cur, 0) + now);
        }

        List<String> list = new ArrayList(sum.keySet());
        Collections.sort(list, (o1 , o2) -> sum.get(o2) - sum.get(o1));

        for(String at : list) {
            Map<Integer, Integer> map = info.get(at);

            List<Integer> tmp = new ArrayList(map.keySet());
            Collections.sort(tmp, (o1 , o2) -> map.get(o2) - map.get(o1));

            answer.add(tmp.get(0));

            if(tmp.size() > 1) {
                answer.add(tmp.get(1));
            }
        } 
        return answer.stream().mapToInt(i -> i).toArray();
    }
}