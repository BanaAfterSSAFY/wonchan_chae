#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    class Fenwick {
    public:
        vector<long long> tree;

        Fenwick(int n) {
            tree.assign(n + 1, 0);
        }

        void update(int index) {
            index++;

            while(index < tree.size()) {
                tree[index]++;
                index += index & -index;
            }
        }

        long long query(int index) {
            long long result = 0;
            index++;

            while(index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }
    };

    long long countMajoritySubarrays(vector<int>& nums, int target) {
        int n = nums.size();
        int offset = n + 1;

        Fenwick fenwick(2 * n + 3);

        long long answer = 0;
        int prefix = 0;

        fenwick.update(offset);

        for(int x : nums) {
            prefix += x == target ? 1 : -1;

            answer += fenwick.query(prefix + offset - 1);
            fenwick.update(prefix + offset);
        }

        return answer;
    }
};