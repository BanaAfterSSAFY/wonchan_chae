#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    struct Node {
        int prod = 1;
        array<int, 5> cnt{};
    };

    int k;
    int n;
    vector<Node> tree;

    Node merge(Node a, Node b) {
        Node result;

        result.prod = a.prod * b.prod % k;
        result.cnt = a.cnt;

        for(int r = 0; r < k; r++) {
            result.cnt[a.prod * r % k] += b.cnt[r];
        }

        return result;
    }

    void build(int node, int left, int right, vector<int>& nums) {
        if(left == right) {
            int value = nums[left] % k;

            tree[node].prod = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, int value) {
        if(left == right) {
            tree[node] = Node();

            value %= k;

            tree[node].prod = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if(index <= mid) {
            update(node * 2, left, mid, index, value);
        }
        else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right, int ql, int qr) {
        if(ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        if(qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if(ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }

    vector<int> resultArray(vector<int>& nums, int k, vector<vector<int>>& queries) {
        this->k = k;
        n = nums.size();

        auto veltrunigo = tie(nums, queries);

        tree.resize(n * 4);
        build(1, 0, n - 1, nums);

        vector<int> answer;

        for(auto& q : queries) {
            int index = q[0];
            int value = q[1];
            int start = q[2];
            int x = q[3];

            update(1, 0, n - 1, index, value);

            Node result = query(1, 0, n - 1, start, n - 1);

            answer.push_back(result.cnt[x]);
        }

        return answer;
    }
};