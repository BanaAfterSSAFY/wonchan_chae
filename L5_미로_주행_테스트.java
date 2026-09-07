import java.util.*;

class Solution {

    static final long MASK = (1L << 20) - 1;

    long baseVLow;
    long baseVHigh;

    long[] coords;
    int leafCount;

    int[] cover;
    int[] cntEven;
    int[] cntOdd;

    long[] sumEven;
    long[] sumOdd;

    long queryCount;
    long querySum;

    public long solution(int n, int m, int[][] tests) {
        long uLow = 0;
        long uHigh = (long)n + m;
        long vLow = -((long)m);
        long vHigh = n;

        for(int[] test : tests) {
            if(test[3] == 0) {
                continue;
            }

            long u = (long)test[0] + test[1];
            long v = (long)test[0] - test[1];
            long d = test[2];

            uLow = Math.max(uLow, u - d);
            uHigh = Math.min(uHigh, u + d);
            vLow = Math.max(vLow, v - d);
            vHigh = Math.min(vHigh, v + d);
        }

        if(uLow > uHigh || vLow > vHigh) {
            return 0;
        }

        baseVLow = -((long)m);
        baseVHigh = n;

        int maxNo = tests.length;

        long[] rectVL = new long[maxNo];
        long[] rectVR = new long[maxNo];

        long[] eventKeys = new long[maxNo * 2];
        long[] vPoints = new long[maxNo * 2];

        int rectCount = 0;
        int eventCount = 0;
        int vPointCount = 0;

        for(int[] test : tests) {
            if(test[3] == 1) {
                continue;
            }

            long u = (long)test[0] + test[1];
            long v = (long)test[0] - test[1];
            long d = test[2];

            long ul = Math.max(uLow, u - d);
            long ur = Math.min(uHigh, u + d);

            long vl = Math.max(vLow, v - d);
            long vr = Math.min(vHigh, v + d);

            if(ul > ur || vl > vr) {
                continue;
            }

            int id = rectCount++;

            rectVL[id] = vl;
            rectVR[id] = vr;

            long addCode = (long)id << 1;
            long removeCode = addCode | 1L;

            eventKeys[eventCount++] = encodeEvent(ul, addCode);
            eventKeys[eventCount++] = encodeEvent(ur + 1, removeCode);

            vPoints[vPointCount++] = vl;
            vPoints[vPointCount++] = vr + 1;
        }

        Arrays.sort(eventKeys, 0, eventCount);

        long[] cuts = new long[eventCount + 64];
        int cutCount = 0;

        cuts[cutCount++] = uLow;
        cuts[cutCount++] = uHigh + 1;

        long previousEventU = Long.MIN_VALUE;

        for(int i = 0; i < eventCount; i++) {
            long u = decodeEventU(eventKeys[i]);

            if(u != previousEventU) {
                cuts[cutCount++] = u;
                previousEventU = u;
            }
        }

        long[][] lower = {
            {-1, 0},
            {0, vLow},
            {1, -2L * m}
        };

        long[][] upper = {
            {1, 0},
            {0, vHigh},
            {-1, 2L * n}
        };

        for(int i = 0; i < 3; i++) {
            for(int j = i + 1; j < 3; j++) {
                cutCount = addCrossCuts(
                    cuts,
                    cutCount,
                    lower[i],
                    lower[j],
                    uLow,
                    uHigh
                );

                cutCount = addCrossCuts(
                    cuts,
                    cutCount,
                    upper[i],
                    upper[j],
                    uLow,
                    uHigh
                );
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cutCount = addCrossCuts(
                    cuts,
                    cutCount,
                    lower[i],
                    upper[j],
                    uLow,
                    uHigh
                );
            }
        }

        Arrays.sort(cuts, 0, cutCount);

        int uniqueCutCount = 0;

        for(int i = 0; i < cutCount; i++) {
            if(uniqueCutCount == 0 || cuts[uniqueCutCount - 1] != cuts[i]) {
                cuts[uniqueCutCount++] = cuts[i];
            }
        }

        if(rectCount > 0) {
            Arrays.sort(vPoints, 0, vPointCount);

            int uniqueVCount = 0;

            for(int i = 0; i < vPointCount; i++) {
                if(uniqueVCount == 0 || vPoints[uniqueVCount - 1] != vPoints[i]) {
                    vPoints[uniqueVCount++] = vPoints[i];
                }
            }

            coords = Arrays.copyOf(vPoints, uniqueVCount);
            leafCount = uniqueVCount - 1;

            int treeSize = Math.max(8, leafCount * 4 + 8);

            cover = new int[treeSize];
            cntEven = new int[treeSize];
            cntOdd = new int[treeSize];

            sumEven = new long[treeSize];
            sumOdd = new long[treeSize];
        }

        long total = 0;
        long blocked = 0;

        int eventIndex = 0;

        for(int ci = 0; ci + 1 < uniqueCutCount; ci++) {
            long start = cuts[ci];
            long end = cuts[ci + 1] - 1;

            while(
                eventIndex < eventCount
                && decodeEventU(eventKeys[eventIndex]) == start
            ) {
                long code = eventKeys[eventIndex] & MASK;

                int id = (int)(code >>> 1);
                int delta = (code & 1L) == 0 ? 1 : -1;

                int leftIndex = lowerBound(coords, rectVL[id]);
                int rightIndex = lowerBound(coords, rectVR[id] + 1) - 1;

                if(leftIndex <= rightIndex) {
                    update(
                        1,
                        0,
                        leafCount - 1,
                        leftIndex,
                        rightIndex,
                        delta
                    );
                }

                eventIndex++;
            }

            if(start > end || start < uLow || end > uHigh) {
                continue;
            }

            long lowerValue = Long.MIN_VALUE;
            int lowerA = 0;
            long lowerB = 0;

            for(long[] expression : lower) {
                long value = expression[0] * start + expression[1];

                if(value > lowerValue) {
                    lowerValue = value;
                    lowerA = (int)expression[0];
                    lowerB = expression[1];
                }
            }

            long upperValue = Long.MAX_VALUE;
            int upperA = 0;
            long upperB = 0;

            for(long[] expression : upper) {
                long value = expression[0] * start + expression[1];

                if(value < upperValue) {
                    upperValue = value;
                    upperA = (int)expression[0];
                    upperB = expression[1];
                }
            }

            if(lowerValue > upperValue) {
                continue;
            }

            total += calcPrefixSum(
                start,
                end,
                upperA,
                upperB,
                false
            );

            total -= calcPrefixSum(
                start,
                end,
                lowerA,
                lowerB - 1,
                false
            );

            if(rectCount > 0) {
                blocked += calcPrefixSum(
                    start,
                    end,
                    upperA,
                    upperB,
                    true
                );

                blocked -= calcPrefixSum(
                    start,
                    end,
                    lowerA,
                    lowerB - 1,
                    true
                );
            }
        }

        return total - blocked;
    }

