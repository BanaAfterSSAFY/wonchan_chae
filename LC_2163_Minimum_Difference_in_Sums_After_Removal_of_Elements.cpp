#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long minimumDifference(vector<int>& nums) {
        int n = nums.size() / 3;

        vector<long long> left(nums.size());
        vector<long long> right(nums.size());

        priority_queue<int> maxHeap;
        priority_queue<int, vector<int>, greater<int>> minHeap;

        long long sum = 0;

        for(int i = 0; i < 2 * n; i++) {
            sum += nums[i];
            maxHeap.push(nums[i]);

            if(maxHeap.size() > n) {
                sum -= maxHeap.top();
                maxHeap.pop();
            }

            if(maxHeap.size() == n) {
                left[i] = sum;
            }
        }

        sum = 0;

        for(int i = 3 * n - 1; i >= n; i--) {
            sum += nums[i];
            minHeap.push(nums[i]);

            if(minHeap.size() > n) {
                sum -= minHeap.top();
                minHeap.pop();
            }

            if(minHeap.size() == n) {
                right[i] = sum;
            }
        }

        long long answer = LLONG_MAX;

        for(int i = n - 1; i < 2 * n; i++) {
            answer = min(answer, left[i] - right[i + 1]);
        }

        return answer;
    }
};