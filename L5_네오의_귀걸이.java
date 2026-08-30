import java.util.*;

class Solution {
    
    static int n;
    static int[] x;
    static int[] y;
    static long[] k;
    static long[] z;
    static long[] start;
    static long[] end;
    static Group[] groups;
    static HashMap<Long, Group> map;

    static class Group {
        long z;
        int[] ids;
        int[] xs;
        long[] pref;
        long[] diff;

        Group(long z, int[] ids, int[] xs) {
            this.z = z;
            this.ids = ids;
            this.xs = xs;
            this.pref = new long[ids.length + 1];
            this.diff = new long[ids.length + 1];
        }
    }

    static class Record {
        long z;
        long x;
        long value;
        int queryIndex;
        int type;

        Record(long z, long x, long value, int queryIndex, int type) {
            this.z = z;
            this.x = x;
            this.value = value;
            this.queryIndex = queryIndex;
            this.type = type;
        }
    }

    public int[] solution(int n, int[][] point, int[][] query) {
        this.n = n;

        x = new int[n];
        y = new int[n];
        k = new long[n];
        z = new long[n];

        int s = 0;
        int e = 0;

        HashMap<Integer, Integer> pointIndex = new HashMap<>((int)(n / 0.75f) + 1);

        for(int i = 0; i < n; i++) {
            x[i] = point[i][0];
            y[i] = point[i][1];
            k[i] = point[i][2];
            z[i] = (long)x[i] - y[i];

            if(x[i] < x[s]) {
                s = i;
            }

            if(x[i] > x[e]) {
                e = i;
            }

            pointIndex.put(x[i], i);
        }

        Integer[] order = new Integer[n];

        for(int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {
            int cmp = Long.compare(z[a], z[b]);

            if(cmp != 0) {
                return cmp;
            }

            return Integer.compare(x[a], x[b]);
        });

        ArrayList<Group> groupList = new ArrayList<>();
        map = new HashMap<>((int)(n / 0.75f) + 1);

        int pos = 0;

        while(pos < n) {
            int end = pos + 1;
            long currentZ = z[order[pos]];

            while(end < n && z[order[end]] == currentZ) {
                end++;
            }

            int size = end - pos;
            int[] ids = new int[size];
            int[] xs = new int[size];

            for(int i = 0; i < size; i++) {
                int id = order[pos + i];
                ids[i] = id;
                xs[i] = x[id];
            }

            Group group = new Group(currentZ, ids, xs);

            groupList.add(group);
            map.put(currentZ, group);

            pos = end;
        }

        groups = groupList.toArray(new Group[0]);

        start = new long[n];
        end = new long[n];

        for(Group group : groups) {
            for(int i = 0; i < group.ids.length; i++) {
                int id = group.ids[i];
                long value = 0;

                if(id == s) {
                    value = 1;
                }
                else if(k[id] > 0) {
                    Group prev = map.get(z[id] - k[id]);

                    if(prev != null) {
                        int left = upperBound(prev.xs, (long)x[id] - k[id]);
                        int right = lowerBound(prev.xs, x[id]);

                        value = prev.pref[right] - prev.pref[left];

                        if(value < 0) {
                            value += 1000000007;
                        }
                    }
                }

                start[id] = value;

                group.pref[i + 1] = group.pref[i] + value;

                if(group.pref[i + 1] >= 1000000007) {
                    group.pref[i + 1] -= 1000000007;
                }
            }
        }

        for(int gi = groups.length - 1; gi >= 0; gi--) {
            Group group = groups[gi];

            long current = 0;

            for(int i = 0; i < group.ids.length; i++) {
                current += group.diff[i];

                if(current >= 1000000007) {
                    current -= 1000000007;
                }

                int id = group.ids[i];

                end[id] = id == e ? 1 : current;
            }

            for(int id : group.ids) {
                if(k[id] <= 0 || end[id] == 0) {
                    continue;
                }

                Group prev = map.get(z[id] - k[id]);

                if(prev == null) {
                    continue;
                }

                int left = upperBound(prev.xs, (long)x[id] - k[id]);
                int right = lowerBound(prev.xs, x[id]);

                if(left >= right) {
                    continue;
                }

                prev.diff[left] += end[id];

                if(prev.diff[left] >= 1000000007) {
                    prev.diff[left] -= 1000000007;
                }

                prev.diff[right] -= end[id];

                if(prev.diff[right] < 0) {
                    prev.diff[right] += 1000000007;
                }
            }
        }

        long total = start[e];

        long[] newToEnd = new long[query.length];

        ArrayList<Record> records = new ArrayList<>(n * 2 + query.length);

        for(int i = 0; i < n; i++) {
            if(k[i] <= 0 || end[i] == 0) {
                continue;
            }

            long targetZ = z[i] - k[i];
            long startX = (long)x[i] - k[i] + 1;
            long endX = x[i];

            records.add(new Record(targetZ, startX, end[i], -1, 0));
            records.add(new Record(targetZ, endX, 1000000007 - end[i], -1, 0));
        }

        for(int i = 0; i < query.length; i++) {
            if(query[i][0] == 0) {
                long queryZ = (long)query[i][1] - query[i][2];

                records.add(new Record(
                    queryZ,
                    query[i][1],
                    0,
                    i,
                    1
                ));
            }
        }

        records.sort((p1, p2) -> {
            int cmp = Long.compare(p1.z, p2.z);

            if(cmp != 0) {
                return cmp;
            }

            cmp = Long.compare(p1.x, p2.x);

            if(cmp != 0) {
                return cmp;
            }

            return Integer.compare(p1.type, p2.type);
        });

        long active = 0;
        long currentZ = Long.MIN_VALUE;

        for(Record record : records) {
            if(record.z != currentZ) {
                currentZ = record.z;
                active = 0;
            }

            if(record.type == 0) {
                active += record.value;

                if(active >= 1000000007) {
                    active -= 1000000007;
                }
            }
            else {
                newToEnd[record.queryIndex] = active;
            }
        }

        int[] answer = new int[query.length + 1];

        answer[0] = (int)total;

        for(int i = 0; i < query.length; i++) {
            if(query[i][0] == 1) {
                Integer id = pointIndex.get(query[i][1]);

                if(id == null || id == s || id == e) {
                    answer[i + 1] = 0;
                    continue;
                }

                long removed = start[id] * end[id] % 1000000007;

                long result = total - removed;

                if(result < 0) {
                    result += 1000000007;
                }

                answer[i + 1] = (int)result;
            }
            else {
                long qx = query[i][1];
                long qy = query[i][2];
                long qk = query[i][3];

                long in = 0;

                if(qk > 0) {
                    Group prev = map.get(qx - qy - qk);

                    if(prev != null) {
                        int left = upperBound(prev.xs, qx - qk);
                        int right = lowerBound(prev.xs, qx);

                        in = prev.pref[right] - prev.pref[left];

                        if(in < 0) {
                            in += 1000000007;
                        }
                    }
                }

                long added = in * newToEnd[i] % 1000000007;

                long result = total + added;

                if(result >= 1000000007) {
                    result -= 1000000007;
                }

                answer[i + 1] = (int)result;
            }
        }

        return answer;
    }

    static int lowerBound(int[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while(left < right) {
            int mid = (left + right) >>> 1;

            if((long)arr[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(int[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while(left < right) {
            int mid = (left + right) >>> 1;

            if((long)arr[mid] <= target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }
}