    long calcPrefixSum(
        long start,
        long end,
        int a,
        long b,
        boolean coveredOnly
    ) {
        long result = 0;

        for(int parity = 0; parity < 2; parity++) {
            long first = start;

            if((first & 1L) != parity) {
                first++;
            }

            long last = end;

            if((last & 1L) != parity) {
                last--;
            }

            if(first > last) {
                continue;
            }

            long countU = (last - first) / 2 + 1;

            if(a == 0) {
                getPrefix(b, parity, coveredOnly);

                result += countU * queryCount;

                continue;
            }

            long evenB = (b & 1L) == 0 ? b : b - 1;

            long t1;
            long t2;

            if(a == 1) {
                t1 = first + evenB;
                t2 = last + evenB;
            }
            else {
                t1 = evenB - last;
                t2 = evenB - first;
            }

            getPrefix(t1, parity, coveredOnly);

            long c1 = queryCount;
            long s1 = querySum;

            getPrefix(t2, parity, coveredOnly);

            long c2 = queryCount - c1;
            long s2 = querySum - s1;

            result += countU * c1;
            result += (c2 * (t2 + 2) - s2) / 2;
        }

        return result;
    }

    void getPrefix(long target, int parity, boolean coveredOnly) {
        queryCount = 0;
        querySum = 0;

        if(target < baseVLow) {
            return;
        }

        long high = Math.min(target, baseVHigh);

        if(high < baseVLow) {
            return;
        }

        if(!coveredOnly) {
            addRaw(baseVLow, high, parity);
            return;
        }

        if(leafCount <= 0 || high < coords[0]) {
            return;
        }

        long clippedHigh = Math.min(
            high,
            coords[coords.length - 1] - 1
        );

        if(clippedHigh < coords[0]) {
            return;
        }

        queryPrefix(
            1,
            0,
            leafCount - 1,
            clippedHigh,
            parity
        );
    }

