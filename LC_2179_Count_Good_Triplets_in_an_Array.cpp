#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    class Fenwick {
    public:
        vector<int> tree;

        Fenwick(int n) {
            tree.resize(n + 1);
        }

        void update(int index, int value) {
            index++;

            while(index < tree.size()) {
                tree[index] += value;
                index += index & -index;
            }
        }

        int query(int index) {
            int result = 0;
            index++;

            while(index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }
    };

    long long goodTriplets(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();

        vector<int> position(n);

        for(int i = 0; i < n; i++) {
            position[nums2[i]] = i;
        }

        vector<int> order(n);

        for(int i = 0; i < n; i++) {
            order[i] = position[nums1[i]];
        }

        vector<long long> left(n);
        vector<long long> right(n);

        Fenwick fenwick(n);

        for(int i = 0; i < n; i++) {
            left[i] = fenwick.query(order[i] - 1);
            fenwick.update(order[i], 1);
        }

        fenwick = Fenwick(n);

        for(int i = n - 1; i >= 0; i--) {
            right[i] = fenwick.query(n - 1) - fenwick.query(order[i]);
            fenwick.update(order[i], 1);
        }

        long long answer = 0;

        for(int i = 0; i < n; i++) {
            answer += left[i] * right[i];
        }

        return answer;
    }
};