import java.util.*;

class Solution {

    public long solve(String time) {
        long val = 0;
        String[] split = time.split(":");
        val += (Long.parseLong(split[0]) * 60 * 60);
        val += (Long.parseLong(split[1]) * 60);
        val *= 1000;
        val += (Double.parseDouble(split[2]) * 1000);

        return val;
    }

    static class Job {
        long s;
        long e;

        public Job(long s, long e) {
            this.s = s;
            this.e = e;
        }
    }

    public int solution(String[] lines) {
        List<Job> jobList = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            long end = solve(splits[1]);
            long start = end - (long) (Double.parseDouble(splits[2].replace("s", "")) * 1000) + 1;

            Job job = new Job(start, end);
            jobList.add(job);
        }

        int ans = 1;
        for(int i = 0; i < jobList.size(); i++) {
            int cnt = 0;
            long end = jobList.get(i).e;
            for (Job job : jobList) {
                if(job.s < end + 1000 && job.e >= end) cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
        return ans;
    }
}