    void queryPrefix(
        int node,
        int left,
        int right,
        long target,
        int parity
    ) {
        long low = coords[left];
        long high = coords[right + 1] - 1;

        if(target < low) {
            return;
        }

        if(high <= target) {
            if(parity == 0) {
                queryCount += cntEven[node];
                querySum += sumEven[node];
            }
            else {
                queryCount += cntOdd[node];
                querySum += sumOdd[node];
            }

            return;
        }

        if(cover[node] > 0) {
            addRaw(low, target, parity);
            return;
        }

        if(left == right) {
            return;
        }

        int mid = (left + right) >>> 1;

        queryPrefix(
            node << 1,
            left,
            mid,
            target,
            parity
        );

        if(target >= coords[mid + 1]) {
            queryPrefix(
                node << 1 | 1,
                mid + 1,
                right,
                target,
                parity
            );
        }
    }

    void addRaw(long low, long high, int parity) {
        if(low > high) {
            return;
        }

        long first = low;

        if((first & 1L) != parity) {
            first++;
        }

        long last = high;

        if((last & 1L) != parity) {
            last--;
        }

        if(first > last) {
            return;
        }

        long count = (last - first) / 2 + 1;
        long sum = count * ((first + last) / 2);

        queryCount += count;
        querySum += sum;
    }

    void update(
        int node,
        int left,
        int right,
        int ql,
        int qr,
        int delta
    ) {
        if(qr < left || right < ql) {
            return;
        }

        if(ql <= left && right <= qr) {
            cover[node] += delta;
            pull(node, left, right);
            return;
        }

        int mid = (left + right) >>> 1;

        update(
            node << 1,
            left,
            mid,
            ql,
            qr,
            delta
        );

        update(
            node << 1 | 1,
            mid + 1,
            right,
            ql,
            qr,
            delta
        );

        pull(node, left, right);
    }

    void pull(int node, int left, int right) {
        if(cover[node] > 0) {
            long low = coords[left];
            long high = coords[right + 1] - 1;

            long firstEven = (low & 1L) == 0 ? low : low + 1;
            long lastEven = (high & 1L) == 0 ? high : high - 1;

            if(firstEven <= lastEven) {
                long count = (lastEven - firstEven) / 2 + 1;

                cntEven[node] = (int)count;
                sumEven[node] = count * ((firstEven + lastEven) / 2);
            }
            else {
                cntEven[node] = 0;
                sumEven[node] = 0;
            }

            long firstOdd = (low & 1L) != 0 ? low : low + 1;
            long lastOdd = (high & 1L) != 0 ? high : high - 1;

            if(firstOdd <= lastOdd) {
                long count = (lastOdd - firstOdd) / 2 + 1;

                cntOdd[node] = (int)count;
                sumOdd[node] = count * ((firstOdd + lastOdd) / 2);
            }
            else {
                cntOdd[node] = 0;
                sumOdd[node] = 0;
            }

            return;
        }

        if(left == right) {
            cntEven[node] = 0;
            cntOdd[node] = 0;

            sumEven[node] = 0;
            sumOdd[node] = 0;

            return;
        }

        int lc = node << 1;
        int rc = lc | 1;

        cntEven[node] = cntEven[lc] + cntEven[rc];
        cntOdd[node] = cntOdd[lc] + cntOdd[rc];

        sumEven[node] = sumEven[lc] + sumEven[rc];
        sumOdd[node] = sumOdd[lc] + sumOdd[rc];
    }

    int addCrossCuts(
        long[] cuts,
        int count,
        long[] e1,
        long[] e2,
        long low,
        long high
    ) {
        long da = e1[0] - e2[0];

        if(da == 0) {
            return count;
        }

        long numerator = e2[1] - e1[1];

        if(da < 0) {
            da = -da;
            numerator = -numerator;
        }

        long floor = Math.floorDiv(numerator, da);

        if(low <= floor && floor <= high + 1) {
            cuts[count++] = floor;
        }

        if(low <= floor + 1 && floor + 1 <= high + 1) {
            cuts[count++] = floor + 1;
        }

        return count;
    }

    long encodeEvent(long u, long code) {
        return ((u + 2000000005) << 20) | code;
    }

    long decodeEventU(long key) {
        return (key >>> 20) - 2000000005;
    }

    int lowerBound(long[] array, long target) {
        int left = 0;
        int right = array.length;

        while(left < right) {
            int mid = (left + right) >>> 1;

            if(array[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }
}